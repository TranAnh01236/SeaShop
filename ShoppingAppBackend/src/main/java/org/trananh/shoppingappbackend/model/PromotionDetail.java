package org.trananh.shoppingappbackend.model;

import java.io.Serializable;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "promotion_details")
public class PromotionDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "promotion_detail_id_generator")
	@GenericGenerator(name = "promotion_detail_id_generator", strategy = "org.trananh.shoppingappbackend.ultilities.idGenerator.PromotionDetailIdGenerator")
	private String id;
	
	@Column(name = "product_id", nullable = true)
	private String productId;
	
	@Column(name = "purchase_value", nullable = false)
	private double purchaseValue;
	
	@Column(name = "promotion_value", nullable = false)
	private double promotionValue;
	
	@ManyToOne
	@JoinColumn(name = "promotion_line_id")
	private PromotionLine promotionLine;
	
}
