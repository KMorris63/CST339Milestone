package com.gcu.data;

import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gcu.model.UserModel;
import com.gcu.model.UsersMapper;

@Repository
public class UsersDataService {
	
	@Autowired
	DataSource datasource;
	JdbcTemplate jdbcTemplate;
	
	public UsersDataService(DataSource datasource)
	{
		this.datasource = datasource;
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	
	
	//@SuppressWarnings("deprecation")
	@SuppressWarnings("deprecation")
	public int getUsersByUsername(String username, String password)
	{

		String sql = "SELECT COUNT(*) FROM users where username = ?";
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users where username = ? and password = ?", new Object[]{username, password}, Integer.class);

	}

	public int addUser(UserModel user)
	{
		return jdbcTemplate.update("insert into users (ID, USERNAME, PASSWORD, FIRST_NAME, LAST_NAME, EMAIL, PHONE) VALUES(?,?,?,?,?,?,?)",
				0,
				user.getUsername(),
				user.getPassword(),
				user.getFirstname(),
				user.getLastname(),
				user.getEmail(),
				user.getPhone()
				);
	}

	
}
