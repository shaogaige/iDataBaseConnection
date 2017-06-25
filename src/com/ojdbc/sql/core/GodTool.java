/**
 * ClassName: GodTool.java
 * Date: 2017年6月10日
 */
package com.ojdbc.sql.core;

import java.lang.reflect.Constructor;

import com.ojdbc.sql.ConnectionManager.ConnectionInfo;
import com.ojdbc.sql.exception.DBCException;
import com.ojdbc.sql.DataBase;
import com.ojdbc.sql.IDataSource;

/**
 * Author: ShaoGaige
 * Description: GodTool反射类
 * Log: 
 */
public class GodTool {
	/**
	 * 利用反射构建DataBase对象
	 * @param connInfo
	 * @return DataBase
	 */
	public static <T extends Enum<T> & IDataSource> DataBase newInstanceDataBase(ConnectionInfo connInfo)
	{
		try 
		{
			Constructor<?> dataBaseConstr = connInfo.getIDataSource().getDataBase().getDeclaredConstructor(ConnectionInfo.class);
			DataBase dataBase= (DataBase) dataBaseConstr.newInstance(connInfo);
			return dataBase;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DBCException.logException(DBCException.E_newInstanceDataBase, e);
			return null;
		}
	}

}
