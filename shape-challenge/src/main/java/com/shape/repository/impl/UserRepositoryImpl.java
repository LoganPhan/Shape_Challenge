package com.shape.repository.impl;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import com.shape.repository.UserRepository;
import com.shape.service.dto.User;

public class UserRepositoryImpl implements UserRepository {
	
	private final AtomicLong sequence = new AtomicLong(0);
	
	private Set<User> users = new LinkedHashSet<User>();

	@Override
	public Set<User> getUsers() {
		return users;
	}

	@Override
	public synchronized User save(User user) {
		user.setId(sequence.incrementAndGet());
		users.add(user);
		return user;
	}

	public Long getSequence() {
		return sequence.get();
	}
}
