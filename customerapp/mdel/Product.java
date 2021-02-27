package com.lec.customerapp.mdel;

public class Product {
    String id,name,price,state,userId;

    public Product() {
    }

    public Product(String id, String name, String price, String state, String userId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.state = state;
        this.userId = userId;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
