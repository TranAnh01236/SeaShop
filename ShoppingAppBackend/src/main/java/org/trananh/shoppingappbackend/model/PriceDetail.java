package org.trananh.shoppingappbackend.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "price_details")
@IdClass(PriceDetailPK.class)
public class PriceDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "price_header_id")
	private PriceHeader priceHeader;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "unit_of_measure_id")
	private UnitOfMeasure unitOfMeasure;
	
	
	@Column(name = "price")
	private double price;


	public PriceHeader getPriceHeader() {
		return priceHeader;
	}


	public void setPriceHeader(PriceHeader priceHeader) {
		this.priceHeader = priceHeader;
	}


	public UnitOfMeasure getUnitOfMeasure() {
		return unitOfMeasure;
	}


	public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public PriceDetail(PriceHeader priceHeader, UnitOfMeasure unitOfMeasure, double price) {
		super();
		this.priceHeader = priceHeader;
		this.unitOfMeasure = unitOfMeasure;
		this.price = price;
	}


	public PriceDetail() {
		super();
	}


	public PriceDetail(PriceHeader priceHeader, UnitOfMeasure unitOfMeasure) {
		super();
		this.priceHeader = priceHeader;
		this.unitOfMeasure = unitOfMeasure;
	}
	
}
