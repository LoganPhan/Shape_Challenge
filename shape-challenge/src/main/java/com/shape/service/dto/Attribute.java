package com.shape.service.dto;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attribute implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String dataType;
	private Boolean required;
	private String value;
	

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Attribute attribute = (Attribute) o;
        if (attribute.getName() == null || getName() == null) {
            return false;
        }
        return Objects.equals(getName(), attribute.getName());
    }
	@Override
	public int hashCode() {
		return Objects.hashCode(getName());
	}
}
