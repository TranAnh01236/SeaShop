package org.trananh.shoppingapp.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private String id;
    private String firstName;
    private String lastName;
    private String loginName;
    private String password;
    private String phoneNumber;
    private String email;
    private Timestamp createAt;
    private Timestamp updateAt;
    private int gender;
    private int type;
    private Date dayOfBirth;
    private String addressDetail;
    private StructureValue address;
    private List<Product> products;
    private List<PriceHeader> priceHeaders;

    public User(String id, String firstName, String lastName, String loginName, String password, String addressDetail, String phoneNumber, String email, int gender, int type, Date dayOfBirth, StructureValue address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.loginName = loginName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.addressDetail = addressDetail;
        this.gender = gender;
        this.type = type;
        this.dayOfBirth = dayOfBirth;
        this.address = address;
        this.products = new ArrayList<>();
        this.priceHeaders = new ArrayList<>();
    }

    public User(String id){
        this.id = id;
        this.products = new ArrayList<>();
        this.priceHeaders = new ArrayList<>();
    }

    public User(){
        this.products = new ArrayList<>();
        this.priceHeaders = new ArrayList<>();
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

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
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
        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", gender=" + gender +
                ", type=" + type +
                ", dayOfBirth=" + dayOfBirth +
                ", addressDetail='" + addressDetail + '\'' +
                ", address=" + address +
                '}';
    }
}
