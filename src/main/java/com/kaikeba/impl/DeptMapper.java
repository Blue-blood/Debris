package com.kaikeba.impl;

import com.kaikeba.service.SqlSession;
import com.mysql.jdbc.PreparedStatement;

public class DeptMapper implements SqlSession {

	PreparedStatement ps;
	public int save(String sql) throws Exception {
		int value = ps.executeUpdate(sql);
		return value;
	}




}
