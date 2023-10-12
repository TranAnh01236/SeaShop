package org.trananh.shoppingappbackend.model;

import java.io.Serializable;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "promotion_result")
public class PromotionResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "promotion_reult_id_generator")
	@GenericGenerator(name = "promotion_reult_id_generator", strategy = "org.trananh.shoppingappbackend.ultilities.idGenerator.PromotionResultIdGenerator")
	private String id;
	
	@OneToOne
	@JoinColumn(name = "promotion_detail_id")
	private PromotionDetail promotionDetails;
	
}
