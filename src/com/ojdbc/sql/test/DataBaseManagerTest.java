package com.ojdbc.sql.test;

import com.ojdbc.sql.DataBase;
import com.ojdbc.sql.DataBaseEnum;
import com.ojdbc.sql.DataBaseManager;
import com.ojdbc.sql.SQLResultSet;

public class DataBaseManagerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("欢迎使用iDataBaseConnection.java");
		System.out.println("Create by shaogaige 20170625");
		//testCase();
	}
	
	public static void testCase(){
		DataBaseEnum type = DataBaseEnum.SQSLITE;
		String dataBaseURL = "jdbc:sqlite://E:/shaogaige/iNote/iNoteRun/data/iNoteData.note";
		String userName = "";
		String passWord = "";
		DataBase sqlite = DataBaseManager.getDataBase(type, dataBaseURL, userName, passWord);
        System.out.println(sqlite.getConnectionString());
        
        String sql = "select * from noteinfo";
        sqlite.exeSQLSelect(sql);
        
        String sql2 = "select * from noteinfo";
        SQLResultSet r = sqlite.exeSQLSelect(sql2);
        System.out.println(r.getRowNum());
        
		dataBaseURL = "jdbc:mongo://172.15.103.42:10001/geoglobe";
		userName = "data";
		passWord = "data";
        DataBase mongo = DataBaseManager.getDataBase(DataBaseEnum.MONGODB, dataBaseURL, userName, passWord);
        System.out.println(mongo.getConnectionString());
        
		dataBaseURL = "jdbc:mysql://172.15.103.42:3306/geoglobe";
		userName = "root";
		passWord = "root";
        DataBase mysql = DataBaseManager.getDataBase(DataBaseEnum.ORACLE, dataBaseURL, userName, passWord);
        System.out.println(mysql.getConnectionString());
        
		dataBaseURL = "jdbc:oracle:thin:@172.15.103.43:1521:geoglobe";
		userName = "autotest";
		passWord = "autotest";
        DataBase oracle = DataBaseManager.getDataBase(DataBaseEnum.ORACLE, dataBaseURL, userName, passWord);
        System.out.println(oracle.getConnectionString());
	}
}
