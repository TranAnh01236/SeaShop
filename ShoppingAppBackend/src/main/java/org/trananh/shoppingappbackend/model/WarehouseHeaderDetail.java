package org.trananh.shoppingappbackend.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "warehouse_import_details")
@IdClass(WarehouseHeaderDetailPK.class)
public class WarehouseHeaderDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "warehouse_import_header_id")
	private WarehouseImportHeader warehouseImportHeader;
	
	@Id
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "base_unit_of_measure_id"),
		@JoinColumn(name = "product_id")
	})
	private UnitOfMeasure unitOfMeasure;
	
	@Column(name = "quantity", nullable = false)
	private int qantity;
}
