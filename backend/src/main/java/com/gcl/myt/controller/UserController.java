package com.gcl.myt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcl.myt.exception.ResourceNotFoundException;
import com.gcl.myt.model.User;
import com.gcl.myt.repository.UserRepository;
import com.gcl.myt.security.CurrentUser;
import com.gcl.myt.security.UserPrincipal;

@RestController
public class UserController {
	
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/user/me")
	@PreAuthorize("hasRole('USER')")
	public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
		return userRepository.findById(userPrincipal.getId())
				.orElseThrow(() -> new ResourceNotFoundException("User","id", userPrincipal.getId()));
	}
	

}
