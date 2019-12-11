/**
 * ClassName: SQLRow.java
 * Date: 2017年6月1日
 */
package com.ojdbc.sql;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Author: ShaoGaige
 * Description: 行数据访问
 * Log: 
 */
public class SQLRow {
	
	private Map<String,Value> row = new LinkedHashMap<String,Value>();
	/**
	 * 构造函数
	 * @param row
	 */
	public SQLRow(Map<String,Value> row)
	{
		if(row != null)
		{
			this.row = row;
		}
	}
	/**
	 * 获取某个字段的值
	 * @param columnName
	 * @return Value
	 */
	public Value getValue(String columnName)
	{
		Value v = this.row.get(columnName);
		if(v != null)
		{
			return v;
		}
		else
		{
			v = this.row.get(columnName.toLowerCase());
			if(v != null)
			{
				return v;
			}
			else
			{
				return this.row.get(columnName.toUpperCase());
			}
		}
		
	}
	/**
	 * 获取字段个数
	 * @return int
	 */
	public int getCount()
	{
		return this.row.size();
	}
	/**
	 * 获取所有的字段名称
	 * @return Set<String>
	 */
	public Set<String> getAllColumnName()
	{
		return this.row.keySet();
	}
	/**
	 * 获取所有的字段值
	 * @return Collection<Value>
	 */
	public Collection<Value> getAllValue()
	{
		return this.row.values();
	}
	/**
	 * 是否包含某个字段
	 * @param columnName
	 * @return boolean
	 */
	public boolean containsColumn(String columnName)
	{
		return this.row.containsKey(columnName);
	}

}
