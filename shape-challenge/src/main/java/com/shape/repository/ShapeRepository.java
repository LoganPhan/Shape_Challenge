package com.shape.repository;

import java.util.Set;

import com.shape.service.dto.Shape;

/**
 * 
 * @author logan
 *
 */
public interface ShapeRepository {
	
	Shape getShapeById(Long id);
	
	Set<Shape> getShapes();
	
	Shape save(Shape shape);
	
	Boolean deleteById(Long shapeId);
	
	void deleteAll();
}
