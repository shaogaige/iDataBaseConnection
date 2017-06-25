/**
 * ClassName: DataBaseType.java
 * Date: 2017年5月16日
 */
package com.ojdbc.sql;

import com.ojdbc.sql.connection.AccessDataBaseConnection;
import com.ojdbc.sql.connection.MongoDataBaseConnection;
import com.ojdbc.sql.connection.MySQLDataBaseConnection;
import com.ojdbc.sql.connection.OracleDataBaseConnection;
import com.ojdbc.sql.connection.PostgreSQLDataBaseConnection;
import com.ojdbc.sql.connection.SQLServerDataBaseConnection;
import com.ojdbc.sql.connection.SQLiteDataBaseConnection;
import com.ojdbc.sql.database.AccessDataBase;
import com.ojdbc.sql.database.MongoDataBase;
import com.ojdbc.sql.database.MySQLDataBase;
import com.ojdbc.sql.database.OracleDataBase;
import com.ojdbc.sql.database.PostgreSQLDataBase;
import com.ojdbc.sql.database.SQLServerDataBase;
import com.ojdbc.sql.database.SQLiteDataBase;

/**
 * Author: ShaoGaige
 * Description: 数据库类型枚举
 * Log: 
 */
public enum DataBaseEnum implements IDataSource {
	
	/**
	 * Oracle
	 */
	 ORACLE{
		 
		@Override
		public String getDataBaseName() {
			// TODO Auto-generated method stub
			return "Oracle";
		}

		@Override
		public IConnection getConnection() {
			// TODO Auto-generated method stub
			return new OracleDataBaseConnection();
		}

		@SuppressWarnings("unchecked")
		@Override
		public <T extends DataBase> Class<T> getDataBase() {
			// TODO Auto-generated method stub
			return (Class<T>) OracleDataBase.class;
		}
		
	},
	/**
	 * MySQL
	 */
	MYSQL{

		@Override
		public String getDataBaseName() {
			// TODO Auto-generated method stub
			return "MySQL";
		}

		@Override
		public IConnection getConnection() {
			// TODO Auto-generated method stub
			return new MySQLDataBaseConnection();
		}

		@SuppressWarnings("unchecked")
		@Override
		public <T extends DataBase> Class<T> getDataBase() {
			// TODO Auto-generated method stub
			return (Class<T>) MySQLDataBase.class;
		}

	
	},
	/**
	 * SQLServer
	 */
	SQLSERVER{

		@Override
		public String getDataBaseName() {
			// TODO Auto-generated method stub
			return "SQLServer";
		}

		@Override
		public IConnection getConnection() {
			// TODO Auto-generated method stub
			return new SQLServerDataBaseConnection();
		}

		@SuppressWarnings("unchecked")
		@Override
		public <T extends DataBase> Class<T> getDataBase() {
			// TODO Auto-generated method stub
			return (Class<T>) SQLServerDataBase.class;
		}
	},
	/**
	 * SQLite
	 */
	SQSLITE{

		@Override
		public String getDataBaseName() {
			// TODO Auto-generated method stub
			return "SQLite";
		}

		@Override
		public IConnection getConnection() {
			// TODO Auto-generated method stub
			return new SQLiteDataBaseConnection();
		}

		@SuppressWarnings("unchecked")
		@Override
		public <T extends DataBase> Class<T> getDataBase() {
			// TODO Auto-generated method stub
			return (Class<T>) SQLiteDataBase.class;
		}
	
	},
	/**
	 * PostgreSQL
	 */
	POSTGRESQL{

		@Override
		public String getDataBaseName() {
			// TODO Auto-generated method stub
			return "PostgreSQL";
		}

		@Override
		public IConnection getConnection() {
			// TODO Auto-generated method stub
			return new PostgreSQLDataBaseConnection();
		}

		@SuppressWarnings("unchecked")
		@Override
		public <T extends DataBase> Class<T> getDataBase() {
			// TODO Auto-generated method stub
			return (Class<T>) PostgreSQLDataBase.class;
		}
	
	},
	/**
	 * MongoDB
	 */
	MONGODB{

		@Override
		public String getDataBaseName() {
			// TODO Auto-generated method stub
			return "MongoDB";
		}

		@Override
		public IConnection getConnection() {
			// TODO Auto-generated method stub
			return new MongoDataBaseConnection();
		}

		@SuppressWarnings("unchecked")
		@Override
		public <T extends DataBase> Class<T> getDataBase() {
			// TODO Auto-generated method stub
			return (Class<T>) MongoDataBase.class;
		}
	},
	/**
	 * Access
	 */
	ACCESS{

		@Override
		public String getDataBaseName() {
			// TODO Auto-generated method stub
			return "Access";
		}

		@Override
		public IConnection getConnection() {
			// TODO Auto-generated method stub
			return new AccessDataBaseConnection();
		}

		@SuppressWarnings("unchecked")
		@Override
		public <T extends DataBase> Class<T> getDataBase() {
			// TODO Auto-generated method stub
			return (Class<T>) AccessDataBase.class;
		}
	};

}
