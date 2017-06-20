package com.ojdbc.sql.test;

import com.ojdbc.sql.DataBase;
import com.ojdbc.sql.DataBaseEnum;
import com.ojdbc.sql.DataBaseManager;

public class DataBaseManagerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataBaseEnum type = DataBaseEnum.SQSLITE;
		String dataBaseURL = "jdbc:sqlite://E:/shaogaige/iNote/iNoteRun/data/iNoteData.note";
		String userName = "";
		String passWord = "";
		DataBase sqlite = DataBaseManager.getDataBase(DataBaseEnum.MYSQL, dataBaseURL, userName, passWord);
        System.out.println(sqlite.getConnectionString());
        
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
