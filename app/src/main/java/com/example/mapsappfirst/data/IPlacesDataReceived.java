package com.example.mapsappfirst.data;

import com.example.mapsappfirst.locations.LocationModel;

import java.util.ArrayList;

public interface IPlacesDataReceived {

    public void onPlacesDataReceived (ArrayList<LocationModel> results_);

}
