package com.shape.repository;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.shape.service.dto.Shape;
import com.shape.service.dto.User;

/**
 * 
 * @author logan
 *
 */
public interface UserShapeRepository {
	
	ConcurrentHashMap<Long, List<Shape>> getUserShapes();
	
	HashMap<Long, List<Shape>> save(User user, Shape shape);
	
	HashMap<User, List<Shape>> getShapesByUserId(Long userId);
}
