package com.example.dogbreeds.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.dogbreeds.model.dogBreedModel;

import com.example.dogbreeds.R;

import com.example.dogbreeds.presenter.dogDetailsPresenter;

import java.io.Serializable;

public class DogDetailsFragment extends Fragment implements dogDetails {
    private ImageView imageView;
    private TextView nameTextView, breedGroupTextView, temparmentTextView,lifeSpanTextView;


    // To receive the DogBreed object
    private static final String DOG_BREED_KEY = "dogBreed";

    public DogDetailsFragment() {
        // Required empty public constructor
    }

    public static DogDetailsFragment newInstance(dogBreedModel dogBreed) {
        DogDetailsFragment fragment = new DogDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(DOG_BREED_KEY, (Serializable) dogBreed);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dog_details, container, false);

        imageView = view.findViewById(R.id.imageViewDog);
        nameTextView = view.findViewById(R.id.textViewName);
        breedGroupTextView = view.findViewById(R.id.dogOrigin);
        temparmentTextView = view.findViewById(R.id.dogTemperament);
        lifeSpanTextView = view.findViewById(R.id.dogBestSuits);

        Toolbar toolbar = view.findViewById(R.id.dog_breed_details_toolbar);
        toolbar.setTitle(getString(R.string.details_title));
        toolbar.setNavigationOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack(); // Just navigate back
        });

        // Get the DogBreed object passed via arguments
        if (getArguments() != null) {
            dogBreedModel dogBreed = (dogBreedModel) getArguments().getSerializable(DOG_BREED_KEY);
            dogDetailsPresenter presenter = new dogDetailsPresenter(this);
            presenter.loadDogBreedDetails(dogBreed);
        }
        return view;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void displayDogBreedDetails(dogBreedModel dogBreed) {
        // Display the dog breed details
        nameTextView.setText(dogBreed.getName());
        breedGroupTextView.setText("Originated From: " + dogBreed.getOrigin());
        temparmentTextView.setText("Temperaments Are: " + dogBreed.getTemperament());
        lifeSpanTextView.setText("Best Suitable For: " + dogBreed.getBreedFor());

        Glide.with(getContext()).load(dogBreed.getImage()).into(imageView);
    }
}
