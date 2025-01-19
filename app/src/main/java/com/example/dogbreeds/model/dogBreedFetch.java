package com.example.dogbreeds.model;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Retrofit;

public class dogBreedFetch {
    private final dogBreedsAPI api;

    public dogBreedFetch(Retrofit retrofit) {
        this.api = retrofit.create(dogBreedsAPI.class);
    }

    public void fetchDogBreeds(Callback<List<dogBreedModel>> callback) {
        api.getDogBreeds().enqueue(callback);
    }
}
