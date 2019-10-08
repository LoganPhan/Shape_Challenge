package com.shape.repository;

import java.util.Set;

import com.shape.service.dto.User;

/**
 * 
 * @author logan
 *
 */
public interface UserRepository {
	
	Set<User> getUsers();
	
	User save(User user);
}
