package com.mitchManuel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import javax.validation.Valid;
import com.mitchManuel.entity.User;
import com.mitchManuel.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository repository;
	
	@Override
	public Iterable<User> getAllUsers(){
		return repository.findAll();
	}
	
	
	
	private boolean checkUsernameAvailable(User user) throws Exception{
		Optional<User> userFound= repository.findByUsername(user.getUsername());
		if(userFound.isPresent()) {
			throw new Exception("Username no disponible");
		}
		return true;
	}

	
	private boolean checkPasswordValid(User user) throws Exception{
		if (user.getConfirmPassword() == null || user.getConfirmPassword().isEmpty()) {
			throw new Exception("Confirm Password es obligatorio");
		}
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			throw new Exception("Password y Confirm Password no son iguales");
		}
		return true;
	}


	/*@Override
	public User createUser(User user) throws Exception {
		if (checkUsernameAvailable(user) && checkPasswordValid(user)) {
			user = repository.save(user);
		}
		return user;
	}*/
	@Override
	public User createUser(User user) throws Exception{
		if (checkUsernameAvailable(user) && checkPasswordValid(user)) {
			user= repository.save(user);
		}
		return user;
	}
	
	@Override
	public User getUserById(Long id) throws Exception {
		User user = repository.findById(id).orElseThrow(() -> new Exception("Usuario no existe"));
		return user;
	}
	
	@Override
	public User updateUser(User fromUser) throws Exception {
		User toUser = getUserById(fromUser.getId());
		mapUser(fromUser, toUser);
		return repository.save(toUser);
	}
	
	/**
	 * Map everythin but the password.
	 * @param from
	 * @param to
	 */
	protected void mapUser(User from,User to) {
		to.setUsername(from.getUsername());
		to.setFirstName(from.getFirstName());
		to.setLastName(from.getLastName());
		to.setEmail(from.getEmail());
		to.setRoles(from.getRoles());
	}
	
	public void deleteUser(Long id) throws Exception {
		User user = repository.findById(id)
				.orElseThrow(() -> new Exception("UsernotFound in deleteUser -"+this.getClass().getName()));

		repository.delete(user);
	}
}
