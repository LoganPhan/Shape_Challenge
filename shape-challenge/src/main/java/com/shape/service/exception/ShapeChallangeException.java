package com.shape.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author logan
 *
 */
@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class ShapeChallangeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 public ShapeChallangeException(String message) {
	        super(message);
	 }
	

}
