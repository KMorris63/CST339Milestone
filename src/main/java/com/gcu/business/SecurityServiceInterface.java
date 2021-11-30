package com.gcu.business;

import javax.servlet.http.HttpServletResponse;

import com.gcu.model.UserEntity;
import com.gcu.model.UserModel;

/*
 * Kacey morris and Alex vergara
 * Milestone
 * 10/22/2021
 */
public interface SecurityServiceInterface {

	public void test();
	public UserEntity registerUser(UserModel usermasodel, HttpServletResponse response);
	boolean isAuthenticated(UserModel loginModel, String username, String password);
	public UserEntity getByUsername(UserModel userModel);	
}
