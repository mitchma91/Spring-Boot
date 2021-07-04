package com.mitchManuel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mitchManuel.entity.User;
import com.mitchManuel.repository.RoleRepository;
import com.mitchManuel.service.UserService;


@Controller
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	RoleRepository roleRepository;
	
	
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/userForm")
	public String UserForm(Model model) {
		model.addAttribute("userForm", new User());
		model.addAttribute("roles",roleRepository.findAll());
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("listTab","active");
		return "user-form/user-view";
	}	
}
