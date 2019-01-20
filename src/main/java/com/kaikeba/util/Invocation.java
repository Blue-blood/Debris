package com.kaikeba.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.kaikeba.service.SqlSession;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Invocation implements InvocationHandler{
	
	SqlSession obj;
	
	public Invocation(SqlSession obj) {
		this.obj=obj;
	}
	
	Connection connection;
	PreparedStatement ps;
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		init(args.toString());
		
		
		//给DeptMapper的ps参数赋值有三部
		//1.获取DeptMapper的ps参数
		Field field = obj.getClass().getDeclaredField("ps");
		//2.属性从私有变成可视化
		field.setAccessible(true);
		//3.赋值
		field.set(obj, ps);
		
		//执行主要业务
		Object value = method.invoke(obj, args);
		close();
		return value;
		
	}

	private void init(String sql)throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		connection = (Connection) DriverManager.getConnection("jdbc:mysql://local:3306/test", "root", "root");
		ps = (PreparedStatement) connection.prepareStatement(sql);
	}
	private void close() throws SQLException{
		if(ps!=null){
			ps.close();
		}
		if(connection!=null){
			connection.close();
		}
	}
}
