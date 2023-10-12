package org.trananh.shoppingapp.model;

import java.io.Serializable;

import kotlin.Unit;

public class UnitOfMeasure implements Serializable {
    private BaseUnitOfMeasure baseUnitOfMeasure;
    private Product product;
    private int quantity;
    private String imageUrl;

    public UnitOfMeasure(BaseUnitOfMeasure baseUnitOfMeasure, Product product, int quantity, String imageUrl) {
        this.baseUnitOfMeasure = baseUnitOfMeasure;
        this.product = product;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }

    public UnitOfMeasure(){

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
}
