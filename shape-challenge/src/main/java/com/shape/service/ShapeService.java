package com.shape.service;

import java.util.HashMap;
import java.util.List;

import com.shape.ws.rest.vm.ShapeVM;
import com.shape.ws.rest.vm.UserVM;

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
	List<ShapeVM> getShapes();
	
	HashMap<UserVM, ShapeVM> submit(UserVM user, ShapeVM shape);
	
	HashMap<UserVM, List<ShapeVM>> getShapesByUserId(Long userId);
	
	ShapeVM save(ShapeVM shape);
	
	Boolean deleteById(Long id);
	
}
