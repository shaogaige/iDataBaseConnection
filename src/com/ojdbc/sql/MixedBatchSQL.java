/**
 * ClassName: MixedBatchSQL.java
 * Date: 2017年6月8日
 */
package com.ojdbc.sql;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: ShaoGaige
 * Description: 混合批量SQL模型类
 * Log: 
 */
public class MixedBatchSQL {
	
	private String preparedSQL = null;
	
	private List<PreparedParam> preparedParams = new ArrayList<PreparedParam>();
	
	private List<String> sql = new ArrayList<String>();
	/**
	 * 增加静态sql语句
	 * @param sql
	 */
	public void addStaticBatchSQL(String sql)
	{
		this.sql.add(sql);
	}
	/**
	 * 获取静态sql语句的数量
	 * @return int
	 */
	public int getStaticSQLCount()
	{
		return this.sql.size();
	}
	/**
	 * 根据索引获取某个静态sql语句
	 * @param index
	 * @return String
	 */
	public String getStaticSQL(int index)
	{
		if(index>=getStaticSQLCount() || index<0)
		{
			return null;
		}
		return this.sql.get(index);
	}
	/**
	 * 设置PreparedSQL语句和参数
	 * @param sql
	 * @param preparedParams
	 */
	public void setPreparedBatchSQL(String sql,List<PreparedParam> preparedParams)
	{
		if(sql!=null && preparedParams!=null)
		{
			this.preparedSQL = sql;
		    this.preparedParams = preparedParams;
		}
		
	}
	/**
	 * 获取PreparedSQL
	 * @return String
	 */
	public String getPreparedSQL() {
		return preparedSQL;
	}
	/**
	 * 获取PreparedSQL的参数
	 * @return List<PreparedParam>
	 */
	public List<PreparedParam> getPreparedParams() {
		return preparedParams;
	}
}
