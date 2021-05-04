package com.example.iwimhub.data.model;

public class UserModel {
    protected String firstName, lastName;
    protected String photoUl;

    public UserModel(){}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhotoUl() {
        return photoUl;
    }

    public void setPhotoUl(String photoUl) {
        this.photoUl = photoUl;
    }

}
