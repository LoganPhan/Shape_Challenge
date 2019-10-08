package com.shape.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shape.service.ShapeService;
import com.shape.ws.rest.vm.ShapeVM;
import com.shape.ws.rest.vm.UserVM;

@Service
public class ShapeServiceImpl implements ShapeService {

	@Override
	public List<ShapeVM> getShapes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<UserVM, ShapeVM> submit(UserVM user, ShapeVM shape) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<UserVM, List<ShapeVM>> getShapesByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShapeVM save(ShapeVM shape) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
