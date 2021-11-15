package com.gcu.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UsersMapper implements RowMapper<UserModel>{

	
	@Override
	public UserModel mapRow(ResultSet resultset, int rowNum) throws SQLException {
		
		UserModel user = new UserModel(
				resultset.getString("USERNAME"),
				resultset.getString("PASSWORD")
				);
		
		return user;
		
	}

}
