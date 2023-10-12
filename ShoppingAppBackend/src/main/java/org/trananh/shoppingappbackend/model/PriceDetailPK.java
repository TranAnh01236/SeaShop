package org.trananh.shoppingappbackend.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class PriceDetailPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String priceHeader;
	private int unitOfMeasure;
	public PriceDetailPK() {
		super();
	}
	@Override
	public int hashCode() {
		return Objects.hash(priceHeader, unitOfMeasure);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PriceDetailPK other = (PriceDetailPK) obj;
		return Objects.equals(priceHeader, other.priceHeader) && unitOfMeasure == other.unitOfMeasure;
	}
	
}
