package com.lec.customerapp.mdel;

public class Rider {
    String name,birthday,address,email,phone,driver_license_number;
    String driver_photo,driver_license_photo,vehicle_category;

    public Rider() {
    }

    public Rider(String name, String birthday, String address, String email, String phone, String driver_license_number) {
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.driver_license_number = driver_license_number;
    }

    public Rider(String name, String birthday, String address, String email, String phone, String driver_license_number, String driver_photo, String driver_license_photo, String vehicle_category) {
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.driver_license_number = driver_license_number;
        this.driver_photo = driver_photo;
        this.driver_license_photo = driver_license_photo;
        this.vehicle_category = vehicle_category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getDriver_license_number() {
        return driver_license_number;
    }

    public void setDriver_license_number(String driver_license_number) {
        this.driver_license_number = driver_license_number;
    }

    public String getDriver_photo() {
        return driver_photo;
    }

    public void setDriver_photo(String driver_photo) {
        this.driver_photo = driver_photo;
    }

    public String getDriver_license_photo() {
        return driver_license_photo;
    }

    public void setDriver_license_photo(String driver_license_photo) {
        this.driver_license_photo = driver_license_photo;
    }

    public String getVehicle_category() {
        return vehicle_category;
    }

    public void setVehicle_category(String vehicle_category) {
        this.vehicle_category = vehicle_category;
    }
}
