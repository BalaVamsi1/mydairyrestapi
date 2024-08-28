package com.bala.mydairyrestapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bala.mydairyrestapi.entity.User;
import com.bala.mydairyrestapi.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/users/")
	public List<User> findAllUsers(){
		List<User> userlist=userService.findAll();
		return userlist;
	}
	@PostMapping("/users/")
	public User saveUser(@RequestBody User user)
	{
		User saveUser=userService.saveUser(user);
		return saveUser;
	}
	@PutMapping("/users/")
	public User updateUser(@RequestBody User user) {
		User updatedUser=userService.updateUser(user);
		return updatedUser;
	}
	@GetMapping("/users/{id}")
	public User getUser(@PathVariable("id") int id) {
		User user1=userService.findById(id);
		return user1;
	}
	@PutMapping("/users/{id}")
	public User updateUser1(@PathVariable("id") int id, @RequestBody User user) {
		User user1=userService.findById(id);
		user1.setUsername(user.getUsername());
		user1.setPassword(user.getPassword());
		User user2=userService.updateUser(user1);
		return user2;
	}
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable("id") int id) {
		User user1=userService.findById(id);
		userService.deleteUser(user1);
		
	}
	@PatchMapping("/users/{id}")
	public User updateById(@PathVariable("id") int id,@RequestBody User user)
	{
		User user1=userService.findById(id);
		String usern=user1.getUsername();
		String pwd=user1.getPassword();
		long uid=user1.getId();
		if(usern!=null && usern.length()>0)
		{
			user1.setUsername(user.getUsername());
		}
		if(pwd!=null && pwd.length()>0) {
			user1.setPassword(user.getPassword());
		}
		if(uid>0) {
			user1.setId(user.getId());
		}
		
		User user2=userService.updateUser(user1);
		return user2;
	}
	
	
	

}
