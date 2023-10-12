package org.trananh.shoppingappbackend.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
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
@Table(name = "inventory_headers")
public class InventoryHeader implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "inventory_header_id_generator")
	@GenericGenerator(name = "inventory_header_id_generator", strategy = "org.trananh.shoppingappbackend.ultilities.idGenerator.InventoryHeaderIdGenerator")
	private String id;
	
	@Column(name = "description", nullable = true)
	private String desscription;
	
	@Column(name = "create_date", nullable = false)
	@CreationTimestamp
	private Timestamp createDate;
	
	@ManyToOne
	@JoinColumn(name = "create_user_id")
	private User createUser;
	
	@OneToMany(mappedBy = "inventoryHeader")
	private List<InventoryDetail> inventoryDetails;
}
