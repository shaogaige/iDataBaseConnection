/**
 * ClassName: PostgreSQLDataBaseConnection.java
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
 * Description: PostgreSQL数据库连接
 * Log: 
 */
public class PostgreSQLDataBaseConnection implements IConnection {

	//初始化jdbc
	static
	{
		try 
		{
			Class.forName("org.postgresql.Driver");
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
	 * dataBaseURL: jdbc:postgresql://127.0.0.1:5432/postgis
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
