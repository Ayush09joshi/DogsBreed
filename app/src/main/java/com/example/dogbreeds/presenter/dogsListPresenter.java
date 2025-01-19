package com.example.dogbreeds.presenter;

import com.example.dogbreeds.model.dogBreedFetch;
import com.example.dogbreeds.model.dogBreedModel;
import com.example.dogbreeds.view.dogList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class dogsListPresenter {
    private final dogBreedFetch model;
    private final dogList view;

    public dogsListPresenter(dogBreedFetch model, dogList view) {
        this.model = model;
        this.view = view;
    }

    public void loadDogBreeds() {
        view.showLoading();
        model.fetchDogBreeds(new Callback<>() {
            @Override
            public void onResponse(Call<List<dogBreedModel>> call, Response<List<dogBreedModel>> response) {
                view.hideLoading();
                if (response.isSuccessful()) {
                    view.displayDogBreeds(response.body());
                } else {
                    view.showError("Failed to load data");
                }
            }

            @Override
            public void onFailure(Call<List<dogBreedModel>> call, Throwable t) {
                view.hideLoading();
                view.showError(t.getMessage());
            }
        });
    }
}

