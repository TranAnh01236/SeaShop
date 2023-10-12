package org.trananh.shoppingappbackend.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "unit_of_measures")
@IdClass(UnitOfMeasurePK.class)
public class UnitOfMeasure implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "base_unit_of_measure_id")
	private BaseUnitOfMeasure baseUnitOfMeasure;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@OneToMany(mappedBy = "unitOfMeasure")
	private List<PriceDetail> priceDetails;
	
	@OneToMany(mappedBy = "unitOfMeasure")
	private List<WarehouseHeaderDetail> warehouseHeaderDetails;
	
	public UnitOfMeasure() {
		this.priceDetails = new ArrayList<PriceDetail>();
		this.warehouseHeaderDetails = new ArrayList<WarehouseHeaderDetail>();
	}

	public UnitOfMeasure(BaseUnitOfMeasure baseUnitOfMeasure, Product product) {
		super();
		this.baseUnitOfMeasure = baseUnitOfMeasure;
		this.product = product;
		this.priceDetails = new ArrayList<PriceDetail>();
		this.warehouseHeaderDetails = new ArrayList<WarehouseHeaderDetail>();
	}

	public UnitOfMeasure(BaseUnitOfMeasure baseUnitOfMeasure, Product product, int quantity, String imageUrl) {
		super();
		this.baseUnitOfMeasure = baseUnitOfMeasure;
		this.product = product;
		this.quantity = quantity;
		this.imageUrl = imageUrl;
	}

	public BaseUnitOfMeasure getBaseUnitOfMeasure() {
		return baseUnitOfMeasure;
	}

	public void setBaseUnitOfMeasure(BaseUnitOfMeasure baseUnitOfMeasure) {
		this.baseUnitOfMeasure = baseUnitOfMeasure;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "UnitOfMeasure [baseUnitOfMeasure=" + baseUnitOfMeasure + ", product=" + product + ", quantity="
				+ quantity + ", imageUrl=" + imageUrl + "]";
	}
	
}
