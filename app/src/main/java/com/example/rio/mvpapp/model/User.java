package com.example.rio.mvpapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("phone")
    private String phone;
    @SerializedName("email")
    private String email;
    @SerializedName("tokenFB")
    private String tokenFB;
    @SerializedName("tokenGG")
    private String tokenGG;
    @SerializedName("password")
    private String password;
    @SerializedName("birthDay")
    private String birthDay;
    @SerializedName("address")
    private String address;
    @SerializedName("avatar")
    private String avatar;
    @SerializedName("createAt")
    private String createAt;
    @SerializedName("updatedAt")
    private String updatedAt;
    @SerializedName("infoStatus")
    private int infoStatus;
    @SerializedName("gender")
    private int gender;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", tokenFB='" + tokenFB + '\'' +
                ", tokenGG='" + tokenGG + '\'' +
                ", password='" + password + '\'' +
                ", birthDay='" + birthDay + '\'' +
                ", address='" + address + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createAt='" + createAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", infoStatus=" + infoStatus +
                ", gender=" + gender +
                '}';
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getInfoStatus() {
        return infoStatus;
    }

    public void setInfoStatus(int infoStatus) {
        this.infoStatus = infoStatus;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTokenFB() {
        return tokenFB;
    }

    public void setTokenFB(String tokenFB) {
        this.tokenFB = tokenFB;
    }

    public String getTokenGG() {
        return tokenGG;
    }

    public void setTokenGG(String tokenGG) {
        this.tokenGG = tokenGG;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
