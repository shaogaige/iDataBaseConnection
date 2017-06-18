/**
 * ClassName: IDataBase.java
 * Date: 2017年5月31日
 */
package com.ojdbc.sql;

import java.util.List;

/**
 * Author: ShaoGaige
 * Description: 数据库接口
 * Log: 
 */
public interface IDataBase {
	/**
	 * 执行Create语句
	 * @param sql
	 * @return boolean
	 */
	public boolean exeSQLCreate(String sql);
	/**
	 * 执行Prepared Create语句
	 * @param sql
	 * @param preparedParam
	 * @return boolean
	 */
	public boolean exePreparedSQLCreate(String sql,PreparedParam preparedParam);
	/**
	 * 执行Drop语句
	 * @param sql
	 * @return boolean
	 */
	public boolean exeSQLDrop(String sql);
	/**
	 * 执行Prepared Drop语句
	 * @param sql
	 * @param preparedParam
	 * @return boolean
	 */
	public boolean exePreparedSQLDrop(String sql,PreparedParam preparedParam);
	/**
	 * 执行Alter语句
	 * @param sql
	 * @return boolean
	 */
	public boolean exeSQLAlter(String sql);
	/**
	 * 执行Prepared Alter语句
	 * @param sql
	 * @param preparedParam
	 * @return boolean
	 */
	public boolean exePreparedSQLAlter(String sql,PreparedParam preparedParam);
	/**
	 * 执行Select语句
	 * @param sql
	 * @return SQLResultSet
	 */
	public SQLResultSet exeSQLSelect(String sql);
	/**
	 * 执行Prepared Select语句
	 * @param sql
	 * @param preparedParam
	 * @return SQLResultSet
	 */
	public SQLResultSet exePreparedSQLSelect(String sql,PreparedParam preparedParam);
	/**
	 * 执行Update语句
	 * @param sql
	 * @return boolean
	 */
	public boolean exeSQLUpdate(String sql);
	/**
	 * 执行Prepared Update语句
	 * @param sql
	 * @param preparedParam
	 * @return boolean
	 */
	public boolean exePreparedSQLUpdate(String sql,PreparedParam preparedParam);
	/**
	 * 执行Insert语句
	 * @param sql
	 * @return int
	 */
	public int exeSQLInsert(String sql);
	/**
	 * 执行Prepared Insert语句
	 * @param sql
	 * @param preparedParam
	 * @return int
	 */
	public int exePreparedSQLInsert(String sql,PreparedParam preparedParam);
	/**
	 * 执行Delete语句
	 * @param sql
	 * @return boolean
	 */
	public boolean exeSQLDelete(String sql);
	/**
	 * 执行Prepared Delete语句
	 * @param sql
	 * @param preparedParam
	 * @return boolean
	 */
	public boolean exePreparedSQLDelete(String sql,PreparedParam preparedParam);
	/**
	 * 批量执行SQL语句
	 * @param sqls
	 * @return boolean
	 */
	public boolean exeBatchSQL(List<String> sqls);
	/**
	 * 批量执行Prepared SQL语句
	 * @param sql
	 * @param preparedParams
	 * @return boolean
	 */
	public boolean exePreparedBatchSQL(String sql,List<PreparedParam> preparedParams);
	/**
	 * 批量执行混合SQL语句
	 * @param mixedBatchSQL
	 * @return boolean
	 */
	public boolean exeMixedBatchSQL(MixedBatchSQL mixedBatchSQL);

}
