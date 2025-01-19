package com.example.dogbreeds.presenter;

import com.example.dogbreeds.model.dogBreedModel;
import com.example.dogbreeds.view.dogDetails;

public class dogDetailsPresenter {
    private final dogDetails view;

    public dogDetailsPresenter(dogDetails view) {
        this.view = view;
    }

    public void loadDogBreedDetails(dogBreedModel dogBreed) {
        view.displayDogBreedDetails(dogBreed);
    }

}
