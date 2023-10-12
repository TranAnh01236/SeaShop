package org.trananh.shoppingappbackend.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class InventoryDetailPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String inventoryHeader;
	private String warehouse;
	
	public InventoryDetailPK() {}

	@Override
	public int hashCode() {
		return Objects.hash(inventoryHeader, warehouse);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InventoryDetailPK other = (InventoryDetailPK) obj;
		return Objects.equals(inventoryHeader, other.inventoryHeader) && Objects.equals(warehouse, other.warehouse);
	}
	
}
