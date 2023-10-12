package org.trananh.shoppingappbackend.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name = "products")
public class Product implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@Column(name = "name", nullable = false, columnDefinition = "nvarchar(255)")
	private String name;
	
	@Column(name = "description", nullable = false, columnDefinition = "nvarchar(max)")
	private String description;
	
	@Column(name = "create_at", updatable = false)
	@CreationTimestamp
	private Timestamp createAt;
	
	@Column(name = "update_at")
	@UpdateTimestamp
	private Timestamp updateAt;
	
	@Column
	private boolean deleted = false;
	
	@Column
	private boolean status = true;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@ManyToOne
	@JoinColumn(name = "create_user_id")
	private User createUser;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private StructureValue category;
	
	@OneToMany(mappedBy = "product")
	private List<UnitOfMeasure> unitOfMeasures;

	public Product() {
		super();
		this.unitOfMeasures = new ArrayList<UnitOfMeasure>();
	}

	public Product(String id) {
		super();
		this.id = id;
		this.unitOfMeasures = new ArrayList<UnitOfMeasure>();
	}

	public Product(String id, String name, String description, Timestamp createAt, Timestamp updateAt, boolean deleted,
			boolean status, String imageUrl, User createUser, StructureValue category) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.createAt = createAt;
		this.updateAt = updateAt;
		this.deleted = deleted;
		this.status = status;
		this.imageUrl = imageUrl;
		this.createUser = createUser;
		this.category = category;
		this.unitOfMeasures = new ArrayList<UnitOfMeasure>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}

	public Timestamp getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Timestamp updateAt) {
		this.updateAt = updateAt;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public StructureValue getCategory() {
		return category;
	}

	public void setCategory(StructureValue category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", createAt=" + createAt
				+ ", updateAt=" + updateAt + ", deleted=" + deleted + ", status=" + status + ", imageUrl=" + imageUrl
				+ ", createUser=" + createUser + ", category=" + category + "]";
	}
	
	
}
