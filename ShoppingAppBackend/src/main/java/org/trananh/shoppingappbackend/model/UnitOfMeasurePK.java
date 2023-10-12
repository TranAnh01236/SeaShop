package org.trananh.shoppingappbackend.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class UnitOfMeasurePK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String product;
	private String baseUnitOfMeasure;
	
	public UnitOfMeasurePK() {
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(baseUnitOfMeasure, product);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UnitOfMeasurePK other = (UnitOfMeasurePK) obj;
		return Objects.equals(baseUnitOfMeasure, other.baseUnitOfMeasure) && Objects.equals(product, other.product);
	}
	
}
