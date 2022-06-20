package com.demo.bms.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.bms.pojo.UserPojo;

@CrossOrigin
@RestController
public class UserController {
	// post - to validate a user
	// http://localhost:5555/users with a request body
	@PostMapping("users")
	public UserPojo validateUser(@RequestBody UserPojo userPojo) {
		//performing hard coded validation
		// ideally the user will be validated against the database
		if("admin".equals(userPojo.getUserName()) && "admin".equals(userPojo.getPassword())){
			userPojo.setFirstName("John");
			userPojo.setLastName("Smith");
			userPojo.setRole("admin");
		}else if("guest".equals(userPojo.getUserName()) && "guest".equals(userPojo.getPassword())){
			userPojo.setFirstName("Katherine");
			userPojo.setLastName("Paul");
			userPojo.setRole("employee");
		}
		
		// return back the user pojo
		// the user pojo is loaded with user info, if validation was a success
		// else the userPojo has only the username and password
		return userPojo;
	}	
}
