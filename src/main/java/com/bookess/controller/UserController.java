package com.bookess.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookess.entity.UserEntity;
import com.bookess.service.UserService;
import com.bookess.vo.LoginVO;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public String login(@RequestBody LoginVO loginVo) {
		return userService.login(loginVo);
	}
	
	
	@GetMapping("/logout")
	public String logout(@RequestParam("email") String email) {
		return userService.logout(email);
	}
	
	@CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
	@PostMapping(value="/createUser")
	public String createUser(@RequestBody UserEntity user) {
		return userService.createUser(user);
	}
	
	@PutMapping(value="/updateUser")
	public String updateUser(@RequestBody UserEntity user) {
		return userService.updateUser(user);
	}
	
	@GetMapping(value="/getUserById")
	public UserEntity getUserById(@RequestParam("userId") Long userId) {
		return userService.getUserById(userId);
	}
	
	@DeleteMapping
	public String deleteUser(@RequestParam("userEmail") String userEmail,@RequestParam("adminEmail") String adminEmail) {
		return userService.deleteuser(userEmail,adminEmail);
	}

}
