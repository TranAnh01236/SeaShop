package org.trananh.shoppingappbackend.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "base_unit_of_measures")
public class BaseUnitOfMeasure implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@Column(name = "value", columnDefinition = "nvarchar(255)")
	private String value;
	
	@OneToMany(mappedBy = "baseUnitOfMeasure")
	private List<UnitOfMeasure> unitOfMeasures;
	
	public BaseUnitOfMeasure() {
		this.unitOfMeasures = new ArrayList<UnitOfMeasure>();
	}

	public BaseUnitOfMeasure(String id) {
		super();
		this.id = id;
		this.unitOfMeasures = new ArrayList<UnitOfMeasure>();
	}

	public BaseUnitOfMeasure(String id, String value) {
		super();
		this.id = id;
		this.value = value;
		this.unitOfMeasures = new ArrayList<UnitOfMeasure>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "BaseUnitOfMeasure [id=" + id + ", value=" + value + "]";
	}
}
