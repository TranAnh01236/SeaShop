package org.trananh.shoppingappbackend.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "warehouse_import_headers")
public class WarehouseImportHeader implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "warehouse_import_header_id_generator")
	@GenericGenerator(name = "warehouse_import_header_id_generator", strategy = "org.trananh.shoppingappbackend.ultilities.idGenerator.WarehouseImportHeaderIdGenerator")
	private String id;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "date")
	private Timestamp date;
	
	@ManyToOne
	@JoinColumn(name = "create_user_id")
	private User createUser;
	
	@OneToMany(mappedBy = "warehouseImportHeader")
	private List<WarehouseHeaderDetail> warehouseHeaderDetails;
}
