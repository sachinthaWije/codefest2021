package com.lec.customerapp.mdel;

public class Tickect {
    String type,title,body,userId,userName;

    public Tickect() {
    }

    public Tickect(String type, String title, String body, String userId, String userName) {
        this.type = type;
        this.title = title;
        this.body = body;
        this.userId = userId;
        this.userName = userName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
