package com.ecommerce.models;

import com.ecommerce.utils.AppException;

import java.sql.Timestamp;

public class Product {
    private int id;
    private int stocks;
    private String title;
    private double price;
    private String description;
    private Category category;


    public Product(int id, String title, String description, double price, int stocks, Category category) {
        this.id = id;
        this.stocks = stocks;
        this.title = title;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public int getStocks() {
        return stocks;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }
}
