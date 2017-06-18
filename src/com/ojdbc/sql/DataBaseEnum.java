/**
 * ClassName: DataBaseType.java
 * Date: 2017年5月16日
 */
package com.ojdbc.sql;

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
		public <T extends IConnection> DataBaseType createDataBaseType(
				String dataBaseType, T dataBaseClass) {
			// TODO Auto-generated method stub
			return new DataBaseType(dataBaseType,dataBaseClass);
		}
	
	},
	/**
	 * MySQL
	 */
	MYSQL{

		@Override
		public <T extends IConnection> DataBaseType createDataBaseType(
				String dataBaseType, T dataBaseClass) {
			// TODO Auto-generated method stub
			return new DataBaseType(dataBaseType,dataBaseClass);
		}
	
	},
	/**
	 * SQLServer
	 */
	SQLSERVER{

		@Override
		public <T extends IConnection> DataBaseType createDataBaseType(
				String dataBaseType, T dataBaseClass) {
			// TODO Auto-generated method stub
			return new DataBaseType(dataBaseType,dataBaseClass);
		}
	
	},
	/**
	 * SQLite
	 */
	SQSLITE{

		@Override
		public <T extends IConnection> DataBaseType createDataBaseType(
				String dataBaseType, T dataBaseClass) {
			// TODO Auto-generated method stub
			return new DataBaseType(dataBaseType,dataBaseClass);
		}
	
	},
	/**
	 * PostgreSQL
	 */
	POSTGRESQL{

		@Override
		public <T extends IConnection> DataBaseType createDataBaseType(
				String dataBaseType, T dataBaseClass) {
			// TODO Auto-generated method stub
			return new DataBaseType(dataBaseType,dataBaseClass);
		}
	
	},
	/**
	 * MongoDB
	 */
	MONGODB{

		@Override
		public <T extends IConnection> DataBaseType createDataBaseType(
				String dataBaseType, T dataBaseClass) {
			// TODO Auto-generated method stub
			return new DataBaseType(dataBaseType,dataBaseClass);
		}
	
	},
	/**
	 * Access
	 */
	ACCESS{

		@Override
		public <T extends IConnection> DataBaseType createDataBaseType(
				String dataBaseType, T dataBaseClass) {
			// TODO Auto-generated method stub
			return new DataBaseType(dataBaseType,dataBaseClass);
		}
	
	};

}
