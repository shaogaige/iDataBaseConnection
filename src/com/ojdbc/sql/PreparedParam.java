/**
 * ClassName: PreparedParam.java
 * Date: 2017年6月7日
 */
package com.ojdbc.sql;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Author: ShaoGaige
 * Description: SQL的Prepared参数类
 * Log:
 */
public class PreparedParam {
	
	private Map<Integer,Value> row = new LinkedHashMap<Integer,Value>();
	/**
	 * 增加参数
	 * @param index
	 * @param value
	 */
	public void addParam(int index,Value value)
	{
		this.row.put(index, value);
	}
	/**
	 * 获取第几个参数
	 * @param index
	 * @return Value
	 */
	public Value getParam(int index)
	{
		return this.row.get(index);
	}
	/**
	 * 获取参数个数
	 * @return int
	 */
	public int getParamCount()
	{
		return this.row.size();
	}
	/**
	 * 获取所有的参数
	 * @return Map<Integer,Value>
	 */
	public Map<Integer,Value> getAllParam()
	{
		return this.row;
	}
}
