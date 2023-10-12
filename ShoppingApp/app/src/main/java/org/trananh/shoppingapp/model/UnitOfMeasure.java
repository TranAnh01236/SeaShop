package org.trananh.shoppingapp.model;

import java.io.Serializable;

import kotlin.Unit;

public class UnitOfMeasure implements Serializable {
    private int id;
    private String value;
    private BaseUnitOfMeasure baseUnitOfMeasure;
    private Product product;
    private int quantity;
    private String imageUrl;

    public UnitOfMeasure(int id, String value, BaseUnitOfMeasure baseUnitOfMeasure, Product product, int quantity, String imageUrl) {
        this.id = id;
        this.value = value;
        this.baseUnitOfMeasure = baseUnitOfMeasure;
        this.product = product;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }

    public UnitOfMeasure(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
        return "UnitOfMeasure{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", baseUnitOfMeasure=" + baseUnitOfMeasure +
                ", product=" + product +
                ", quantity=" + quantity +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
