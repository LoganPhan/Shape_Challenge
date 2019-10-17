package com.shape.repository.impl;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.shape.repository.ShapeRepository;
import com.shape.service.dto.Shape;
import com.shape.service.exception.NotFoundException;

@Repository
public class ShapeRepositoryImpl implements ShapeRepository{
	
	private final AtomicLong sequence = new AtomicLong(0);
	
	private Set<Shape> shapes = new LinkedHashSet<>();
	
	@Override
	public Set<Shape> getShapes() {
		return shapes;
	}

	@Override
	public synchronized Shape save(Shape shape) {
		if(shape.getId() == null) {
			shape.setId(sequence.incrementAndGet());
		}
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

	@Override
	public Shape getShapeById(Long id) {
		return shapes.stream().filter(shape -> shape.getId().equals(id)).findFirst()
				.orElseThrow(() -> new NotFoundException(String.format("Shape not found by Id: %s", id)));
	}

	@Override
	public void deleteAll() {
		shapes.clear();
	}
}
