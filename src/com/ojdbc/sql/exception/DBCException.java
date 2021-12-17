/**
 * ClassName: DBCException.java
 * Date: 2017年5月22日
 */
package com.ojdbc.sql.exception;


/**
 * Author: ShaoGaige
 * Description: 异常信息处理
 * Log: 
 */
public class DBCException {
	
    public final static String E_GetConnection = "\u83b7\u53d6\u6570\u636e\u8fde\u63a5\u51fa\u9519\uff01";
	
	public final static String E_LoadJDBC = "\u52a0\u8f7dJDBC\u51fa\u73b0\u5f02\u5e38\uff01";
	
	public final static String E_SQL = "SQL\u6267\u884c\u51fa\u73b0\u5f02\u5e38\uff01";
	
	public final static String E_SQL_RollBack = "SQL\u56de\u6eda\u51fa\u73b0\u5f02\u5e38\uff01";
	
	public final static String E_DataBaseInit = "\u6570\u636e\u5e93\u521d\u59cb\u5316\u51fa\u73b0\u5f02\u5e38\uff01";
	
	public final static String E_ConnectionPoolInit = "\u6570\u636e\u5e93\u8fde\u63a5\u6c60\u521d\u59cb\u5316\u5931\u8d25\uff01";
	
	public final static String E_GetConnectionFromPool = "\u4ece\u6570\u636e\u5e93\u8fde\u63a5\u6c60\u4e2d\u83b7\u53d6\u5bf9\u8c61\u5931\u8d25\uff01";
	
	public final static String E_ReturnObject = "\u5f52\u8fd8\u5bf9\u8c61\u5230\u6570\u636e\u5e93\u8fde\u63a5\u6c60\u4e2d\u5931\u8d25\uff01";
	
	public final static String E_ReadConfig = "\u8bfb\u53d6\u914d\u7f6e\u6587\u4ef6\u51fa\u9519\uff01";
	
	public final static String E_ConnectionSetAutoCommit = "\u0043\u006f\u006e\u006e\u0065\u0063\u0074\u0069\u006f\u006e\u5c5e\u6027\u0041\u0075\u0074\u006f\u0043\u006f\u006d\u006d\u0069\u0074\u8bbe\u7f6e\u51fa\u9519\uff01";
	
	public final static String E_newInstanceDataBase = "\u5229\u7528\u53cd\u5c04\u521b\u5efa\u0044\u0061\u0074\u0061\u0042\u0061\u0073\u0065\u5bf9\u8c61\u51fa\u9519\uff01"; 
	
	public final static String E_ResultSet = "\u0052\u0065\u0073\u0075\u006c\u0074\u0053\u0065\u0074\u904d\u5386\u51fa\u73b0\u95ee\u9898\uff01";
	
	public final static String E_PreparedStatement = "\u0050\u0072\u0065\u0070\u0061\u0072\u0065\u0064\u0053\u0074\u0061\u0074\u0065\u006d\u0065\u006e\u0074\u8bbe\u7f6e\u53c2\u6570\u51fa\u73b0\u95ee\u9898\uff01";
	
	public final static String E_ValidConnection = "\u9a8c\u8bc1\u6570\u636e\u5e93\u8fde\u63a5\u0056\u0061\u006c\u0069\u0064\u0043\u006f\u006e\u006e\u0065\u0063\u0074\u0069\u006f\u006e\u51fa\u73b0\u5f02\u5e38\u002e\u002e\u002e";
	
	/**
	 * 异常信息提示类
	 * @param exceptionType
	 * @param e
	 */
	public static void logException(String exceptionType,Exception e)
	{
		//显示异常信息
		System.out.println(exceptionType);
		System.out.println(e.getMessage());
	}

}
