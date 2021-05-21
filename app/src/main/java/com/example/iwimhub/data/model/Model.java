package com.example.iwimhub.data.model;

public class Model {

    private String ID;
    
    public String id(){
        return ID;
    }

    public String id(String id){
        this.ID = id;
        return null;
    }

    public String getEmail(){
        return id();
    }

}
