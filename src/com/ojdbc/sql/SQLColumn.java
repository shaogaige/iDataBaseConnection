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
	
	public SQLColumn(String columnName,List<Value> values)
	{
		if(columnName != null && values != null)
		{
			this.columnName = columnName;
		    this.values = values;
		}
	}
	
	public String getColumnName() {
		return columnName;
	}

	public List<Value> getValues() {
		return values;
	}

	public void addValue(Value value){
		this.values.add(value);
	}

}
