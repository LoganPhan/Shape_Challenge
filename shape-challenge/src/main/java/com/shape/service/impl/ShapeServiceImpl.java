package com.shape.service.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.shape.repository.ShapeRepository;
import com.shape.service.ShapeService;
import com.shape.service.dto.Attribute;
import com.shape.service.dto.Formular;
import com.shape.service.dto.Shape;
import com.shape.service.exception.BadRequestException;
import com.shape.service.exception.ShapeChallangeException;

@Service
public class ShapeServiceImpl implements ShapeService {
	
	@Autowired
	private ShapeRepository shapeRepository;
	
	@Override
	public List<Shape> getShapes() {
		return shapeRepository.getShapes().stream().map(item -> item).collect(Collectors.toList());
	}

	@Override
	public Shape submit(Shape shape){
		Shape shapeRespo = shapeRepository.getShapeById(shape.getId());
		isAttributesValid(getRequiredAttributes(shapeRespo), shape);
		Set<Formular> resultFormulars = new LinkedHashSet<>();
		shapeRespo.getFormulars().stream().forEach(formular -> {
			resultFormulars.add(Formular.builder().name(formular.getName())
					.value(computeFormular(formular.getPattern(), shape.getAttributes())).build());
		});
		shape.setFormulars(resultFormulars);
		return shape;
	}

	@Override
	public Shape save(Shape shape) {
		isShapeValid(shape);
		return shapeRepository.save(shape);
	}

	@Override
	public Boolean deleteById(Long id) {
		return shapeRepository.deleteById(id);
	}

	private void isShapeValid(Shape shape) {
		Set<Attribute> attributes = getRequiredAttributes(shape);
		Set<Formular> formulars = shape.getFormulars();
		formulars.stream().forEach(formular -> {
			String pattern = formular.getPattern();
			attributes.stream().forEach(attribute -> {
				if (ObjectUtils.isEmpty(pattern))
					throw new BadRequestException("Fomular pattern is required!");
				if (!pattern.contains(attribute.getName()))
					throw new BadRequestException(
							String.format("Fomular pattern should contain attribute '%s' !", attribute.getName()));
			});
		});
	}
	
	private Set<Attribute> getRequiredAttributes(Shape shape){
		return shape.getAttributes().stream()
				.filter(attribute -> attribute.getRequired())
				.collect(Collectors.toSet());
	}
	
	private void isAttributesValid(Set<Attribute> requiredAttributes, Shape shape) {
		Long validAttribute = shape.getAttributes().stream()
				.filter(attribute -> requiredAttributes.contains(attribute) && !ObjectUtils.isEmpty(attribute.getValue()))
				.count();
		if (validAttribute != Long.valueOf(requiredAttributes.size()))
			throw new BadRequestException("Required Attribute is missing");
	}
	
	private String computeFormular(String pattern, Set<Attribute> attributes){
		pattern = mappingValue(pattern, attributes);
		try {
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("js");
			return engine.eval(pattern).toString();
		} catch (ScriptException e) {
			throw new ShapeChallangeException(e.getMessage());
		}
	
	}
	private String mappingValue(String pattern, Set<Attribute> attributes) {
		for (Attribute attribute : attributes) {
			pattern = pattern.replace(attribute.getName(), attribute.getValue());
		}
		return pattern;
	}

	@Override
	public Shape getShapeById(Long id) {
		return shapeRepository.getShapeById(id);
	}

	@Override
	public void deleteAll() {
		shapeRepository.deleteAll();
	}
}
