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
	
	public void addStaticBatchSQL(String sql)
	{
		this.sql.add(sql);
	}
	public int getStaticSQLCount()
	{
		return this.sql.size();
	}
	
	public String getStaticSQL(int index)
	{
		if(index>=getStaticSQLCount() || index<0)
		{
			return null;
		}
		return this.sql.get(index);
	}
	public void setPreparedBatchSQL(String sql,List<PreparedParam> preparedParams)
	{
		if(sql!=null && preparedParams!=null)
		{
			this.preparedSQL = sql;
		    this.preparedParams = preparedParams;
		}
		
	}
	
	public String getPreparedSQL() {
		return preparedSQL;
	}

	public List<PreparedParam> getPreparedParams() {
		return preparedParams;
	}
}
