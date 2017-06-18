/**
 * ClassName: AccessDataBaseConnection.java
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
 * Description: Access数据库连接
 * Log: 
 */
public class AccessDataBaseConnection implements IConnection {
	
	/**
	 * 构造函数
	 * @param conn
	 */
	//初始化jdbc
	static
	{
		try 
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
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
	 * dataBaseURL: e://student.mdb 
	 * userName: null
	 * passWord: null
	 */
	@Override
	public Connection createConnection(String dataBaseURL, String userName,
			String passWord) {
		// TODO Auto-generated method stub
		Connection conn = null;
		String url = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ="+dataBaseURL;
		try 
		{
			conn = DriverManager.getConnection(url,userName,passWord);
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
