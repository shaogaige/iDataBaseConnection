/**
 * ClassName: SQLServerDataBaseConnection.java
 * Date: 2017年5月23日
 */
package com.ojdbc.sql.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.ojdbc.sql.IConnection;
import com.ojdbc.sql.exception.DBCException;

/**
 * Author: ShaoGaige
 * Description: SQLServer数据库连接
 * Log: 
 */
public class SQLServerDataBaseConnection implements IConnection {

	//初始化jdbc
	static
	{
		try 
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
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
	 * dataBaseURL: jdbc:sqlserver://127.0.0.1:1433;DatabaseName=test
	 * userName: root
	 * passWord: root
	 */
	@Override
	public Connection createConnection(String dataBaseURL, String userName,
			String passWord) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try 
		{
			conn = DriverManager.getConnection(dataBaseURL,userName,passWord);
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
