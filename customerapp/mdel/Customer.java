package com.lec.customerapp.mdel;

public class Customer {
    String name,email,phone,googleUId,state,nic,gender,profile;


    public Customer() {
    }

    public Customer(String name, String email, String phone, String googleUId, String state, String nic, String gender, String profile) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.googleUId = googleUId;
        this.state = state;
        this.nic = nic;
        this.gender = gender;
        this.profile = profile;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
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
