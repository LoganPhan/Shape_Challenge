package com.shape.repository.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.shape.repository.UserShapeRepository;
import com.shape.service.dto.Shape;
import com.shape.service.dto.User;

@Component
public class UserShapeRepositoryImpl implements UserShapeRepository{
	
	private ConcurrentHashMap<Long, List<Shape>> userShapes = new ConcurrentHashMap<Long, List<Shape>>();
	
	@Override
	public ConcurrentHashMap<Long, List<Shape>> getUserShapes() {
		return userShapes;
	}

	@Override
	public HashMap<Long, List<Shape>> save(User user, Shape shape) {
		List<Shape> shapes = userShapes.compute(user.getId(), (k, v) -> {
			if(ObjectUtils.isEmpty(v)){
				v = Collections.synchronizedList(new ArrayList<Shape>());
				v.add(shape);
				return v;
			}else {
				v.add(shape);
				return v;
			}
		});
		HashMap<Long, List<Shape>> result = new LinkedHashMap<>();
		result.put(user.getId(), shapes);
		return result;
	}

}
