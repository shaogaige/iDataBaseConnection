/**
 * ClassName: SQLPreparedParamUtil.java
 * Date: 2017年6月7日
 */
package com.ojdbc.sql.core;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ojdbc.sql.PreparedParam;
import com.ojdbc.sql.Value;

/**
 * Author: ShaoGaige
 * Description: SQLPreparedParam解析工具类
 * Log:
 */
public class SQLPreparedParamUtil {
	/**
	 * 设置PreparedParam的参数
	 * @param preStmt
	 * @param preparedParam
	 */
	public static void setSQLPreparedParam(PreparedStatement preStmt,PreparedParam preparedParam)
	{
		if(preparedParam ==  null){
			return;
		}
		int size = preparedParam.getParamCount();
		for(int i=1;i<=size;i++)
		{
			setValue(preStmt,i,preparedParam.getParam(i));
		}
	}
	//设置Value
	private static void setValue(PreparedStatement preStmt,int index,Value value)
	{
		try 
		{
			if(value.isStringValue())
			{
				preStmt.setString(index, value.getString_value());
			}
			else if(value.isIntValue())
			{
				preStmt.setInt(index, value.getInt_value());
			}
			else if(value.isDoubleValue())
			{
				preStmt.setDouble(index, value.getDouble_value());
			}
			else if(value.isBooleanValue())
			{
				preStmt.setBoolean(index, value.getBoolean_value());
			}
			else if(value.isBlobValue())
			{
				preStmt.setBlob(index, value.getBlob_value());
			}
			else if(value.isBytesValue())
			{
				preStmt.setBytes(index, value.getBytes_value());
			}
			else if(value.isLongValue())
			{
				preStmt.setLong(index, value.getLong_value());
			}
			else if(value.isFloatValue())
			{
				preStmt.setFloat(index, value.getFloat_value());
			}
			else if(value.isBigdecimalValue())
			{
				preStmt.setBigDecimal(index, value.getBigdecimal_value());
			}
			else if(value.isByteValue())
			{
				preStmt.setByte(index, value.getByte_value());
			}
			else if(value.isDateValue())
			{
				preStmt.setDate(index, value.getDate_value());
			}
			else if(value.isTimeValue())
			{
				preStmt.setTime(index, value.getTime_value());
			}
			else
			{
				preStmt.setObject(index, value.getObject_value());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
