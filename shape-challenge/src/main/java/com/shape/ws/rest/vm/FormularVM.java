package com.shape.ws.rest.vm;

import java.io.Serializable;
import java.util.Set;

import com.shape.ws.rest.vm.ShapeVM.ShapeVMBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class FormularVM implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String pattern;
	private String value;
}
