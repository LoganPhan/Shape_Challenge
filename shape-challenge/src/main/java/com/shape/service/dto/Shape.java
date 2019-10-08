package com.shape.service.dto;

import java.io.Serializable;
import java.util.Set;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class Shape implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private Set<Attribute> attributes;
	private Set<Formular> formulars;
	private short level;
}
