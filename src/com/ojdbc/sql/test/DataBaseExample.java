/**
 * ClassName: DataBaseExample.java
 * Date: 2017年6月24日
 */
package com.ojdbc.sql.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.ojdbc.sql.ConnectionManager;
import com.ojdbc.sql.ConnectionObject;
import com.ojdbc.sql.DataBase;
import com.ojdbc.sql.DataBaseEnum;
import com.ojdbc.sql.DataBaseManager;
import com.ojdbc.sql.MixedBatchSQL;
import com.ojdbc.sql.PreparedParam;
import com.ojdbc.sql.SQLColumn;
import com.ojdbc.sql.SQLResultSet;
import com.ojdbc.sql.SQLRow;
import com.ojdbc.sql.Value;

/**
 * Author: ShaoGaige
 * Description: 测试示例类
 * Log: 
 */
public class DataBaseExample {

	@Test
	public void SQliteDataBaseTest1() {
		
		DataBaseEnum type = DataBaseEnum.SQSLITE;
		String dataBaseURL = "jdbc:sqlite://E:/shaogaige/iNote/iNoteRun/data/iNoteData.note";
		/**
		 *   [Oracle: jdbc:oracle:thin:@127.0.0.1:1521:orcl]
	     *   [SQLServer: jdbc:sqlserver://127.0.0.1:1433;DatabaseName=test]
	     *   [MySQL: jdbc:mysql://127.0.0.1:3306/test]
	     *   [PostgreSQL: jdbc:postgresql://127.0.0.1:5432/postgis]
	     *   [SQLite: jdbc:sqlite://d:/test.db]
	     *   [Mongo: jdbc:mongo://127.0.0.1:29847/test]
	     *   [Access: e://student.mdb]
		 */
		String userName = "";
		String passWord = "";
		DataBase sqlite = DataBaseManager.getDataBase(type, dataBaseURL, userName, passWord);
		
		String sql = "select * from noteinfo";
        SQLResultSet r = sqlite.exeSQLSelect(sql);
        //按行访问
        int size = r.getRowNum();
        System.out.println("返回记录数: "+size);
        for(int i=0;i<size;i++)
        {
        	SQLRow row = r.getRow(i);
        	Value v = row.getValue("title");
        	String s = v.getString_value();
        	System.out.println(s);
        	
        }
        //按列访问
        SQLColumn col = r.getColumn("uploadperson");
        List<Value> values = col.getValues();
        System.out.println(values.size());
        assertEquals("shaogaige",values.get(0).getString_value());

	}
	
	@Test
	public void SQliteDataBaseTest2() {
		
		DataBaseEnum type = DataBaseEnum.SQSLITE;
		String dataBaseURL = "jdbc:sqlite://E:/shaogaige/iNote/iNoteRun/data/iNoteData.note";
		String userName = "";
		String passWord = "";
		DataBase sqlite = DataBaseManager.getDataBase(type, dataBaseURL, userName, passWord);
		
		String sql1 = "drop table if exists testinfo";
        boolean f1 = sqlite.exeSQLDrop(sql1);
        assertTrue(f1);
        
        String sql2 = "create table testinfo(nid INTEGER PRIMARY KEY autoincrement,title TEXT,num INTEGER)";
        boolean f2 = sqlite.exeSQLCreate(sql2);
        assertTrue(f2);
        
        String sql3 = "insert into testinfo (title,num) values('insert1',1)";
        int id = sqlite.exeSQLInsert(sql3);
        System.out.println("id:"+id);
        
        String sql4 = "insert into testinfo (title,num) values(?,?)";
        PreparedParam preparedParam = new PreparedParam();
        Value value1 = new Value();
        value1.setString_value("insert2");
        preparedParam.addParam(1, value1);
        Value value2 = new Value();
        value2.setInt_value(2);
        preparedParam.addParam(2, value2);
        int id2 = sqlite.exePreparedSQLInsert(sql4,preparedParam);
        System.out.println("id:"+id2);
        
        String sql5 = "update testinfo set title='update1' where num=1";
        boolean f5 = sqlite.exeSQLUpdate(sql5);
        assertTrue(f5);
        
        String sql6 = "update testinfo set title=? where num=?";
        PreparedParam preparedParam2 = new PreparedParam();
        Value value3 = new Value();
        value3.setString_value("update2");
        preparedParam2.addParam(1, value3);
        Value value4 = new Value();
        value4.setInt_value(2);
        preparedParam2.addParam(2, value4);
        boolean f6 = sqlite.exePreparedSQLUpdate(sql6, preparedParam2);
        assertTrue(f6);
        
        String sql7 = "select * from testinfo where num=1";
        SQLResultSet r = sqlite.exeSQLSelect(sql7);
        int size = r.getRowNum();
        System.out.println("返回记录数: "+size);
        for(int i=0;i<size;i++)
        {
        	SQLRow row = r.getRow(i);
        	Value v = row.getValue("title");
        	String s = v.getString_value();
        	System.out.println(s);
        	assertEquals("update1",s);
        }
        
        String sql8 = "select * from testinfo where num=?";
        PreparedParam preparedParam3 = new PreparedParam();
        Value value5 = new Value();
        value5.setInt_value(2);
        preparedParam3.addParam(1, value5);
        SQLResultSet r2 = sqlite.exePreparedSQLSelect(sql8, preparedParam3);
        int size2 = r2.getRowNum();
        System.out.println("返回记录数: "+size2);
        for(int i=0;i<size2;i++)
        {
        	SQLRow row = r2.getRow(i);
        	Value v = row.getValue("title");
        	String s = v.getString_value();
        	System.out.println(s);
        	assertEquals("update2",s);
        }
        
        String sql9 = "delete from testinfo where num=1";
        boolean f9 = sqlite.exeSQLDelete(sql9);
        assertTrue(f9);
        
        String sql10 = "delete from testinfo where num=?";
        PreparedParam preparedParam4 = new PreparedParam();
        Value value6 = new Value();
        value6.setInt_value(2);
        preparedParam4.addParam(1, value6);
        boolean f10 = sqlite.exePreparedSQLDelete(sql10, preparedParam4);
        assertTrue(f10);
        
        Map<String, PreparedParam> psqls = new HashMap<String, PreparedParam>();
        String sql11 = "insert into testinfo (title,num) values('tinsert1',3)";
        String sql12 = "insert into testinfo (title,num) values(?,?)";
        PreparedParam preparedParam5 = new PreparedParam();
        Value value7 = new Value();
        value7.setString_value("tinsert2");
        preparedParam5.addParam(1, value7);
        Value value8 = new Value();
        value8.setInt_value(4);
        preparedParam5.addParam(2, value8);
        psqls.put(sql11, null);
        psqls.put(sql12, preparedParam5);
        boolean t = sqlite.exeTransactionPreparedSQL(psqls);
        assertTrue(t);
        
        MixedBatchSQL mixedBatchSQL = new MixedBatchSQL();
        //mixedBatchSQL.addStaticBatchSQL(sql3); sqlite not support
        List<PreparedParam> preparedParams = new ArrayList<PreparedParam>();
        preparedParams.add(preparedParam);
        mixedBatchSQL.setPreparedBatchSQL(sql4, preparedParams);
        //
        //sqlite.exePreparedBatchSQL(sql4, preparedParams);
        //sqlite.exeBatchSQL(sqls);
        //
        boolean fx = sqlite.exeMixedBatchSQL(mixedBatchSQL);
        assertTrue(fx);
	}
	
	@Test
	public void ConnectionObjectTest1() throws SQLException {
		
		DataBaseEnum type = DataBaseEnum.SQSLITE;
		String dataBaseURL = "jdbc:sqlite://E:/shaogaige/iNote/iNoteRun/data/iNoteData.note";
		String userName = "";
		String passWord = "";
		
		//***************************************************************************************
		//this operation will take a while,and make you get connection faster.
		//It should execute in system-initialization,but not necessary.
		//根据配置初始化，这个操作需要消耗一定的时间，建议放在系统初始化。执行此操作后，会加速第一次获取数据库连接的耗时。
		//ConnectionManager.initConnectionPoolByConfigure(type, dataBaseURL, userName, passWord);
		//***************************************************************************************
		
		ConnectionObject connobj = ConnectionManager.borrowConnectionObject(type, dataBaseURL, userName, passWord);
		Connection conn = connobj.getConnection();
		conn.prepareStatement("select * from .....");
		//this Connection is JDBC's Connection Object 
		//your code 
		//you can use JDBC's all operation
		
		ConnectionManager.returnConnectionObject(connobj);
	}
	
	//Time always leaves the best thing for you at the end.
	//*****************************************************************************************
	//如何扩展新的数据库?
	//how to extend a new database ?
	//------------------------------------------------------
	//1.实现IConnection接口
	//1。implements IConnection interface
	//2.继承DataBase类
	//2. extends DataBase,you can override all methods.
	//3.新建枚举类，实现IDataSource接口
	//3. create a new enum class implements IDataSource.
	//------------------------------------------------------
	// When you did this, you can use it the same way as others database.
	//------------------------------------------------------
	// see mysql example:MySQLDataBaseConnection.java/MySQLDataBase.java/DataBaseEnum.java
	//------------------------------------------------------
	// if you have any questions, please send email to shaogaige@126.com
	//------------------------------------------------------

}
