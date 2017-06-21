/**
 * ClassName: GodTool.java
 * Date: 2017年6月10日
 */
package com.ojdbc.sql.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.ojdbc.sql.ConnectionManager.ConnectionInfo;
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
			
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
