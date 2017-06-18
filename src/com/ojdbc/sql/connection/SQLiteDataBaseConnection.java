/**
 * ClassName: SQLiteDataBaseConnection.java
 * Date: 2017年5月22日
 */
package com.ojdbc.sql.connection;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.ojdbc.sql.IConnection;
import com.ojdbc.sql.exception.DBCException;

/**
 * Author: ShaoGaige
 * Description: sqlite数据库连接
 * Log: 
 */
public class SQLiteDataBaseConnection implements IConnection {

	//初始化jdbc
	static
	{
		try 
		{
			Class.forName("org.sqlite.JDBC");
		} 
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			DBCException.throwException(DBCException.E_LoadJDBC, e);
		}
	}	

	/* (non-Javadoc)
	 * @see com.ojdbc.sql.IDataBase#createConnection(java.lang.String, java.lang.String, java.lang.String)
	 */
	/**
	 * 示例
	 * dataBaseURL: jdbc:sqlite://d:/test.db
	 * userName: null
	 * passWord: null
	 */
	@Override
	public Connection createConnection(String dataBaseURL, String userName,
			String passWord) {
		// TODO Auto-generated method stub
		Connection conn = null;
		File file = new File(dataBaseURL);
		if(!file.getParentFile().exists())
		{
			file.getParentFile().mkdirs();
		}
		try 
		{
			conn = DriverManager.getConnection(dataBaseURL);
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			DBCException.throwException(DBCException.E_GetConnection, e);
			return null;
		}
		return conn;
	}

}
