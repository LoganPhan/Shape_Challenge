package com.shape.service.dto;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Formular implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String pattern;
	private String value;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Formular formular = (Formular) o;
        if (formular.getName() == null || getName() == null) {
            return false;
        }
        return Objects.equals(getName(), formular.getName());
    }
	@Override
	public int hashCode() {
		return Objects.hashCode(getName());
	}
}
