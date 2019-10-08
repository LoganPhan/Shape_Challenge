package com.shape.repository.impl;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

import com.shape.repository.ShapeRepository;
import com.shape.service.dto.Shape;

@Component
public class ShapeRepositoryImpl implements ShapeRepository{
	
	private final AtomicLong sequence = new AtomicLong(0);
	
	private Set<Shape> shapes = new LinkedHashSet<>();
	
	@Override
	public Set<Shape> getShapes() {
		return shapes;
	}

	@Override
	public synchronized Shape save(Shape shape) {
		shape.setId(sequence.incrementAndGet());
		shapes.add(shape);
		return shape;
	}
	
	public Long getSequence() {
		return sequence.get();
	}

	@Override
	public Boolean deleteById(Long shapeId) {
		return shapes.removeIf(item -> item.getId().equals(shapeId));
	}
}
