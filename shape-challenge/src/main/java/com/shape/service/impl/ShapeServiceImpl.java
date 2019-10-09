package com.shape.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shape.repository.ShapeRepository;
import com.shape.service.ShapeService;
import com.shape.ws.rest.vm.AttributeVM;
import com.shape.ws.rest.vm.FormularVM;
import com.shape.ws.rest.vm.ShapeVM;
import com.shape.ws.rest.vm.UserVM;

@Service
public class ShapeServiceImpl implements ShapeService {

	@Autowired
	private ShapeRepository shapeRepository;
	
	@Override
	public List<ShapeVM> getShapes() {
		return shapeRepository.getShapes().stream().map(shape -> {
			return ShapeVM.builder().id(shape.getId())
				.name(shape.getName())
				.level(shape.getLevel())
				.formulars(
						shape.getFormulars().stream().map(formular -> {
							return FormularVM.builder()
									.id(formular.getId())
									.value(formular.getName())
									.pattern(formular.getPattern())
									.build();
							}).collect(Collectors.toSet())
						)
				.attributes(
						shape.getAttributes().stream().map(attribute ->{
							return AttributeVM.builder()
									.id(attribute.getId())
									.name(attribute.getName())
									.dataType(attribute.getDataType())
									.required(attribute.getRequired())
									.value(attribute.getValue())
									.build();
						}).collect(Collectors.toSet()))
				.build();
		}).collect(Collectors.toList());
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
