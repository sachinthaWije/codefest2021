package com.lec.rider.model;

public class Customer {
    String name,email,phone,address,googleUId,state;


    public Customer() {
    }

    public Customer(String name, String email, String phone, String address, String googleUId, String state) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.googleUId = googleUId;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGoogleUId() {
        return googleUId;
    }

    public void setGoogleUId(String googleUId) {
        this.googleUId = googleUId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}