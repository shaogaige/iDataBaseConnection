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
	 * 数据库类型实现接口
	 * @param dataBaseType
	 * @param dataBaseClass
	 * @return DataBaseType
	 */
	public <T extends IConnection> DataBaseType createDataBaseType(String dataBaseType,T dataBaseClass);

}
