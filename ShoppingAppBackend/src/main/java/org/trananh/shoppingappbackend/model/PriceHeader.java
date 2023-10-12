package org.trananh.shoppingappbackend.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
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
@Table(name = "price_headers")
public class PriceHeader implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "price_id_generator")
	@GenericGenerator(name = "price_id_generator", strategy = "org.trananh.shoppingappbackend.ultilities.idGenerator.PriceIdGenerator")
	private String id;
	
	@Column(name = "description", nullable = true)
	private String description;
	
	@Column(name = "start_date")
	private Date startDate;
	
	@Column(name = "end_date")
	private Date endDate;
	
	@Column(name = "status")
	private boolean status = true;
	
	@ManyToOne
	@JoinColumn(name = "create_user_id")
	private User createUser;	
	
	@OneToMany(mappedBy = "priceHeader")
	private List<PriceDetail> priceDetails;
	
	public PriceHeader() {
		this.priceDetails = new ArrayList<PriceDetail>();
	}

	public PriceHeader(String id) {
		super();
		this.id = id;
		this.priceDetails = new ArrayList<PriceDetail>();
	}

	public PriceHeader(String id, String description, Date startDate, Date endDate, boolean status, User createUser) {
		super();
		this.id = id;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.createUser = createUser;
		this.priceDetails = new ArrayList<PriceDetail>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	@Override
	public String toString() {
		return "PriceHeader [id=" + id + ", description=" + description + ", startDate=" + startDate + ", endDate="
				+ endDate + ", status=" + status + ", createUser=" + createUser + "]";
	}
	
}
