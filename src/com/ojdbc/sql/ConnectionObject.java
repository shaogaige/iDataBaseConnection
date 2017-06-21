/**
 * ClassName: ConnectionObject.java
 * Date: 2017年6月10日
 */
package com.ojdbc.sql;

import java.sql.Connection;

/**
 * Author: ShaoGaige
 * Description: 数据库连接对象类
 * Log: 
 */
public class ConnectionObject {
	//数据库连接
	private Connection connection = null;
	//连接标识
	private String key;
	//构造函数
	public ConnectionObject(String key,Connection connection)
	{
		if(key!=null && connection!=null)
		{
			this.key = key;
			this.connection = connection;
		}
	}
	/**
	 * 获取java.sql.Connection对象
	 * @return Connection
	 */
	public Connection getConnection() {
		return connection;
	}
	/**
	 * 获取数据库连接标识
	 * @return String
	 */
	public String getKey() {
		return key;
	}
}
