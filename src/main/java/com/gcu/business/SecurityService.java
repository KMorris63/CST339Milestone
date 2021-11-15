package com.gcu.business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;

import com.gcu.data.UsersDataService;
import com.gcu.model.UserModel;

/*
 * Kacey morris and Alex vergara
 * Milestone
 * 11/7/2021
 */
public class SecurityService implements SecurityServiceInterface{
	
	@Autowired
	UsersDataService usersDAO;
	
	
	@Override
	public boolean isAuthenticated(UserModel loginModel, String username, String password) {
	
		// Check to see if the login matches any of the valid logins
		int result = usersDAO.getUsersByUsername(loginModel.getUsername(), loginModel.getPassword());
		
		
		// successful login ; ie returns true
		if(result > 0) {
			System.out.println("number of people with this username: " + result);
			return true;
		}
		// checks for registered user using cookies
		else if(loginModel.getUsername().equals(username) && loginModel.getPassword().equals(password)) {
			return true;
		}
		// user not found
		else {
			return false;
		}
	}
	

	@Override
	public void test() {
		System.out.println("We are running and using the SecurityService");
		
	}

	// register user
	@Override
	public UserModel registerUser(UserModel userModel, HttpServletResponse response) {
		
		// call add user method
		usersDAO.addUser(userModel);
		
		// return user model
		UserModel usr1 = new UserModel(userModel.getUsername(), userModel.getPassword());
		
		return usr1;
	}


	
	// Valid Logins
//		String[][] validLogins = new String[][] {
//			{"kacey", "12345"},
//			{"dayglow","fuzzy"},
//			{"j^p^n","lofi"},
//			{"cloud","password"},
//			{"tifa","12345"},
//			{"summer","ville"},
//			{"aldn","glaivy"},
//			{"alexvii", "12345"}
//			
//		};
		
		// check if user exits
//		@Override
//		public boolean isAuthenticated(UserModel loginModel, String username, String password) {
	//	
//			// Check to see if the login matches any of the valid logins
//			boolean success = false;
//			// loop through 2d array
//			for (int i = 0; i < validLogins.length; i++) {
//				if(loginModel.getUsername().equals(validLogins[i][0]) && loginModel.getPassword().equals(validLogins[i][1])){
//					// if the user exists return true
//					success = true;
//				}
//			}
//			// successful login ; ie returns true
//			if(success) {
//				return true;
//			}
//			// checks for registered user using cookies
//			else if(loginModel.getUsername().equals(username) && loginModel.getPassword().equals(password)) {
//				return true;
//			}
//			// user not found
//			else {
//				return false;
//			}
//		}

}
