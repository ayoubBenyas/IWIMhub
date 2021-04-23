package com.example.iwimhub.data.model;

public class StudentModel extends UserModel{

    private String cne;
    private String apogee;

    public StudentModel(String uid, String firstName,String lastName, String cne, String apogee, String email){
        super( uid, firstName, lastName, email);
        this.cne = cne;
        this.apogee = apogee;
    }

    public String getCne() {
        return cne;
    }

    public String getApogee() {
        return apogee;
    }
}
