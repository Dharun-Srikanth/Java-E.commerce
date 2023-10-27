package com.ecommerce.models;

import com.ecommerce.utils.AppException;

import java.sql.Timestamp;

public class Product {
    private Timestamp id;
    private int stocks;
    private String title;
    private double price;
    private String description;

    public Timestamp getId() {
        return id;
    }

    public void setId(Timestamp id) {
        this.id = id;
    }

    public int getStocks() {
        return stocks;
    }

    public void setStocks(int stocks) {
        this.stocks = stocks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws AppException {
        if(price<=0){
            throw new AppException("Price Cannot be negative");
        }
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
