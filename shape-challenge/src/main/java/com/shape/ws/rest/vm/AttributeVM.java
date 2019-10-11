package com.shape.ws.rest.vm;

import java.io.Serializable;

import com.shape.ws.rest.vm.FormularVM.FormularVMBuilder;

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
public class AttributeVM implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String dataType;
	private Boolean required;
	private String value;
}
