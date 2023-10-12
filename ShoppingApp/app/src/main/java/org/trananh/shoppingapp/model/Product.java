package org.trananh.shoppingapp.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class Product implements Serializable {
    private String id;
    private String name;
    private String description;
    private Timestamp createAt;
    private Timestamp updateAt;
    private boolean deleted;
    private boolean status;
    private String imageUrl;
    private User createUser;
    private StructureValue category;
    private List<UnitOfMeasure> unitOfMeasures;
    private List<PriceDetail> priceDetails;

    public Product(String id, String name, String description, Timestamp createAt, Timestamp updateAt, boolean deleted, boolean status, String imageUrl, User createUser, StructureValue category) {
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
    }

    public Product(){

    }

    public Product(String id) {
        this.id = id;
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
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", deleted=" + deleted +
                ", status=" + status +
                ", imageUrls='" + imageUrl + '\'' +
                ", createUser=" + createUser +
                ", category=" + category +
                '}';
    }
}
