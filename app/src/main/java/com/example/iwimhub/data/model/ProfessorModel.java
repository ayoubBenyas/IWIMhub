package com.example.iwimhub.data.model;

public class ProfessorModel extends UserModel {
    private String matricule;

    public ProfessorModel(){
        super();
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
}
