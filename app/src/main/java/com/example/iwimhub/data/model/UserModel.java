package com.example.iwimhub.data.model;

public class UserModel {

    private String uid;
    private String firstName;
    private String lastName;
    private String email;

    public UserModel(String uid, String firstName,String lastName, String email){
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
