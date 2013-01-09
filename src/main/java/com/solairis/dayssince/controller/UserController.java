/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solairis.dayssince.controller;

import com.solairis.incident.entity.User;
import com.solairis.incident.repository.UserRepository;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author JanieBear
 */
@Controller
@RequestMapping(value = "/api/user")
public class UserController {

	private final UserRepository userRepository;

	@Inject
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@RequestMapping(value = "/{id}")
	@ResponseBody
	public User get(@PathVariable("id") User user) {
		return user;
	}

}
