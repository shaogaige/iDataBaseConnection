/**
 * ClassName: ConnectionManager.java
 * Date: 2017年6月20日
 */
package com.ojdbc.sql;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.pool.KeyedPoolableObjectFactory;
import org.apache.commons.pool.impl.GenericKeyedObjectPool;

import com.ojdbc.sql.core.DBConnectionFactory;

/**
 * Author: ShaoGaige
 * Description: 数据库连接管理类
 * Log: 
 */
public class ConnectionManager {
	//对象池
	private static GenericKeyedObjectPool connectionPool = null;
	//保存key和IConnection
	private static Map<String,IConnection> keyConnection = new HashMap<String,IConnection>();
	//相关配置
	private static Properties configContent = null;
	
	//私有构造函数
	private ConnectionManager()
	{
		//do something
	}
	//初始化
	static
	{
		init();
	}
	/**
	 * 根据数据库连接标识获取连接接口
	 * @param key
	 * @return IConnection
	 */
	public static IConnection getIConnection(String key)
	{
		return keyConnection.get(key);
	}
	/**
	 * 获得数据库连接信息
	 * @param type
	 * @param dataBaseURL
	 *   [数据库类别：dataBaseURL值的写法]
	 *   [Oracle: jdbc:oracle:thin:@127.0.0.1:1521:orcl]
	 *   [SQLServer: jdbc:sqlserver://127.0.0.1:1433;DatabaseName=test]
	 *   [MySQL: jdbc:mysql://127.0.0.1:3306/test]
	 *   [PostgreSQL: jdbc:postgresql://127.0.0.1:5432/postgis]
	 *   [SQLite: jdbc:sqlite://d:/test.db]
	 *   [Mongo: jdbc:mongo://127.0.0.1:29847/test]
	 *   [Access: e://student.mdb]
	 * @param userName
	 * @param passWord
	 * @return ConnectionInfo
	 */
	public static <T extends Enum<T> & IDataSource> ConnectionInfo getConnectionInfo(T type,String dataBaseURL,String userName,String passWord)
	{
		String key = dataBaseURL+"+"+userName+"+"+passWord;
		IConnection conn = null;
		if(!keyConnection.containsKey(key))
		{
			conn = type.getConnection();
			keyConnection.put(key, conn);
		}
		return new ConnectionInfo(key,type);
	}
	/**
	 * 获取数据库连接对象
	 * @param type
	 * @param dataBaseURL
	 *   [数据库类别：dataBaseURL值的写法]
	 *   [Oracle: jdbc:oracle:thin:@127.0.0.1:1521:orcl]
	 *   [SQLServer: jdbc:sqlserver://127.0.0.1:1433;DatabaseName=test]
	 *   [MySQL: jdbc:mysql://127.0.0.1:3306/test]
	 *   [PostgreSQL: jdbc:postgresql://127.0.0.1:5432/postgis]
	 *   [SQLite: jdbc:sqlite://d:/test.db]
	 *   [Mongo: jdbc:mongo://127.0.0.1:29847/test]
	 *   [Access: e://student.mdb]
	 * @param userName
	 * @param passWord
	 * @return ConnectionObject
	 */
	public static <T extends Enum<T> & IDataSource> ConnectionObject borrowConnectionObject(T type,String dataBaseURL,String userName,String passWord)
	{
		String key = dataBaseURL+"+"+userName+"+"+passWord;
		IConnection conn = null;
		if(keyConnection.containsKey(key))
		{
			conn = keyConnection.get(key);
		}
		else
		{
			conn = type.getConnection();
			keyConnection.put(key, conn);
		}
		ConnectionObject connobject = null;
		try 
		{
			connobject = (ConnectionObject) connectionPool.borrowObject(key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return connobject;
	}
	/**
	 * 从连接池中拿到一个连接对象
	 * WARNING:需要归还！调用returnConnectionObject
	 * @param connInfo
	 * @return ConnectionObject
	 */
	public static <T extends Enum<T> & IDataSource> ConnectionObject borrowConnectionObject(ConnectionInfo connInfo)
	{
		String key = connInfo.getKey();
		ConnectionObject connobject = null;
		try 
		{
			connobject = (ConnectionObject) connectionPool.borrowObject(key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return connobject;
	}
	/**
	 * 归还数据库连接对象
	 * @param conn
	 * @return boolean
	 */
	public static boolean returnConnectionObject(ConnectionObject conn)
	{
		try 
		{
			connectionPool.returnObject(conn.getKey(),conn);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 根据配置信息初始化连接池
	 * @param type
	 * @param dataBaseURL
	 *   [数据库类别：dataBaseURL值的写法]
	 *   [Oracle: jdbc:oracle:thin:@127.0.0.1:1521:orcl]
	 *   [SQLServer: jdbc:sqlserver://127.0.0.1:1433;DatabaseName=test]
	 *   [MySQL: jdbc:mysql://127.0.0.1:3306/test]
	 *   [PostgreSQL: jdbc:postgresql://127.0.0.1:5432/postgis]
	 *   [SQLite: jdbc:sqlite://d:/test.db]
	 *   [Mongo: jdbc:mongo://127.0.0.1:29847/test]
	 *   [Access: e://student.mdb]
	 * @param userName
	 * @param passWord
	 */
	public static <T extends Enum<T> & IDataSource> void initConnectionPoolByConfigure(T type,String dataBaseURL,String userName,String passWord)
	{
		String key = dataBaseURL+"+"+userName+"+"+passWord;
		IConnection conn = null;
		if(keyConnection.containsKey(key))
		{
			conn = keyConnection.get(key);
		}
		else
		{
			conn = type.getConnection();
			keyConnection.put(key, conn);
		}
		
		ConnectionObject connobject = null;
		int size = Integer.parseInt(configContent.getProperty("minIdle"));
		for(int i=0;i<size;i++)
		{
			try 
			{
				connobject = (ConnectionObject) connectionPool.borrowObject(key);
				connectionPool.returnObject(key, connobject);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//对象池初始化
	private static void init()
	{
		KeyedPoolableObjectFactory fac = (KeyedPoolableObjectFactory) new DBConnectionFactory();
		//读取配置信息
		configContent = readConfig();
		GenericKeyedObjectPool.Config config = new GenericKeyedObjectPool.Config();   
		config.maxTotal = 300;//池中对象总数
		if(configContent.getProperty("maxTotal") != null && !"".equals(configContent.getProperty("maxTotal")))
		{
			config.maxTotal = Integer.parseInt(configContent.getProperty("maxTotal"));
		}
		
		config.maxActive = 100;//每个键分配的最大对象数 
		if(configContent.getProperty("maxActive") != null && !"".equals(configContent.getProperty("maxActive")))
		{
			config.maxActive = Integer.parseInt(configContent.getProperty("maxActive"));
		}
		
		config.maxWait = 10 * 1000L;//获取对象的等待时间
		config.testOnBorrow = true;//借出对象时监测对象是否可用
		config.maxIdle = 30;//最大空闲对象数
		if(configContent.getProperty("maxIdle") != null && !"".equals(configContent.getProperty("maxIdle")))
		{
			config.maxIdle = Integer.parseInt(configContent.getProperty("maxIdle"));
		}
		
		config.minIdle = 10;//最小空闲对象数
		if(configContent.getProperty("minIdle") != null && !"".equals(configContent.getProperty("minIdle")))
		{
			config.minIdle = Integer.parseInt(configContent.getProperty("minIdle"));
		}
		
		config.minEvictableIdleTimeMillis = 1000L*60L*10L;//空闲对象清理之前在池中闲置的最小时间
		if(configContent.getProperty("minEvictableIdleTimeMillis") != null && !"".equals(configContent.getProperty("minEvictableIdleTimeMillis")))
		{
			config.minEvictableIdleTimeMillis = Integer.parseInt(configContent.getProperty("minEvictableIdleTimeMillis"));
		}
		
		config.timeBetweenEvictionRunsMillis = 1000L*60L*30L;//空闲对象清理运行周期
		if(configContent.getProperty("timeBetweenEvictionRunsMillis") != null && !"".equals(configContent.getProperty("timeBetweenEvictionRunsMillis")))
		{
			config.timeBetweenEvictionRunsMillis = Integer.parseInt(configContent.getProperty("timeBetweenEvictionRunsMillis"));
		}
		
		config.whenExhaustedAction = GenericKeyedObjectPool.WHEN_EXHAUSTED_GROW;//当达到最大容量时直接创建
		
		connectionPool = new GenericKeyedObjectPool(fac, config);  
	}
	//读取配置文件
	private static Properties readConfig()
	{
		Properties configParam = new Properties();
		try 
		{
			configParam.load(DataBaseManager.class.getClassLoader().getResourceAsStream("config/ConnectionPool.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return configParam;
	}
	
	//==========================================================================
	/**
	 * 内部静态类
	 * @author SHAO Gaige
	 *
	 */
	public static class ConnectionInfo{
		//type
		private IDataSource sourceType;
		//key
		private String key;
		
		private ConnectionInfo(String key,IDataSource sourceType){
			this.key = key;
			this.sourceType = sourceType;
		}
		/**
		 * 获取IDataSource操作接口
		 * @return IDataSource
		 */
		public IDataSource getIDataSource() {
			return sourceType;
		}
		/**
		 * 获取数据库连接标识
		 * @return String
		 */
		public String getKey() {
			return key;
		}
	}
	//==========================================================================

}
