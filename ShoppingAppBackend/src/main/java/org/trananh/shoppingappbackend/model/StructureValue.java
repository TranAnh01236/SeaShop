package org.trananh.shoppingappbackend.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "structure_values")
public class StructureValue {

	@Id
	private String id;
	
	@Column(name = "value", nullable = false, columnDefinition = "nvarchar(max)")
	private String value;
	
	@Column(name = "level", nullable = false)
	private int level;
	
	@Column(name = "parent_id", nullable = true)
	private String parentId;
	
	@Column(name = "description", nullable = true, columnDefinition = "nvarchar(max)")
	private String description;
	
	@Column(name = "image_url", nullable = true)
	private String imageUrl;
	
	@ManyToOne
	@JoinColumn(name = "type")
	private HierarchyStructure type;
	
	@OneToMany(mappedBy = "address")
	private List<User> users;
	
	@OneToMany(mappedBy = "category")
	private List<Product> products;

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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public HierarchyStructure getType() {
		return type;
	}

	public void setType(HierarchyStructure type) {
		this.type = type;
	}

	public StructureValue() {
		super();
		this.users = new ArrayList<User>();
		this.products = new ArrayList<Product>();
	}

	public StructureValue(String id) {
		super();
		this.id = id;
		this.users = new ArrayList<User>();
		this.products = new ArrayList<Product>();
	}

	public StructureValue(String id, String value, int level, String parentId, String description, String imageUrl,
			HierarchyStructure type) {
		super();
		this.id = id;
		this.value = value;
		this.level = level;
		this.parentId = parentId;
		this.description = description;
		this.imageUrl = imageUrl;
		this.type = type;
		this.users = new ArrayList<User>();
		this.products = new ArrayList<Product>();
	}
	
}
