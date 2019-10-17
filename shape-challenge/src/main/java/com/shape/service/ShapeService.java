package com.shape.service;

import java.util.List;

import com.shape.service.dto.Shape;

/**
 *
 * @author logan
 *
 */
public interface ShapeService {
	
	Shape getShapeById(Long id);
	
	/**
	 * API to list all the ​ shape​ ​ categories​ , each with its corresponding
	 * requirement sets of dimension, length and/or angle.
	 * 
	 * @return list of shapes
	 */
	List<Shape> getShapes();

	Shape submit(Shape shape);

	Shape save(Shape shape);

	Boolean deleteById(Long id);

}
