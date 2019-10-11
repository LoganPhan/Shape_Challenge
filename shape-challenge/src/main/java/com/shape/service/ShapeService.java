package com.shape.service;

import java.util.HashMap;
import java.util.List;

import com.shape.service.dto.Shape;
import com.shape.service.dto.User;

/**
 *
 * @author logan
 *
 */
public interface ShapeService {
	
	/**
	 * API to list all the ​ shape​ ​ categories​ , each with its corresponding requirement sets of dimension, length and/or angle.
	 * @return list of shapes
	 */
	List<Shape> getShapes();
	
	HashMap<User, Shape> submit(User user, Shape shape);
	
	HashMap<User, List<Shape>> getShapesByUserId(Long userId);
	
	Shape save(Shape shape);
	
	Boolean deleteById(Long id);
	
}
