package org.trananh.shoppingappbackend.model;

import java.io.Serializable;
import java.sql.Date;
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
@Table(name = "users")
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	@Column(name = "first_name", columnDefinition = "nvarchar(255)", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false, columnDefinition = "nvarchar(255)")
	private String lastName;
	
	@Column(name = "login_name", nullable = false)
	private String loginName;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;
	
	@Column(name = "email", nullable = true)
	private String email;
	
	@Column(name = "gender")
	private int gender = 0;
	
	@Column(name = "type", nullable = false)
	private int type;
	
	@Column(name = "create_at", updatable = false)
	@CreationTimestamp
	private Timestamp createAt;
	
	@Column(name = "update_at")
	@UpdateTimestamp
	private Timestamp updateAt;
	
	@Column(name = "day_of_birth", nullable = true)
	private Date dayOfBirth;
	
	@Column(name = "address_detail", nullable = true, columnDefinition = "nvarchar(max)")
	private String addressDetail;
	
	@ManyToOne
	@JoinColumn(name = "address_id")
	private StructureValue address;
	
	@OneToMany(mappedBy = "createUser")
	private List<Product> products;
	
	@OneToMany(mappedBy = "createUser")
	private List<PriceHeader> priceHeaders;
	
	@OneToMany(mappedBy = "createUser")
	private List<PromotionHeader> promotionHeaders;
	
	@OneToMany(mappedBy = "createUser")
	private List<WarehouseImportHeader> warehouseImportHeaders;
	
	@OneToMany(mappedBy = "createUser")
	private List<InventoryHeader> inventoryHeaders;

	public User(String id, String firstName, String lastName, String loginName, String password, String phoneNumber,
			String email, int gender, int type, Date dayOfBirth, String addressDetail, StructureValue address) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.loginName = loginName;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.gender = gender;
		this.type = type;
		this.dayOfBirth = dayOfBirth;
		this.addressDetail = addressDetail;
		this.address = address;
		this.products = new ArrayList<Product>();
		this.priceHeaders = new ArrayList<PriceHeader>();
		this.promotionHeaders = new ArrayList<PromotionHeader>();
		this.warehouseImportHeaders = new ArrayList<WarehouseImportHeader>();
		this.inventoryHeaders = new ArrayList<InventoryHeader>();
	}

	public User(String id) {
		super();
		this.id = id;
		this.products = new ArrayList<Product>();
		this.priceHeaders = new ArrayList<PriceHeader>();
		this.promotionHeaders = new ArrayList<PromotionHeader>();
		this.warehouseImportHeaders = new ArrayList<WarehouseImportHeader>();
		this.inventoryHeaders = new ArrayList<InventoryHeader>();
	}

	public User() {
		super();
		this.products = new ArrayList<Product>();
		this.priceHeaders = new ArrayList<PriceHeader>();
		this.promotionHeaders = new ArrayList<PromotionHeader>();
		this.warehouseImportHeaders = new ArrayList<WarehouseImportHeader>();
		this.inventoryHeaders = new ArrayList<InventoryHeader>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
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

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public void setUpdateAt(Timestamp updateAt) {
		this.updateAt = updateAt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getDayOfBirth() {
		return dayOfBirth;
	}

	public void setDayOfBirth(Date dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}

	public StructureValue getAddress() {
		return address;
	}

	public void setAddress(StructureValue address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", loginName=" + loginName
				+ ", password=" + password + ", phoneNumber=" + phoneNumber + ", email=" + email + ", gender=" + gender
				+ ", type=" + type + ", createAt=" + createAt + ", updateAt=" + updateAt + ", dayOfBirth=" + dayOfBirth
				+ ", addressDetail=" + addressDetail + ", address=" + address + "]";
	}
	
}
