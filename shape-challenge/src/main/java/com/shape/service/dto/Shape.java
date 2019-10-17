package com.shape.service.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import com.shape.service.dto.Formular.FormularBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shape implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private Set<Attribute> attributes;
	private Set<Formular> formulars;

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Shape shape = (Shape) o;
        if (shape.getName() == null || getName() == null) {
            return false;
        }
        return Objects.equals(getId(), shape.getId());
    }
	
	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}
}
