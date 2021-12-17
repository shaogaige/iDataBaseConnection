/**
 * ClassName: DBConnectionFactory.java
 * Date: 2017年6月10日
 */
package com.ojdbc.sql.core;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.pool.KeyedPoolableObjectFactory;

import com.ojdbc.sql.ConnectionObject;
import com.ojdbc.sql.IConnection;
import com.ojdbc.sql.IDataSource;
import com.ojdbc.sql.exception.DBCException;

/**
 * Author: ShaoGaige
 * Description: 数据库连接工厂类
 * Log: 
 */
public class DBConnectionFactory implements KeyedPoolableObjectFactory {
	
	//保存key和IConnection
	private static Map<String,IConnection> keyConnection = new HashMap<String,IConnection>();

	/* (non-Javadoc)
	 * @see org.apache.commons.pool.KeyedPoolableObjectFactory#activateObject(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void activateObject(Object arg0, Object arg1) throws Exception {
		// TODO Auto-generated method stub
		//激活对象
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.pool.KeyedPoolableObjectFactory#destroyObject(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void destroyObject(Object arg0, Object arg1) throws Exception {
		// TODO Auto-generated method stub
		//销毁对象
		//String key = (String)arg0;
		//System.out.println("正在销毁连接对象:"+key);
		if(arg1 != null)
		{
			ConnectionObject conn = (ConnectionObject)arg1;
		    conn.getConnection().close();
		    conn = null;
		}
		arg1 = null;
		arg0 = null;
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.pool.KeyedPoolableObjectFactory#makeObject(java.lang.Object)
	 */
	@Override
	public Object makeObject(Object arg0) throws Exception {
		// TODO Auto-generated method stub
		//创建对象
		String key = (String) arg0;
		return createConnectionObject(key);
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.pool.KeyedPoolableObjectFactory#passivateObject(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void passivateObject(Object arg0, Object arg1) throws Exception {
		// TODO Auto-generated method stub
		//挂起对象
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.pool.KeyedPoolableObjectFactory#validateObject(java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean validateObject(Object arg0, Object arg1) {
		// TODO Auto-generated method stub
		//验证对象
		if(arg1 != null)
		{
			//设置为自动提交
			ConnectionObject conn = (ConnectionObject)arg1;
			try {
				if(conn.getConnection().isClosed())
				{
					return false;
				}
				if(conn.getConnection().isValid(3))
				{
					boolean f = conn.getConnection().getAutoCommit();
					if(!f){
						conn.getConnection().setAutoCommit(true);
					}
					return true;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				DBCException.logException(DBCException.E_ValidConnection, e);
				return false;
			}
			return false;
		}
		else
		{
			return false;
		}
		
	}
	/**
	 * 根据数据库连接标识获取连接接口
	 * @param key
	 * @return IConnection
	 */
	public static IConnection getIConnection(String key,IDataSource type)
	{
		IConnection conn = null;
		if(keyConnection.containsKey(key))
		{
			conn = keyConnection.get(key);
		}
		else if(type != null)
		{
			conn = type.getConnection();
			keyConnection.put(key, conn);
		}
		return conn;
	}
	//创建连接对象
	private ConnectionObject createConnectionObject(String key)
	{
		IConnection iconn = getIConnection(key,null);
		String[] info = new String[3];
		String[] param = key.split("\\+");
		for(int i=0;i<param.length && i<3;i++)
		{
			info[i] = param[i];
		}
		Connection conn = null;
		if(iconn != null)
		{
			conn = iconn.createConnection(info[0], info[1], info[2]);
		}
		return new ConnectionObject(key,conn);
	}

}
