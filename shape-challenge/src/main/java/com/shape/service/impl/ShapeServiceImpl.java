package com.shape.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shape.repository.ShapeRepository;
import com.shape.repository.UserShapeRepository;
import com.shape.service.ShapeService;
import com.shape.service.dto.Shape;
import com.shape.service.dto.User;

@Service
public class ShapeServiceImpl implements ShapeService {

	@Autowired
	private ShapeRepository shapeRepository;
	
	@Autowired
	private UserShapeRepository userShapeRepository;
	
	@Override
	public List<Shape> getShapes() {
		return shapeRepository.getShapes().stream().map(item -> item).collect(Collectors.toList());
	}

	@Override
	public HashMap<User, Shape> submit(User user, Shape shape) {
		userShapeRepository.save(user, shape);
		return null;
	}

	@Override
	public HashMap<User, List<Shape>> getShapesByUserId(Long userId) {
		return userShapeRepository.getShapesByUserId(userId);
	}

	@Override
	public Shape save(Shape shape) {
		return shapeRepository.save(shape);
	}

	@Override
	public Boolean deleteById(Long id) {
		return shapeRepository.deleteById(id);
	}

}
