package com.gcu.business;

import javax.servlet.http.HttpServletResponse;

import com.gcu.model.UserModel;

/*
 * Kacey morris and Alex vergara
 * Milestone
 * 10/22/2021
 */
public interface SecurityServiceInterface {

	public void test();
	public UserModel registerUser(UserModel usermasodel, HttpServletResponse response);
	boolean isAuthenticated(UserModel loginModel, String username, String password);
	
}
