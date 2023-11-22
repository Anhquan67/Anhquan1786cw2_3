package com.example.exercise_three_cw2.model;

public class ContactModel {
    private String uuid;
    private String name;
    private String dob;
    private String email;
    private Integer avatar;

    public ContactModel(String uuid, String name, String dob, String email, Integer avatar) {
        this.uuid = uuid;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.avatar = avatar;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAvatar() {
        return avatar;
    }

    public void setAvatar(Integer avatar) {
        this.avatar = avatar;
    }
}
