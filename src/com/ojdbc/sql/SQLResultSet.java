/**
 * ClassName: SQLResultSet.java
 * Date: 2017年6月1日
 */
package com.ojdbc.sql;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: ShaoGaige
 * Description: 查询结果数据集类
 * Log: 
 */
public class SQLResultSet {
	
	private List<SQLRow> resultSet = new ArrayList<SQLRow>();
	
	public SQLResultSet(List<SQLRow> resultSet)
	{
		if(resultSet != null)
		{
			this.resultSet = resultSet;
		}
	}
	/**
	 * 获取 行 个数
	 * @return int
	 */
	public int getRowNum()
	{
		return this.resultSet.size();
	}
	/**
	 * 获取第几行的数据,起始是 0
	 * @param i
	 * @return SQLRow
	 */
	public SQLRow getRow(int i)
	{
		if(i>=this.resultSet.size() || i<0)
		{
			return null;
		}
		return this.resultSet.get(i);
	}
	/**
	 * 获取所有的行数据
	 * @return List<SQLRow>
	 */
	public List<SQLRow> getAllRows()
	{
		return this.resultSet;
	}
	/**
	 * 获取某一列数据
	 * @param columnName
	 * @return SQLColumn
	 */
	public SQLColumn getColumn(String columnName)
	{
		if(this.resultSet.size()>0)
		{
			if(this.resultSet.get(0).containsColumn(columnName))
			{
				List<Value> values = new ArrayList<Value>();
				for(SQLRow sqlRow:this.resultSet)
				{
					Value value = sqlRow.getValue(columnName);
					values.add(value);
				}
				SQLColumn column = new SQLColumn(columnName,values);
				return column;
			}
			else
			{
				return null;
			}
		}
		else
		{
			return null;
		}
	}

}
