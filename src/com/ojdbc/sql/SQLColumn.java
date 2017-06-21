/**
 * ClassName: SQLColumn.java
 * Date: 2017年6月1日
 */
package com.ojdbc.sql;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: ShaoGaige
 * Description: 列数据访问
 * Log: 
 */
public class SQLColumn {
	
    private String columnName = "";
	
	private List<Value> values = new ArrayList<Value>();
	//构造函数
	public SQLColumn(String columnName,List<Value> values)
	{
		if(columnName != null && values != null)
		{
			this.columnName = columnName;
		    this.values = values;
		}
	}
	/**
	 * 获取字段名称
	 * @return String
	 */
	public String getColumnName() {
		return columnName;
	}
    /**
     * 获取本列所有的值
     * @return List<Value>
     */
	public List<Value> getValues() {
		return values;
	}
    /**
     * 给该列增加一个值
     * @param value
     */
	public void addValue(Value value){
		this.values.add(value);
	}

}
