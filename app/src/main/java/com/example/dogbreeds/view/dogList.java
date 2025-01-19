package com.example.dogbreeds.view;

import com.example.dogbreeds.model.dogBreedModel;

import java.util.List;

public interface dogList {
    void showLoading();
    void hideLoading();
    void displayDogBreeds(List<dogBreedModel> dogBreeds);
    void showError(String message);
}
