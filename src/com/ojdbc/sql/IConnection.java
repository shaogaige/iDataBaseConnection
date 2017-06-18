/**
 * ClassName: IDataBase.java
 * Date: 2017年5月16日
 */
package com.ojdbc.sql;

import java.sql.Connection;

/**
 * Author: ShaoGaige
 * Description: 数据库连接接口
 * Log: 
 */
public interface IConnection {
	
	/**
	 * 获取数据库的连接
	 * @param dataBaseURL
	 * @param userName
	 * @param passWord
	 * @return java.sql.Connection
	 */
	public Connection createConnection(String dataBaseURL,String userName,String passWord);

}
