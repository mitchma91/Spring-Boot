package com.mitchManuel.service;

import javax.validation.Valid;

import com.mitchManuel.dto.ChangePasswordForm;
import com.mitchManuel.entity.User;

public interface UserService {

	public Iterable<User> getAllUsers();
	public User createUser(@Valid User user) throws Exception;
	User getUserById(Long id) throws Exception ;
	public User updateUser(User user) throws Exception;
	public void deleteUser(Long id) throws Exception;
	public User changePassword(ChangePasswordForm form) throws Exception;
}
