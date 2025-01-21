package com.example.dogbreeds.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class dogBreedModel implements Serializable {
    private String life_span;
    private String bred_for;
    private String name;
    private String temperament;
    @SerializedName("url")
    private String image;
    private String origin;


    //GETTERS
    public String getName() {
        return name;
    }
    public String getTemperament(){
        return temperament;
    }
    public String getImage() {
        return image;

    }
    public String getLifeSpan() {
        return life_span;
    }

    public String getBreedFor() {
        return bred_for;
    }

    public String getOrigin() {
        return origin;
    }
}
