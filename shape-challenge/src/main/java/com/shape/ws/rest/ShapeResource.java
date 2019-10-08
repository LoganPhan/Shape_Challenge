package com.shape.ws.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shape.service.ShapeService;
import com.shape.ws.rest.vm.ShapeVM;

@RestController
@RequestMapping("/api")
public class ShapeResource {

	private final Logger log = LoggerFactory.getLogger(ShapeResource.class);

	@Autowired
	private ShapeService shapeService;

	@RequestMapping("/shapes")
	public ResponseEntity<List<ShapeVM>> getShapes() {
		return new ResponseEntity<>(shapeService.getShapes(), HttpStatus.OK);
	}

	@PostMapping("/shapes")
	public ResponseEntity<ShapeVM> createShape(ShapeVM shape) throws URISyntaxException {
		return ResponseEntity.created(new URI("/api/shapes/" + shape.getId())).body(shapeService.save(shape));

	}

	@PutMapping("/shapes")
	public ResponseEntity<ShapeVM> updateShape(ShapeVM shape) throws URISyntaxException {
		return ResponseEntity.created(new URI("/api/shapes/" + shape.getId())).body(shapeService.save(shape));

	}

	@DeleteMapping("/shapes/{id}")
	public ResponseEntity<ShapeVM> deleteShape(@PathVariable Long id) throws URISyntaxException {
		log.debug("REST request to delete Shape : {}", id);
		shapeService.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
}
