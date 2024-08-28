package com.bala.mydairyrestapi.service;

import java.util.List;

import com.bala.mydairyrestapi.entity.User;

public interface UserService {
	public User saveUser(User user);
	public User updateUser(User user);
	public void deleteUser(User user);
	public User findById(long id);
	public List<User> findAll();
	public User findByUsername(String username);
	

}
