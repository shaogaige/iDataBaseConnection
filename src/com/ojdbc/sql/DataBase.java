/**
 * ClassName: AbstractDataBase.java
 * Date: 2017年5月27日
 */
package com.ojdbc.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import com.ojdbc.sql.core.ResultSetUtil;
import com.ojdbc.sql.core.SQLPreparedParamUtil;

/**
 * Author: ShaoGaige
 * Description: 抽象数据超类
 * Log: 
 */
public class DataBase implements IDataBase{
	
	//数据库连接
	private ConnectionObject conn = null;
	/**
	 * 构造函数
	 * @param conn
	 * @throws Exception
	 */
	public DataBase(ConnectionObject conn)
	{
		if(conn != null)
		{
			this.conn = conn;
		}
		else
		{
			//throw new Exception("Error: conn is null, DataBase construct fail !!!");
		}
	}
	//销毁对象
	protected void finalize()
	{
		//归还连接对象
		if(conn != null)
		{
			DataBaseManager.returnConnectionObject(conn);
		}
		this.conn = null;
	}
	/**
	 * 获取数据库连接
	 * @return ConnectionObject
	 */
	public ConnectionObject getConn() {
		return this.conn;
	}
	public String getConnectionString(){
		return conn.getKey();	
	}

	/**
	 * 新建表
	 * @param sql
	 * @return boolean
	 */
	@Override
	public boolean exeSQLCreate(String sql)
	{
		try 
		{
			Statement stat = this.conn.getConnection().createStatement();
			stat.execute(sql);
			stat.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
	}
	@Override
	public boolean exePreparedSQLCreate(String sql, PreparedParam preparedParam) {
		// TODO Auto-generated method stub
		try 
		{
			PreparedStatement preStmt = this.conn.getConnection().prepareStatement(sql);
			SQLPreparedParamUtil.setSQLPreparedParam(preStmt, preparedParam);
			preStmt.execute();
			preStmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
	}
	
	@Override
	public SQLResultSet exeSQLSelect(String sql)
	{
		try 
		{
			Statement stat = this.conn.getConnection().createStatement();
			ResultSet rs = stat.executeQuery(sql);
			List<SQLRow> resultSet = ResultSetUtil.getRowList(rs);
			SQLResultSet r = new SQLResultSet(resultSet);
			rs.close();
			stat.close();
			return r;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
	}
	@Override
	public SQLResultSet exePreparedSQLSelect(String sql,PreparedParam preparedParam) {
		// TODO Auto-generated method stub
		try 
		{
			PreparedStatement preStmt = this.conn.getConnection().prepareStatement(sql);
			SQLPreparedParamUtil.setSQLPreparedParam(preStmt, preparedParam);
			ResultSet rs = preStmt.executeQuery();
			List<SQLRow> resultSet = ResultSetUtil.getRowList(rs);
			SQLResultSet r = new SQLResultSet(resultSet);
			rs.close();
			preStmt.close();
			return r;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
	}
	@Override
	public boolean exeSQLDrop(String sql) {
		// TODO Auto-generated method stub
		try 
		{
			Statement stat = this.conn.getConnection().createStatement();
			stat.execute(sql);
			stat.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
	}
	@Override
	public boolean exePreparedSQLDrop(String sql, PreparedParam preparedParam) {
		// TODO Auto-generated method stub
		try 
		{
			PreparedStatement preStmt = this.conn.getConnection().prepareStatement(sql);
			SQLPreparedParamUtil.setSQLPreparedParam(preStmt, preparedParam);
			preStmt.execute();
			preStmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
	}
	@Override
	public boolean exeSQLAlter(String sql) {
		// TODO Auto-generated method stub
		try 
		{
			Statement stat = this.conn.getConnection().createStatement();
			stat.execute(sql);
			stat.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
	}
	@Override
	public boolean exePreparedSQLAlter(String sql, PreparedParam preparedParam) {
		// TODO Auto-generated method stub
		try 
		{
			PreparedStatement preStmt = this.conn.getConnection().prepareStatement(sql);
			SQLPreparedParamUtil.setSQLPreparedParam(preStmt, preparedParam);
			preStmt.execute();
			preStmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
	}
	@Override
	public boolean exeSQLUpdate(String sql) {
		// TODO Auto-generated method stub
		try 
		{
			Statement stat = this.conn.getConnection().createStatement();
			stat.executeUpdate(sql);
			stat.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
	}
	@Override
	public boolean exePreparedSQLUpdate(String sql, PreparedParam preparedParam) {
		// TODO Auto-generated method stub
		try 
		{
			PreparedStatement preStmt = this.conn.getConnection().prepareStatement(sql);
			SQLPreparedParamUtil.setSQLPreparedParam(preStmt, preparedParam);
			preStmt.executeUpdate();
			preStmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
	}
	@Override
	public int exeSQLInsert(String sql) {
		// TODO Auto-generated method stub
		try 
		{
			Statement stat = this.conn.getConnection().createStatement();
			//stat.executeUpdate(sql);
			int autoGeneratedKeys = 0;
			stat.executeUpdate(sql, autoGeneratedKeys);
			stat.close();
			return autoGeneratedKeys;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return 0;
		}
	}
	@Override
	public int exePreparedSQLInsert(String sql, PreparedParam preparedParam) {
		// TODO Auto-generated method stub
		try 
		{
			PreparedStatement preStmt = this.conn.getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			SQLPreparedParamUtil.setSQLPreparedParam(preStmt, preparedParam);
			preStmt.executeUpdate();
			ResultSet rs = preStmt.getGeneratedKeys();
			int num = 0;
			if(rs.next())
			{
				num = rs.getInt(1);
			}
			preStmt.close();
			return num;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return 0;
		}
	}
	@Override
	public boolean exeSQLDelete(String sql) {
		// TODO Auto-generated method stub
		try 
		{
			Statement stat = this.conn.getConnection().createStatement();
			stat.executeUpdate(sql);
			stat.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
	}
	@Override
	public boolean exePreparedSQLDelete(String sql, PreparedParam preparedParam) {
		// TODO Auto-generated method stub
		try 
		{
			PreparedStatement preStmt = this.conn.getConnection().prepareStatement(sql);
			SQLPreparedParamUtil.setSQLPreparedParam(preStmt, preparedParam);
			preStmt.executeUpdate();
			preStmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
	}
	@Override
	public boolean exeBatchSQL(List<String> sqls) {
		// TODO Auto-generated method stub
		try
		{
			Statement stat = this.conn.getConnection().createStatement();
			if(sqls!=null && sqls.size()>0)
			{
				int size = sqls.size();
				for(int i=0;i<size;i++)
				{
					stat.addBatch(sqls.get(i));
				}
				stat.executeBatch();
			    stat.close();
			    return true;
			}
			else
			{
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean exePreparedBatchSQL(String sql,List<PreparedParam> preparedParams) {
		// TODO Auto-generated method stub
		try 
		{
			PreparedStatement preStmt = this.conn.getConnection().prepareStatement(sql);
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
				preStmt.close();
				return true;
			}
			else
			{
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean exeMixedBatchSQL(MixedBatchSQL mixedBatchSQL) {
		// TODO Auto-generated method stub
		if(mixedBatchSQL != null)
		{
			String pSQL = mixedBatchSQL.getPreparedSQL();
			try 
			{
				PreparedStatement preStmt = this.conn.getConnection().prepareStatement(pSQL);
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
					preStmt.close();
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	@Override
	public boolean exeTransactionSQL(List<String> sqls) {
		// TODO Auto-generated method stub
		try 
		{
			if(sqls!=null && sqls.size()>0)
			{
				this.conn.getConnection().setAutoCommit(false);
			    Statement stat = this.conn.getConnection().createStatement();
			    int size = sqls.size();
			    for(int i=0;i<size;i++)
			    {
			    	stat.executeUpdate(sqls.get(i));
			    }
			    stat.close();
			    this.conn.getConnection().commit();
			    return true;
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.conn.getConnection().rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return false;
		}
		finally
		{
			try {
				this.conn.getConnection().setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	@Override
	public boolean exeTransactionPreparedSQL(Map<String, PreparedParam> psqls) {
		// TODO Auto-generated method stub
		try 
		{
			if(psqls!=null && psqls.size()>0)
			{
				this.conn.getConnection().setAutoCommit(false);
				for(String sql:psqls.keySet())
				{
					PreparedParam param = psqls.get(sql);
					if(param == null)
					{
						this.exeSQLUpdate(sql);
					}
					else
					{
						this.exePreparedSQLUpdate(sql, param);
					}
				}
				this.conn.getConnection().commit();
				return true;
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
		finally
		{
			try {
				this.conn.getConnection().setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
