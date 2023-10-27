package com.ecommerce.models;

import java.util.ArrayList;

public class User {
    private int id;
    private String email;
    private String password;
    private Role role;
    private String name;

    private Cart userCart;
    private ArrayList<Order> orders;

    public Cart getUserCart() {
        return userCart;
    }

    public void setUserCart(Cart cart) {
        this.userCart = cart;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
