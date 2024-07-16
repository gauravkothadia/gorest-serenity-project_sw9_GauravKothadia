package com.gorest.model;

public class UserPojo {
    private int id;
    private String name;
    private String email;
    private String gender;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static UserPojo setUserPojo(String name, String email, String gender, String status) {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);
        return userPojo;
    }

    public static UserPojo setUserPojoPatchMethod(String email, String status) {
        UserPojo userPojo = new UserPojo();
        userPojo.setEmail(email);
        userPojo.setStatus(status);
        return userPojo;
    }
}
