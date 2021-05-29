package com.example.iwimhub.data.model;

public class UserModel extends Model{
    protected String firstName, lastName;
    protected String photoUrl;
    protected String ROLE;

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

    public String role() {
        return ROLE;
    }

    public String role(String role) {
        this.ROLE = role;
        return null;
    }

    @Override
    public String toString() {
        return firstName+" "+lastName;
    }
}
