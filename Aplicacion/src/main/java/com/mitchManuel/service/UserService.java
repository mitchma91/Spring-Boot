package com.mitchManuel.service;

import javax.validation.Valid;

import com.mitchManuel.entity.User;

public interface UserService {

	public Iterable<User> getAllUsers();
	public User createUser(@Valid User user) throws Exception;
	
}
