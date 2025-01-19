package com.example.dogbreeds.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.dogbreeds.adapter.DogBreedAdapter;
import com.example.dogbreeds.model.dogBreedFetch;
import com.example.dogbreeds.model.dogBreedModel;
import com.example.dogbreeds.presenter.dogsListPresenter;
import com.example.dogbreeds.R;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class dogsListFragment extends Fragment implements dogList {
    private RecyclerView recyclerView;
    private dogsListPresenter presenter;
    private ProgressBar progressBar;
    private ConstraintLayout noInternetLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.fragment_dog_list, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.dogBreedListRecyclerView);
        noInternetLayout = view.findViewById(R.id.noInternetLayout);
        Button tryAgainButton = view.findViewById(R.id.tryAgainButton);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // setup toolbar
        Toolbar toolbar = view.findViewById(R.id.show_dog_breeds_toolbar);
        toolbar.setTitle(getString(R.string.dog_breeds_title));
        // Setup Retrofit
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://raw.githubusercontent.com/DevTides/DogsApi/master/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Instantiate the data and presenter
        dogBreedFetch data = new dogBreedFetch(retrofit);
        presenter = new dogsListPresenter(data, this);
        tryAgainButton.setOnClickListener(v -> {
            noInternetLayout.setVisibility(View.GONE);
            presenter.loadDogBreeds(); // Retry API call
            recyclerView.setVisibility(View.VISIBLE);
        });
        // Load data
        presenter.loadDogBreeds();
        return view;
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void displayDogBreeds(List<dogBreedModel> dogBreeds) {
        recyclerView.setAdapter(new DogBreedAdapter(dogBreeds, this::onDogBreedClick));
    }

    @Override
    public void showError(String message) {
        noInternetLayout.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    private void onDogBreedClick(dogBreedModel dogBreed) {
        // Replace the fragment dynamically.
       DogDetailsFragment dogDetailsFragment = DogDetailsFragment.newInstance(dogBreed);
        // Begin a fragment transaction to switch to the details fragment
       requireActivity().getSupportFragmentManager().beginTransaction()
               .replace(R.id.fragment_container, dogDetailsFragment)
                .addToBackStack(null) // So the user can go back
                .commit();
    }
}
