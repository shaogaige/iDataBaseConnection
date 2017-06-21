/**
 * ClassName: DataBaseManager.java
 * Date: 2017年5月23日
 */
package com.ojdbc.sql;

import com.ojdbc.sql.ConnectionManager.ConnectionInfo;
import com.ojdbc.sql.core.GodTool;

/**
 * Author: ShaoGaige
 * Description: 数据库管理类
 * Log: 
 */
public class DataBaseManager {
	
	//私有构造函数
	private DataBaseManager()
	{
		//do something
	}
	/**
	 * 获取数据库对象
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
	 * @return DataBase
	 */
	public static <T extends Enum<T> & IDataSource> DataBase getDataBase(T type,String dataBaseURL,String userName,String passWord)
	{
		ConnectionInfo connInfo = ConnectionManager.getConnectionInfo(type, dataBaseURL, userName, passWord);
		DataBase dataBase = GodTool.newInstanceDataBase(connInfo);
		return dataBase;
	}
	/**
	 * 由数据库连接信息获取数据库对象
	 * @param connInfo
	 * @return DataBase
	 */
	public static DataBase getDataBase(ConnectionInfo connInfo)
	{
		DataBase dataBase = GodTool.newInstanceDataBase(connInfo);
		return dataBase;
	}

}