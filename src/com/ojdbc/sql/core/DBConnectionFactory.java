/**
 * ClassName: DBConnectionFactory.java
 * Date: 2017年6月10日
 */
package com.ojdbc.sql.core;

import java.sql.Connection;

import org.apache.commons.pool.KeyedPoolableObjectFactory;

import com.ojdbc.sql.ConnectionObject;
import com.ojdbc.sql.DataBaseManager;
import com.ojdbc.sql.IConnection;

/**
 * Author: ShaoGaige
 * Description: 数据库连接工厂类
 * Log: 
 */
public class DBConnectionFactory implements KeyedPoolableObjectFactory {

	/* (non-Javadoc)
	 * @see org.apache.commons.pool.KeyedPoolableObjectFactory#activateObject(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void activateObject(Object arg0, Object arg1) throws Exception {
		// TODO Auto-generated method stub
		//激活对象
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.pool.KeyedPoolableObjectFactory#destroyObject(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void destroyObject(Object arg0, Object arg1) throws Exception {
		// TODO Auto-generated method stub
		//销毁对象
		String key = (String)arg0;
		System.out.println("正在销毁绘制对象:"+key);
		ConnectionObject conn = (ConnectionObject)arg1;
		conn.getConnection().close();
		conn = null;
		arg1 = null;
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.pool.KeyedPoolableObjectFactory#makeObject(java.lang.Object)
	 */
	@Override
	public Object makeObject(Object arg0) throws Exception {
		// TODO Auto-generated method stub
		//创建对象
		String key = (String) arg0;
		return createConnectionObject(key);
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.pool.KeyedPoolableObjectFactory#passivateObject(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void passivateObject(Object arg0, Object arg1) throws Exception {
		// TODO Auto-generated method stub
		//挂起对象
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.pool.KeyedPoolableObjectFactory#validateObject(java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean validateObject(Object arg0, Object arg1) {
		// TODO Auto-generated method stub
		//验证对象
		if(arg1 != null)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	private ConnectionObject createConnectionObject(String key)
	{
		IConnection iconn = DataBaseManager.getIConnection(key);
		String[] info = new String[3];
		String[] param = key.split("\\+");
		for(int i=0;i<param.length && i<3;i++)
		{
			info[i] = param[i];
		}
		Connection conn = iconn.createConnection(info[0], info[1], info[2]);
		return new ConnectionObject(key,conn);
	}

}
