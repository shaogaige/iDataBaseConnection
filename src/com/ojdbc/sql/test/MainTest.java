/**
 * ClassName: MainTest.java
 * Date: 2017年5月16日
 */
package com.ojdbc.sql.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ojdbc.sql.DataBase;
import com.ojdbc.sql.DataBaseType;
import com.ojdbc.sql.SQLResultSet;
import com.ojdbc.sql.connection.MongoDataBaseConnection;
import com.ojdbc.sql.connection.MySQLDataBaseConnection;
import com.ojdbc.sql.connection.OracleDataBaseConnection;
import com.ojdbc.sql.connection.SQLiteDataBaseConnection;

/**
 * Author: ShaoGaige
 * Description: 猪测试类
 * Log: 
 */
public class MainTest {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn;
		
		SQLiteDataBaseConnection sqlite = new SQLiteDataBaseConnection();
		String dataBaseURL = "jdbc:sqlite://E:/shaogaige/iNote/iNoteRun/data/iNoteData.note";
		String userName = "";
		String passWord = "";
		conn = sqlite.createConnection(dataBaseURL, userName, passWord);
		System.out.println(conn.getMetaData().getURL());
		
		DataBase database = new DataBase(conn);
		SQLResultSet rs =database.exeSQLSelect("select * from noteinfo");
	    System.out.println(rs.getRowNum());
		
		MongoDataBaseConnection mongo = new MongoDataBaseConnection();
		dataBaseURL = "jdbc:mongo://172.15.103.42:10001/geoglobe";
		userName = "data";
		passWord = "data";
		conn = mongo.createConnection(dataBaseURL, userName, passWord);
		System.out.println(conn.getMetaData().getURL());
		
		MySQLDataBaseConnection mysql = new MySQLDataBaseConnection();
		dataBaseURL = "jdbc:mysql://172.15.103.42:3306/geoglobe";
		userName = "root";
		passWord = "root";
		conn = mysql.createConnection(dataBaseURL, userName, passWord);
		System.out.println(conn.getMetaData().getURL());
		
		OracleDataBaseConnection oracle = new OracleDataBaseConnection();
		dataBaseURL = "jdbc:oracle:thin:@172.15.103.43:1521:geoglobe";
		userName = "autotest";
		passWord = "autotest";
		conn = oracle.createConnection(dataBaseURL, userName, passWord);
		System.out.println(conn.getMetaData().getURL());
	}

}
