package org.trananh.shoppingappbackend.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "hierarchy_structures")
public class HierarchyStructure implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name = "value", nullable = false, columnDefinition = "nvarchar(max)")
	private String value;
	
	@OneToMany(mappedBy = "type")
	private List<StructureValue> structureValues;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public HierarchyStructure(int id, String value) {
		super();
		this.id = id;
		this.value = value;
		this.structureValues = new ArrayList<StructureValue>();
	}

	public HierarchyStructure(int id) {
		super();
		this.id = id;
		this.structureValues = new ArrayList<StructureValue>();
	}

	public HierarchyStructure() {
		super();
		this.structureValues = new ArrayList<StructureValue>();
	}

	@Override
	public String toString() {
		return "HierarchyStructure [id=" + id + ", value=" + value + "]";
	}
	
}
