/**
 * ClassName: OracleDataBase.java
 * Date: 2017年6月6日
 */
package com.ojdbc.sql.database;

import com.ojdbc.sql.ConnectionManager.ConnectionInfo;
import com.ojdbc.sql.core.SQLPreparedParamUtil;
import com.ojdbc.sql.exception.DBCException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.ojdbc.sql.ConnectionManager;
import com.ojdbc.sql.ConnectionObject;
import com.ojdbc.sql.DataBase;
import com.ojdbc.sql.PreparedParam;

/**
 * Author: ShaoGaige
 * Description: Oracle数据库
 * Log: 
 */
public class OracleDataBase extends DataBase {

	public OracleDataBase(ConnectionInfo connInfo) {
		super(connInfo);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int exeSQLInsert(String sql) {
		// TODO Auto-generated method stub
		ConnectionObject conn = ConnectionManager.borrowConnectionObject(connInfo);
		Statement stat = null;
		try 
		{
			stat = conn.getConnection().createStatement();
			//stat.executeUpdate(sql);
			stat.executeUpdate(sql);
			return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DBCException.logException(DBCException.E_SQL, e);
			return 0;
		}
		finally
		{
			if(stat != null)
			{
				try 
				{
					stat.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			ConnectionManager.returnConnectionObject(conn);
		}
	}
	
	@Override
	public int exePreparedSQLInsert(String sql, PreparedParam preparedParam) {
		// TODO Auto-generated method stub
		ConnectionObject conn = ConnectionManager.borrowConnectionObject(connInfo);
		PreparedStatement preStmt = null;
		try 
		{
			preStmt = conn.getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			SQLPreparedParamUtil.setSQLPreparedParam(preStmt, preparedParam);
			preStmt.executeUpdate();
			return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DBCException.logException(DBCException.E_SQL, e);
			return 0;
		}
		finally
		{
			if(preStmt != null)
			{
				try 
				{
					preStmt.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			ConnectionManager.returnConnectionObject(conn);
		}
	}

}
