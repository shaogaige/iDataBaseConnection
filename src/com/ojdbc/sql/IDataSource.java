/**
 * ClassName: IDataSource.java
 * Date: 2017年5月16日
 */
package com.ojdbc.sql;

/**
 * Author: ShaoGaige
 * Description: 数据源接口
 * Log: 
 */
public interface IDataSource {
	/**
	 * 数据库类型的名称
	 * @return String
	 */
	public String getDataBaseName();
	/**
	 * 数据库连接
	 * @return IConnection
	 */
	public IConnection getConnection();
	/**
	 * 数据库实例
	 * @return T extends DataBase
	 */
	public <T extends DataBase> Class<T> getDataBase();

}
