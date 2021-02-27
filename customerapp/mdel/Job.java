package com.lec.customerapp.mdel;


import com.google.type.LatLng;

import java.util.Date;

public class Job {
    double startLocation_lat;
    double startLocation_log;
    double endLocation_lat;
    double endLocation_log;
    Customer jobPlacedBy;
    Date jobCreatedAt;
    String estimationPrice;
    String durationStringAttr;
    String date;

    String status;
    Date statuesTime;
    String custerName;
    String custerEmail;
    String customer_phone;

    String riderDocId;
    String customerDocId;

    double currentRider_lat;
    double currentRider_log;

    double currentCustomer_lat;
    double currentCustomer_log;


    LatLng Rider_Current_Location;

    public LatLng getRider_Current_Location() {
        return Rider_Current_Location;
    }

    public void setRider_Current_Location(LatLng rider_Current_Location) {
        Rider_Current_Location = rider_Current_Location;
    }

    public Job() {
    }

    public double getCurrentRider_lat() {
        return currentRider_lat;
    }

    public void setCurrentRider_lat(double currentRider_lat) {
        this.currentRider_lat = currentRider_lat;
    }

    public double getCurrentRider_log() {
        return currentRider_log;
    }

    public void setCurrentRider_log(double currentRider_log) {
        this.currentRider_log = currentRider_log;
    }

    public double getCurrentCustomer_lat() {
        return currentCustomer_lat;
    }

    public void setCurrentCustomer_lat(double currentCustomer_lat) {
        this.currentCustomer_lat = currentCustomer_lat;
    }

    public double getCurrentCustomer_log() {
        return currentCustomer_log;
    }

    public void setCurrentCustomer_log(double currentCustomer_log) {
        this.currentCustomer_log = currentCustomer_log;
    }

    public Job(double startLocation_lat, double startLocation_log, double endLocation_lat, double endLocation_log, Customer jobPlacedBy, Date jobCreatedAt, String estimationPrice, String durationStringAttr, String date, String status, Date statuesTime, String custerName, String custerEmail, String customer_phone, String riderDocId, String customerDocId, double currentRider_lat, double currentRider_log, double currentCustomer_lat, double currentCustomer_log, LatLng rider_Current_Location) {
        this.startLocation_lat = startLocation_lat;
        this.startLocation_log = startLocation_log;
        this.endLocation_lat = endLocation_lat;
        this.endLocation_log = endLocation_log;
        this.jobPlacedBy = jobPlacedBy;
        this.jobCreatedAt = jobCreatedAt;
        this.estimationPrice = estimationPrice;
        this.durationStringAttr = durationStringAttr;
        this.date = date;
        this.status = status;
        this.statuesTime = statuesTime;
        this.custerName = custerName;
        this.custerEmail = custerEmail;
        this.customer_phone = customer_phone;
        this.riderDocId = riderDocId;
        this.customerDocId = customerDocId;
        this.currentRider_lat = currentRider_lat;
        this.currentRider_log = currentRider_log;
        this.currentCustomer_lat = currentCustomer_lat;
        this.currentCustomer_log = currentCustomer_log;
        Rider_Current_Location = rider_Current_Location;
    }

    public String getRiderDocId() {
        return riderDocId;
    }

    public void setRiderDocId(String riderDocId) {
        this.riderDocId = riderDocId;
    }

    public String getCustomerDocId() {
        return customerDocId;
    }

    public void setCustomerDocId(String customerDocId) {
        this.customerDocId = customerDocId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public String getCusterEmail() {
        return custerEmail;
    }

    public void setCusterEmail(String custerEmail) {
        this.custerEmail = custerEmail;
    }

    public String getCusterName() {
        return custerName;
    }

    public void setCusterName(String custerName) {
        this.custerName = custerName;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStatuesTime() {
        return statuesTime;
    }

    public void setStatuesTime(Date statuesTime) {
        this.statuesTime = statuesTime;
    }

    public double getStartLocation_lat() {
        return startLocation_lat;
    }

    public void setStartLocation_lat(double startLocation_lat) {
        this.startLocation_lat = startLocation_lat;
    }

    public double getStartLocation_log() {
        return startLocation_log;
    }

    public void setStartLocation_log(double startLocation_log) {
        this.startLocation_log = startLocation_log;
    }

    public double getEndLocation_lat() {
        return endLocation_lat;
    }

    public void setEndLocation_lat(double endLocation_lat) {
        this.endLocation_lat = endLocation_lat;
    }

    public double getEndLocation_log() {
        return endLocation_log;
    }

    public void setEndLocation_log(double endLocation_log) {
        this.endLocation_log = endLocation_log;
    }

    public Customer getJobPlacedBy() {
        return jobPlacedBy;
    }

    public void setJobPlacedBy(Customer jobPlacedBy) {
        this.jobPlacedBy = jobPlacedBy;
    }

    public Date getJobCreatedAt() {
        return jobCreatedAt;
    }

    public void setJobCreatedAt(Date jobCreatedAt) {
        this.jobCreatedAt = jobCreatedAt;
    }

    public String getEstimationPrice() {
        return estimationPrice;
    }

    public void setEstimationPrice(String estimationPrice) {
        this.estimationPrice = estimationPrice;
    }

    public String getDurationStringAttr() {
        return durationStringAttr;
    }

    public void setDurationStringAttr(String durationStringAttr) {
        this.durationStringAttr = durationStringAttr;
    }
}
