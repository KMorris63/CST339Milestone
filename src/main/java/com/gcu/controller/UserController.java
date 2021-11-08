package com.gcu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.MilestoneApplication;
import com.gcu.business.SecurityServiceInterface;
import com.gcu.model.ProductModel;
import com.gcu.model.UserModel;
/*
 * Kacey morris and Alex vergara
 * Milestone
 * 10/22/2021
 */
@Controller
@RequestMapping("/main")
public class UserController {
	
	// DEPENDENCY INJECTION
	@Autowired
	SecurityServiceInterface securityService;
	
	
	// home route
	@GetMapping("/home")
	public String homePage(Model model) {
		// Display Login Form View
		model.addAttribute("title", "Vacation Site");
		model.addAttribute("userModel", new UserModel());
		int users=1;
		model.addAttribute("users", users);
		return "homePage";
	}
	
	// login route takes us to login page
	@GetMapping("/login")
	public String display(Model model) {
		// Display Login Form View
		model.addAttribute("userModel", new UserModel());
		return "login";
	}
	
	
	// Login Form
	@PostMapping("/doLogin")
	public String doLogin(@Valid UserModel loginModel, BindingResult bindingResult, Model model, @CookieValue(value = "username", defaultValue = "Atta") String username, 
			@CookieValue(value = "password", defaultValue = "Atta") String password)
	{
		//console output showing that the securityService is running
		securityService.test();
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("title", "Login Form");
			return "login";
		}		
		
		// Dependency injection
		// if user exists 
		if(securityService.isAuthenticated(loginModel, username, password)) {
			model.addAttribute("model", loginModel);
			return "LoginSuccess";
		}
		// if user is not registered
		else {
			System.out.println(username);
			return "LoginFailed";
		}
		
	}
	
	
	// this takes us to the register page
	@GetMapping("/register")
	public String showRegister(Model model) {
		// Display Register Form View
		model.addAttribute("userModel", new UserModel());
	
		return "register";
	}
	
	// register form post	
	@PostMapping("/doRegister")
	public String doRegister(@Valid UserModel userModel, BindingResult bindingResult, Model model, HttpServletResponse response) {
		// check for errors
		if (bindingResult.hasErrors()) {
			model.addAttribute("title", "Registration Form");
			return "register";
		}
		
		// add user model
		model.addAttribute("userModel", userModel);
		
		// register user and track them with cookies
		UserModel usr1 = securityService.registerUser(userModel, response);
		System.out.println();
		
		return "RegisterSuccess";
		
	}
	
	// goes to create product page
	@GetMapping("/createProduct")
	public String createProduct(Model model) {
		model.addAttribute("productModel", new ProductModel());
		return "createProduct";
	}
	
	@PostMapping("/createPackage")
	public String createPackage(@Valid ProductModel productModel,BindingResult bindingResult,  Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("title", "Registration Form");
			return "CreateProduct";
		}
		return "ProductSuccess";
	}
		
	
}