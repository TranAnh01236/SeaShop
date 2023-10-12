package org.trananh.shoppingappbackend.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class WarehouseHeaderDetailPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String warehouseImportHeader;
	private int unitOfMeasure;
	
	public WarehouseHeaderDetailPK() {}

	@Override
	public int hashCode() {
		return Objects.hash(unitOfMeasure, warehouseImportHeader);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WarehouseHeaderDetailPK other = (WarehouseHeaderDetailPK) obj;
		return unitOfMeasure == other.unitOfMeasure
				&& Objects.equals(warehouseImportHeader, other.warehouseImportHeader);
	}

	
	
}
