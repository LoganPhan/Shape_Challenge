package com.shape.repository.impl;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.shape.repository.UserRepository;
import com.shape.service.dto.Role;
import com.shape.service.dto.User;
import com.shape.service.exception.BadRequestException;
import com.shape.service.exception.NotFoundException;
import com.shape.service.impl.UserServiceImpl;

@Component
public class UserRepositoryImpl implements UserRepository {
	
	private final Logger log = LoggerFactory.getLogger(UserRepositoryImpl.class);
	
	private final AtomicLong sequence = new AtomicLong(0);
	
	private Set<User> users = new LinkedHashSet<User>();
	
	public UserRepositoryImpl(){
		
	}
	@Override
	public Set<User> getUsers() {
		return users;
	}

	@Override
	public synchronized User save(User user) {
		if(user.getId() == null) {
			user.setId(sequence.incrementAndGet());
		}
		users.add(user);
		return user;
	}

	public Long getSequence() {
		return sequence.get();
	}

	@Override
	public User getUserById(Long userId) {
		return users.stream().filter(user -> user.getId().equals(userId)).findFirst()
				.orElseThrow(() -> new UsernameNotFoundException("User Not found"));
	}
	@Override
	public User getUserByUserName(String userName) {
		return users.stream().filter(user -> user.getUserName().equals(userName)).findFirst()
				.orElseThrow(() -> new UsernameNotFoundException("User Not found"));
	}
	
	@Override
	public boolean findUserByUserNameAndToken(String userName, String token) {
		return 	users.stream()
				.filter(user -> user.getUserName().equals(userName) && token.equals(user.getAccessToken())).findFirst()
				.isPresent();
	}
	@Override
	public synchronized boolean deleteUserById(Long userId) {
		User currentUser = users.stream().filter(user -> user.getId().equals(userId)).findFirst()
				.orElseThrow(() -> new NotFoundException(String.format("User not found by Id: %s", userId)));
		if(currentUser.getRoles().contains(Role.SUPER_ADMIN.name())) {
			log.error("Some one try to delete SuperAdmin. System is leaked");
			 throw new BadRequestException(String.format("Something When Wrong", userId));
		}
		return users.remove(currentUser);
	}
	
}
