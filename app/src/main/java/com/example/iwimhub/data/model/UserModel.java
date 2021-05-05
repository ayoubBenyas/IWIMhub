package com.example.iwimhub.data.model;

public class UserModel {
    protected String firstName, lastName;
    protected String photoUrl;

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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUl) {
        this.photoUrl = photoUrl;
    }

    @Override
    public String toString() {
        return firstName+" "+lastName;
    }
}
