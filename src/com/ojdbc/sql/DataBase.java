/**
 * ClassName: AbstractDataBase.java
 * Date: 2017年5月27日
 */
package com.ojdbc.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import com.ojdbc.sql.ConnectionManager.ConnectionInfo;
import com.ojdbc.sql.core.ResultSetUtil;
import com.ojdbc.sql.core.SQLPreparedParamUtil;
import com.ojdbc.sql.exception.DBCException;

/**
 * Author: ShaoGaige
 * Description: 抽象数据超类
 * Log: 
 */
public class DataBase implements IDataBase{
	
	//数据库连接
	protected ConnectionInfo connInfo = null;
	/**
	 * 构造函数
	 * @param conn
	 * @throws Exception
	 */
	public DataBase(ConnectionInfo connInfo)
	{
		if(connInfo != null)
		{
			this.connInfo = connInfo;
		}
		else
		{
			try
			{
				throw new Exception("Error: conninfo is null, DataBase construct fail !!!");
			}catch(Exception e){
				e.printStackTrace();
				DBCException.logException(DBCException.E_DataBaseInit, e);
			}
		}
	}
	/**
	 * 获取数据库连接字符串信息
	 * @return String
	 */
	public String getConnectionString(){
		return connInfo.getKey();	
	}

	/**
	 * 新建表
	 * @param sql
	 * @return boolean
	 */
	@Override
	public boolean exeSQLCreate(String sql)
	{
		ConnectionObject conn = ConnectionManager.borrowConnectionObject(connInfo);
		Statement stat = null;
		try 
		{
			stat = conn.getConnection().createStatement();
			stat.execute(sql);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DBCException.logException(DBCException.E_SQL, e);
			return false;
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
	public boolean exePreparedSQLCreate(String sql, PreparedParam preparedParam) {
		// TODO Auto-generated method stub
		ConnectionObject conn = ConnectionManager.borrowConnectionObject(connInfo);
		PreparedStatement preStmt = null;
		try 
		{
			preStmt = conn.getConnection().prepareStatement(sql);
			SQLPreparedParamUtil.setSQLPreparedParam(preStmt, preparedParam);
			preStmt.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DBCException.logException(DBCException.E_SQL, e);
			return false;
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
	
	@Override
	public SQLResultSet exeSQLSelect(String sql)
	{
		ConnectionObject conn = ConnectionManager.borrowConnectionObject(connInfo);
		Statement stat = null;
		ResultSet rs = null;
		try 
		{
			stat = conn.getConnection().createStatement();
			rs = stat.executeQuery(sql);
			SQLResultSet r = ResultSetUtil.getSQLResultSet(rs);
			
			
			return r;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DBCException.logException(DBCException.E_SQL, e);
			return null;
		}
		finally
		{
			try 
			{
				if(rs != null)
				{
					rs.close();
				}
				if(stat != null)
				{
					stat.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ConnectionManager.returnConnectionObject(conn);
		}
	}
	@Override
	public SQLResultSet exePreparedSQLSelect(String sql,PreparedParam preparedParam) {
		// TODO Auto-generated method stub
		ConnectionObject conn = ConnectionManager.borrowConnectionObject(connInfo);
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		try 
		{
			preStmt = conn.getConnection().prepareStatement(sql);
			SQLPreparedParamUtil.setSQLPreparedParam(preStmt, preparedParam);
			rs = preStmt.executeQuery();
			SQLResultSet r = ResultSetUtil.getSQLResultSet(rs);
			return r;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DBCException.logException(DBCException.E_SQL, e);
			return null;
		}
		finally
		{
			try 
			{
				if(rs != null)
				{
					rs.close();
				}
				if(preStmt != null)
				{
					preStmt.close();
				}
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ConnectionManager.returnConnectionObject(conn);
		}
	}
	@Override
	public boolean exeSQLDrop(String sql) {
		// TODO Auto-generated method stub
		ConnectionObject conn = ConnectionManager.borrowConnectionObject(connInfo);
		Statement stat = null;
		try 
		{
			stat = conn.getConnection().createStatement();
			stat.execute(sql);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DBCException.logException(DBCException.E_SQL, e);
			return false;
		}
		finally
		{
			if(stat != null)
			{
				try {
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
	public boolean exePreparedSQLDrop(String sql, PreparedParam preparedParam) {
		// TODO Auto-generated method stub
		ConnectionObject conn = ConnectionManager.borrowConnectionObject(connInfo);
		PreparedStatement preStmt = null;
		try 
		{
			preStmt = conn.getConnection().prepareStatement(sql);
			SQLPreparedParamUtil.setSQLPreparedParam(preStmt, preparedParam);
			preStmt.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DBCException.logException(DBCException.E_SQL, e);
			return false;
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
	@Override
	public boolean exeSQLAlter(String sql) {
		// TODO Auto-generated method stub
		ConnectionObject conn = ConnectionManager.borrowConnectionObject(connInfo);
		Statement stat = null;
		try 
		{
			stat = conn.getConnection().createStatement();
			stat.execute(sql);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DBCException.logException(DBCException.E_SQL, e);
			return false;
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
	public boolean exePreparedSQLAlter(String sql, PreparedParam preparedParam) {
		// TODO Auto-generated method stub
		ConnectionObject conn = ConnectionManager.borrowConnectionObject(connInfo);
		PreparedStatement preStmt = null;
		try 
		{
			preStmt = conn.getConnection().prepareStatement(sql);
			SQLPreparedParamUtil.setSQLPreparedParam(preStmt, preparedParam);
			preStmt.execute();
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DBCException.logException(DBCException.E_SQL, e);
			return false;
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
	@Override
	public boolean exeSQLUpdate(String sql) {
		// TODO Auto-generated method stub
		ConnectionObject conn = ConnectionManager.borrowConnectionObject(connInfo);
		Statement stat = null;
		try 
		{
			stat = conn.getConnection().createStatement();
			stat.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DBCException.logException(DBCException.E_SQL, e);
			return false;
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
	public boolean exePreparedSQLUpdate(String sql, PreparedParam preparedParam) {
		// TODO Auto-generated method stub
		ConnectionObject conn = ConnectionManager.borrowConnectionObject(connInfo);
		PreparedStatement preStmt = null;
		try 
		{
			preStmt = conn.getConnection().prepareStatement(sql);
			SQLPreparedParamUtil.setSQLPreparedParam(preStmt, preparedParam);
			preStmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DBCException.logException(DBCException.E_SQL, e);
			return false;
		}
		finally
		{
			if(preStmt != null)
			{
				try 
				{
					preStmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			ConnectionManager.returnConnectionObject(conn);
		}
	}
	@Override
	public int exeSQLInsert(String sql) {
		// TODO Auto-generated method stub
		ConnectionObject conn = ConnectionManager.borrowConnectionObject(connInfo);
		Statement stat = null;
		ResultSet rs = null;
		try 
		{
			stat = conn.getConnection().createStatement();
			//stat.executeUpdate(sql);
			int autoGeneratedKeys = 0;
			stat.executeUpdate(sql);
			rs = stat.getGeneratedKeys();
			if(rs.next())
			{
				autoGeneratedKeys = rs.getInt(1);
			}
			
			return autoGeneratedKeys;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DBCException.logException(DBCException.E_SQL, e);
			return 0;
		}
		finally
		{
			try 
			{
				if(rs != null)
				{
					rs.close();
				}
				if(stat != null)
				{
					stat.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ConnectionManager.returnConnectionObject(conn);
		}
	}
	@Override
	public int exePreparedSQLInsert(String sql, PreparedParam preparedParam) {
		// TODO Auto-generated method stub
		ConnectionObject conn = ConnectionManager.borrowConnectionObject(connInfo);
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		try 
		{
			preStmt = conn.getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			SQLPreparedParamUtil.setSQLPreparedParam(preStmt, preparedParam);
			preStmt.executeUpdate();
			rs = preStmt.getGeneratedKeys();
			int num = 0;
			if(rs.next())
			{
				num = rs.getInt(1);
			}
			return num;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DBCException.logException(DBCException.E_SQL, e);
			return 0;
		}
		finally
		{
			try 
			{
				if(rs != null)
				{
					rs.close();
				}
				if(preStmt != null)
				{
					preStmt.close();
				}
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ConnectionManager.returnConnectionObject(conn);
		}
	}
	@Override
	public boolean exeSQLDelete(String sql) {
		// TODO Auto-generated method stub
		ConnectionObject conn = ConnectionManager.borrowConnectionObject(connInfo);
		Statement stat = null;
		try 
		{
			stat = conn.getConnection().createStatement();
			stat.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DBCException.logException(DBCException.E_SQL, e);
			return false;
		}
		finally
		{
			if(stat != null)
			{
				try
				{
					stat.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			ConnectionManager.returnConnectionObject(conn);
		}
	}
	@Override
	public boolean exePreparedSQLDelete(String sql, PreparedParam preparedParam) {
		// TODO Auto-generated method stub
		ConnectionObject conn = ConnectionManager.borrowConnectionObject(connInfo);
		PreparedStatement preStmt = null;
		try 
		{
			preStmt = conn.getConnection().prepareStatement(sql);
			SQLPreparedParamUtil.setSQLPreparedParam(preStmt, preparedParam);
			preStmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DBCException.logException(DBCException.E_SQL, e);
			return false;
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
	@Override
	public boolean exeBatchSQL(List<String> sqls) {
		// TODO Auto-generated method stub
		ConnectionObject conn = ConnectionManager.borrowConnectionObject(connInfo);
		Statement stat = null;
		try
		{
			stat = conn.getConnection().createStatement();
			if(sqls!=null && sqls.size()>0)
			{
				int size = sqls.size();
				for(int i=0;i<size;i++)
				{
					stat.addBatch(sqls.get(i));
				}
				stat.executeBatch();
			    return true;
			}
			else
			{
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DBCException.logException(DBCException.E_SQL, e);
			return false;
		}
		finally
		{
			if(stat != null)
			{
				try 
				{
					stat.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			ConnectionManager.returnConnectionObject(conn);
		}
	}
	@Override
	public boolean exePreparedBatchSQL(String sql,List<PreparedParam> preparedParams) {
		// TODO Auto-generated method stub
		ConnectionObject conn = ConnectionManager.borrowConnectionObject(connInfo);
		PreparedStatement preStmt = null;
		try 
		{
			preStmt = conn.getConnection().prepareStatement(sql);
			if(preparedParams!=null && preparedParams.size()>0)
			{
				int size = preparedParams.size();
				for(int i=0;i<size;i++)
				{
					PreparedParam preparedParam = preparedParams.get(i);
					SQLPreparedParamUtil.setSQLPreparedParam(preStmt, preparedParam);
					preStmt.addBatch();
				}
				preStmt.executeBatch();
				return true;
			}
			else
			{
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DBCException.logException(DBCException.E_SQL, e);
			return false;
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
	@Override
	public boolean exeMixedBatchSQL(MixedBatchSQL mixedBatchSQL) {
		// TODO Auto-generated method stub
		ConnectionObject conn = ConnectionManager.borrowConnectionObject(connInfo);
		if(mixedBatchSQL != null)
		{
			String pSQL = mixedBatchSQL.getPreparedSQL();
			PreparedStatement preStmt = null;
			try 
			{
				preStmt = conn.getConnection().prepareStatement(pSQL);
				List<PreparedParam> preparedParams = mixedBatchSQL.getPreparedParams();
				if(preparedParams!=null && preparedParams.size()>0)
				{
					int size = preparedParams.size();
					for(int i=0;i<size;i++)
					{
						PreparedParam preparedParam = preparedParams.get(i);
						SQLPreparedParamUtil.setSQLPreparedParam(preStmt, preparedParam);
						preStmt.addBatch();
					}
					int size2 = mixedBatchSQL.getStaticSQLCount();
					for(int j=0;j<size2;j++)
					{
						preStmt.addBatch(mixedBatchSQL.getStaticSQL(j));
					}
					preStmt.executeBatch();
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				DBCException.logException(DBCException.E_SQL, e);
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
		return false;
	}
	@Override
	public boolean exeTransactionSQL(List<String> sqls) {
		// TODO Auto-generated method stub
		ConnectionObject conn = ConnectionManager.borrowConnectionObject(connInfo);
		Statement stat = null;
		try 
		{
			if(sqls!=null && sqls.size()>0)
			{
				conn.getConnection().setAutoCommit(false);
			    stat = conn.getConnection().createStatement();
			    int size = sqls.size();
			    for(int i=0;i<size;i++)
			    {
			    	stat.executeUpdate(sqls.get(i));
			    }
			    
			    conn.getConnection().commit();
			    return true;
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DBCException.logException(DBCException.E_SQL, e);
			try {
				conn.getConnection().rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				DBCException.logException(DBCException.E_SQL_RollBack, e);
			}
			return false;
		}
		finally
		{
			try 
			{
				if(stat != null)
				{
					stat.close();
				}
				conn.getConnection().setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				DBCException.logException(DBCException.E_ConnectionSetAutoCommit, e);
			}
			finally
			{
				ConnectionManager.returnConnectionObject(conn);
			}
		}
		
		
	}
	@Override
	public boolean exeTransactionPreparedSQL(Map<String, PreparedParam> psqls) {
		// TODO Auto-generated method stub
		ConnectionObject conn = ConnectionManager.borrowConnectionObject(connInfo);
		try 
		{
			if(psqls!=null && psqls.size()>0)
			{
				conn.getConnection().setAutoCommit(false);
				for(String sql:psqls.keySet())
				{
					PreparedParam param = psqls.get(sql);
					if(param == null)
					{
						boolean f = exeSQLUpdate(sql);
						if(!f)
						{
							return false;
						}
					}
					else
					{
						boolean f = exePreparedSQLUpdate(sql, param);
						if(!f)
						{
							return false;
						}
					}
				}
				conn.getConnection().commit();
				return true;
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DBCException.logException(DBCException.E_SQL, e);
			try {
				conn.getConnection().rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				DBCException.logException(DBCException.E_SQL_RollBack, e);
			}
			return false;
		}
		finally
		{
			try {
				conn.getConnection().setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				DBCException.logException(DBCException.E_ConnectionSetAutoCommit, e);
			}
			finally
			{
				ConnectionManager.returnConnectionObject(conn);
			}
		}
		
	}
	@Override
	public ResultSetMetaData getMetaData(String sql) {
		// TODO Auto-generated method stub
		ConnectionObject conn = ConnectionManager.borrowConnectionObject(connInfo);
		Statement stat = null;
		ResultSet rs = null;
		try 
		{
			stat = conn.getConnection().createStatement();
			rs = stat.executeQuery(sql);
			return rs.getMetaData();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DBCException.logException(DBCException.E_SQL, e);
			return null;
		}
		finally
		{
			try 
			{
				if(rs != null)
				{
					rs.close();
				}
				if(stat != null)
				{
					stat.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			
			ConnectionManager.returnConnectionObject(conn);
		}
	}

}
