package com.shape.ws.rest.jwt;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String accessToken;
	
}
