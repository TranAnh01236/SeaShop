package org.trananh.shoppingappbackend.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "warehouse")
public class Warehouse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "warehouse_id_generator")
	@GenericGenerator(name = "warehouse_id_generator", strategy = "org.trananh.shoppingappbackend.ultilities.idGenerator.WarehouseImportHeaderIdGenerator")
	private String id;
	
	@OneToOne
	@JoinColumn(name = "unit_of_measure_id")
	private UnitOfMeasure unitOfMeasure;
	
	@Column(name = "quantity", nullable = false)
	private int quantity;
	
	@OneToMany(mappedBy = "warehouse")
	private List<InventoryDetail> inventoryDetails;
}
