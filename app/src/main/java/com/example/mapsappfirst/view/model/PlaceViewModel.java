package com.example.mapsappfirst.view.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.mapsappfirst.data.Place;
import com.example.mapsappfirst.repository.PlaceRepository;

import java.util.List;

public class PlaceViewModel extends AndroidViewModel {

    private PlaceRepository mRepository;
    private LiveData<List<Place>> mAllPlaces;

    public PlaceViewModel(Application application) {
        super(application);
        mRepository = new PlaceRepository(application);
        mAllPlaces = mRepository.getAllPlaces();
    }

    public LiveData<List<Place>> getAllPlaces() {
        return mAllPlaces;
    }

    public LiveData <List <Place>> deleteAllPlaces () {
        return mAllPlaces;
    }

    public void insert(Place place) {
        mRepository.insert(place);
    }


}
