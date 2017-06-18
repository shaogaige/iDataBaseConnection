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
	
	public final static String E_IO_File_TXT = "\u5199\u5165TXT\u51fa\u73b0\u5f02\u5e38\uff01";
	
	public final static String E_IO_File_Create = "\u6587\u4ef6\u521b\u5efa\u51fa\u73b0\u5f02\u5e38\uff01";
	
	public final static String E_GZIP = "GZIPUtil\u538b\u7f29\u51fa\u73b0\u5f02\u5e38\uff01";
	
	public final static String E_DataBaseInit = "\u6570\u636e\u5e93\u521d\u59cb\u5316\u51fa\u73b0\u5f02\u5e38\uff01";
	
	public final static String E_IO_File_READ = "\u6587\u4ef6\u8bfb\u53d6\u51fa\u73b0\u5f02\u5e38\uff01";
	
	public final static String E_IO_File_NO = "\u8981\u8bfb\u53d6\u7684\u6587\u4ef6\u4e0d\u5b58\u5728\uff01";
	
	public final static String E_IO_File_WRITE = "\u6587\u4ef6\u5199\u5165\u51fa\u73b0\u5f02\u5e38\uff01";
	
	/**
	 * 异常信息提示类
	 * @param exceptionType
	 * @param e
	 */
	public static void throwException(String exceptionType,Exception e)
	{
		//显示异常信息
		System.out.println(e.getMessage());
	}

}
