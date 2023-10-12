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
@Table(name = "promotion_headers")
public class PromotionHeader implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "promotion_header_id_generator")
	@GenericGenerator(name = "promotion_header_id_generator", strategy = "org.trananh.shoppingappbackend.ultilities.idGenerator.PromotionHeaderIdGenerator")
	private String id;
	
	@Column(name = "description", nullable = true)
	private String description;
	
	@Column(name = "start_date", nullable = true)
	private Timestamp startDate;
	
	@Column(name = "end_date", nullable = true)
	private Timestamp endDate;
	
	@Column(name = "status", nullable = false)
	private boolean status = true;
	
	@ManyToOne
	@JoinColumn(name = "create_user_id")
	private User createUser;
	
	@OneToMany(mappedBy = "promotionHeader")
	private List<PromotionLine> promotionLines;
}
