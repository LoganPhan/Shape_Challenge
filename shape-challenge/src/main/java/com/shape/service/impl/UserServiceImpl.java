package com.shape.service.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shape.repository.UserRepository;
import com.shape.service.UserService;
import com.shape.service.dto.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	private Set<SimpleGrantedAuthority> getAuthority(User user) {
		Set<SimpleGrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toSet());
		return authorities;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.getUserByUserName(username);
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				getAuthority(user));
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public void removeToken(String userName) {
		User user = userRepository.getUserByUserName(userName);
		user.setAccessToken(null);
		userRepository.save(user);
	}

	@Override
	public Set<User> getUsers() {
		return userRepository.getUsers().stream().map(item -> item).collect(Collectors.toSet());
	}

	@Override
	public String setToken(String userName, String token) {
		User user = userRepository.getUserByUserName(userName);
		user.setAccessToken(token);
		return save(user).getAccessToken();
	}

	@Override
	public boolean findUserByUserNameAndToken(String userName, String token) {
		return userRepository.findUserByUserNameAndToken(userName, token);
	}

}
