package com.example.iwimhub.data.model;

public class StudentModel extends UserModel{
    //email:id
    private String cne, apogee;

    public StudentModel(){
        super();
        role("STUDENT");
    }

    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }

    public String getApogee() {
        return apogee;
    }

    public void setApogee(String apogee) {
        this.apogee = apogee;
    }

}
