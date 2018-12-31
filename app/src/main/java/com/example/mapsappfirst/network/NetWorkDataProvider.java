package com.example.mapsappfirst.network;

import android.os.AsyncTask;

import com.example.mapsappfirst.data.IPlacesDataReceived;
import com.example.mapsappfirst.data.Place;
import com.example.mapsappfirst.locations.LocationModel;
import com.example.mapsappfirst.repository.PlaceRepository;
import com.example.mapsappfirst.ui.NearByPlacesApplication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetWorkDataProvider {

    // i can replace thevlag and ln with Location location_

    public void getPlacesByLocation(double lat, double lng, IPlacesDataReceived resultListener_) {

        GetPlacesByLocationAsyncTask getPlacesByLocationAsyncTask = new GetPlacesByLocationAsyncTask( resultListener_ );
        getPlacesByLocationAsyncTask.execute( lat,lng );

    }

    private class GetPlacesByLocationAsyncTask extends AsyncTask<Double, Integer, IPlacesDataReceived> {

        private ArrayList<LocationModel> mLocationModels;
        private IPlacesDataReceived mIPlacesDataReceived;
        public GetPlacesByLocationAsyncTask (IPlacesDataReceived iPlacesDataReceived) {
            mIPlacesDataReceived = iPlacesDataReceived;
        }


        @Override
        protected IPlacesDataReceived doInBackground(Double... doubles) {
            OkHttpClient client = new OkHttpClient();
            // i need to change the radius from the option  and get a key with a code to the manifest
            String urlQuery = " https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+doubles[0] +"," +doubles[1]+ "&radius=1500&key=AIzaSyDc7oNJ8FQZc6xmDVEvj-vewU5-ohnlwR0";
            Request request = new Request.Builder()
                    .url( urlQuery )
                    .build();

            Response response = null;
            try {
                response = client.newCall( request ).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!response.isSuccessful()) try {
                throw new IOException( "Unexpected code " + response );
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                mLocationModels = getLocationListFromJson( response.body().string() );
                PlaceRepository placeRepository = new PlaceRepository( NearByPlacesApplication.getApplication() );
                ArrayList <Place> listPlaces = new ArrayList<>(  );
                for (LocationModel locationModel : mLocationModels) {

                    String photoUrl ="https://imgplaceholder.com/420x320/ff7f7f/333333/fa-image";
                    if(locationModel.getPhotos()!=null&&locationModel.getPhotos().size()>0)
                    {
                        photoUrl  =locationModel.getPhotos().get(0).getPhoto_reference();
                    }
                    Place place = new Place( locationModel.getName(),locationModel.getGeometry().getLocation().getLatitude(),locationModel.getGeometry().getLocation().getLongitude(),photoUrl,true,true,locationModel.getVicinity());
                    listPlaces.add( place );
                }
                placeRepository.deleteLastSearch ();

                placeRepository.insert( listPlaces );

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        private ArrayList<LocationModel> getLocationListFromJson(String jsonResponse) {
            List<LocationModel> stubMovieData;
            Gson gson = new GsonBuilder().create();
            LocationResponse response = gson.fromJson( jsonResponse, LocationResponse.class );
            stubMovieData = response.results;
            ArrayList<LocationModel> arrList = new ArrayList<>();
            arrList.addAll( stubMovieData );
            return arrList;

        }

        @Override
        protected void onPostExecute(IPlacesDataReceived iPlacesDataReceived) {
            mIPlacesDataReceived.onPlacesDataReceived( mLocationModels );
        }

        public class LocationResponse {

            private List<LocationModel> results;

        }
    }
}
