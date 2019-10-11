package com.shape.service;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.shape.service.dto.User;

/**
 *
 * @author logan
 *
 */
public interface UserService extends UserDetailsService{
	
	Set<User> getUsers();
	
	User save(User user);
	
	String setToken(String userName, String token);
	
	void removeToken(String userName);
	
	boolean findUserByUserNameAndToken(String userName, String token);
	
}
