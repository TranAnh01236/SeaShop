package org.trananh.shoppingapp.model;

import java.io.Serializable;

public class PriceDetail implements Serializable {
    private PriceHeader priceHeader;
    private UnitOfMeasure unitOfMeasure;
    private double price;

    public PriceDetail() {
    }

    public PriceDetail(PriceHeader priceHeader, UnitOfMeasure unitOfMeasure, double price) {
        this.priceHeader = priceHeader;
        this.unitOfMeasure = unitOfMeasure;
        this.price = price;
    }

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

    @Override
    public String toString() {
        return "PriceDetail{" +
                "priceHeader=" + priceHeader +
                ", unitOfMeasure=" + unitOfMeasure +
                ", price=" + price +
                '}';
    }
}
