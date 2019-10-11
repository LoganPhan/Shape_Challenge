package com.shape.ws.rest;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shape.service.UserService;
import com.shape.service.dto.User;

@RestController
@RequestMapping("/api")
public class UserResource {

	@Autowired
	private UserService userService;

	@RequestMapping("/users")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Set<User>> getShapes() {
		return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
	}
	
	
}
