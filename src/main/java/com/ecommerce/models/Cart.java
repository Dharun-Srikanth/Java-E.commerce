package com.ecommerce.models;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Cart {
    private Timestamp id;
    private ArrayList<Product> cartProducts;

    public Timestamp getId() {
        return id;
    }

    public void setId(Timestamp id) {
        this.id = id;
    }

    public ArrayList<Product> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(ArrayList<Product> cartProducts) {
        this.cartProducts = cartProducts;
    }
}
