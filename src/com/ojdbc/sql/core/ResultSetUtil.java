/**
 * ClassName: ResultSetUtil.java
 * Date: 2017年6月6日
 */
package com.ojdbc.sql.core;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ojdbc.sql.SQLResultSet;
import com.ojdbc.sql.SQLRow;
import com.ojdbc.sql.Value;
import com.ojdbc.sql.exception.DBCException;

/**
 * Author: ShaoGaige
 * Description: ResultSet解析工具类
 * Log:
 */
public class ResultSetUtil {
	/**
	 * 将ResultSet对象变成按照行存储的SQLResultSet对象
	 * @param rs
	 * @return SQLResultSet
	 */
	public static SQLResultSet getSQLResultSet(ResultSet rs)
	{
		try 
		{
			ResultSetMetaData resultMetaData = rs.getMetaData();
			Map<String,Integer> columnNames = new LinkedHashMap<String,Integer>();
			int size = resultMetaData.getColumnCount();
			//获取所有列名称
			for(int i=1;i<=size;i++)
			{
				columnNames.put(resultMetaData.getColumnName(i),resultMetaData.getColumnType(i));
			}
			List<SQLRow> resultSet = new ArrayList<SQLRow>();
			while(rs.next())
			{
				Map<String,Value> keyValue = new LinkedHashMap<String,Value>();
				//按照行整理每个字段的数据
				for(String key:columnNames.keySet())
				{
					int type = columnNames.get(key);
					Value value = getValue(key,type,rs);
					keyValue.put(key, value);
				}
				SQLRow row = new SQLRow(keyValue);
				resultSet.add(row);
			}
			
			SQLResultSet r = new SQLResultSet(resultSet);
			return r;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DBCException.logException(DBCException.E_ResultSet, e);
			return null;
		}
	}
	/**
	 * 将ResultSet对象变成按照行存储的SQLResultSet对象，支持分页
	 * @param rs start count
	 * @return SQLResultSet
	 */
	public static SQLResultSet getSQLResultSet(ResultSet rs, int start, int count)
	{
		try 
		{
			ResultSetMetaData resultMetaData = rs.getMetaData();
			Map<String,Integer> columnNames = new LinkedHashMap<String,Integer>();
			int size = resultMetaData.getColumnCount();
			//获取所有列名称
			for(int i=1;i<=size;i++)
			{
				columnNames.put(resultMetaData.getColumnName(i),resultMetaData.getColumnType(i));
			}
			List<SQLRow> resultSet = new ArrayList<SQLRow>();
			int current = 0,cou = 1;
			while(rs.next())
			{
				current++;
				if(current < start)
				{
					continue;
				}
				Map<String,Value> keyValue = new LinkedHashMap<String,Value>();
				//按照行整理每个字段的数据
				for(String key:columnNames.keySet())
				{
					int type = columnNames.get(key);
					Value value = getValue(key,type,rs);
					keyValue.put(key, value);
				}
				SQLRow row = new SQLRow(keyValue);
				resultSet.add(row);
				
				cou++;
				if(cou>count)
				{
					break;
				}
			}
			
			SQLResultSet r = new SQLResultSet(resultSet);
			return r;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DBCException.logException(DBCException.E_ResultSet, e);
			return null;
		}
	}
	//获取Value
	public static Value getValue(String columnName,int type,ResultSet rs)
	{
		Value value = new Value(type);
		try 
		{
			switch(type)
			{
			   case Types.CHAR:
			   case Types.VARCHAR:
			   case Types.LONGVARCHAR:
			   case Types.NCHAR:
			   case Types.NVARCHAR:
			   case Types.LONGNVARCHAR:
			       value.setString_value(rs.getString(columnName));
			       break;
			   case Types.FLOAT:
			   case Types.DOUBLE:
				   value.setDouble_value(rs.getDouble(columnName));
				   break;
			   case Types.INTEGER:
			   case Types.SMALLINT:
				   value.setInt_value(rs.getInt(columnName));
				   value.setLong_value(rs.getLong(columnName));
				   break;
			   case Types.BINARY:
			   case Types.VARBINARY:
			   case Types.LONGVARBINARY:
				   value.setBytes_value(rs.getBytes(columnName));
				   break;
			   case Types.BLOB:
				   value.setBlob_value(rs.getBlob(columnName));
				   value.setBytes_value(rs.getBytes(columnName));
				   break;
			   case Types.CLOB:
				   value.setClob_value(rs.getClob(columnName));
				   value.setString_value(rs.getString(columnName));
				   break;
			   case Types.NUMERIC:
			   case Types.DECIMAL:
				   value.setBigdecimal_value(rs.getBigDecimal(columnName));
				   value.setDouble_value(rs.getDouble(columnName));
				   break;
			   case Types.BIT:
			   case Types.BOOLEAN:
				   value.setBoolean_value(rs.getBoolean(columnName));
				   break;
			   case Types.TINYINT:
				   value.setByte_value(rs.getByte(columnName));
				   break;
			   case Types.BIGINT:
				   value.setLong_value(rs.getLong(columnName));
				   break;
			   case Types.REAL:
				   value.setFloat_value(rs.getFloat(columnName));
				   value.setDouble_value(rs.getDouble(columnName));
				   break;
			   case Types.DATE:
				   value.setDate_value(rs.getDate(columnName));
				   break;
			   case Types.TIME:
			   case Types.TIMESTAMP:
				   value.setTime_value(rs.getTime(columnName));
				   break;
			   default:
				   value.setObject_value(rs.getObject(columnName));
				   value.setString_value(rs.getString(columnName));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DBCException.logException(DBCException.E_ResultSet, e);
			return null;
		}
		return value;
	}
}
