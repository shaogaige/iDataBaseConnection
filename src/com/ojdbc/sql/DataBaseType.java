/**
 * ClassName: DataBaseType.java
 * Date: 2017年5月22日
 */
package com.ojdbc.sql;

/**
 * Author: ShaoGaige
 * Description: 数据库类别bean
 * Log: 
 */
public class DataBaseType {
	
	//数据库类型
	private String dataBaseType = null;
	//数据库连接类
	private IConnection dataBaseCon = null;
	
	//构造函数
	public DataBaseType(String dataBaseType,IConnection dataBase)
	{
		this.dataBaseType = dataBaseType;
		this.dataBaseCon = dataBase;
	}

	public String getDataBaseType() {
		return dataBaseType;
	}

	public IConnection getDataBase() {
		return dataBaseCon;
	}	

}
