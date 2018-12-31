package com.example.mapsappfirst.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mapsappfirst.R;
import com.example.mapsappfirst.adapter.PlacesListAdapter;
import com.example.mapsappfirst.data.IPlacesDataReceived;
import com.example.mapsappfirst.data.Place;
import com.example.mapsappfirst.locations.LocationModel;
import com.example.mapsappfirst.network.NetWorkDataProvider;
import com.example.mapsappfirst.view.model.PlaceViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IPlacesDataReceived {

    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 3484;
    private static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 665;
    private PlaceViewModel mPlacesViewModel;
    private FusedLocationProviderClient mFusedLocationClient;
    private Place mMyLocation;
    private Button favorite_btn;
    private Button map_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        favorite_btn = findViewById( R.id.favorites_btn_main_activity );
        map_btn =  findViewById( R.id.map_btn_main_activity );

        map_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MapActivity.class);
                startActivityForResult( intent , 1 );

            }
        } );
        mPlacesViewModel = ViewModelProviders.of( this ).get( PlaceViewModel.class );
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient( this );
        if (ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale( this,
                    Manifest.permission.ACCESS_COARSE_LOCATION )) {
                Toast.makeText( this, "Please I need yor location to...", Toast.LENGTH_LONG ).show();
                Handler handler = new Handler();
                handler.postDelayed( new Runnable() {
                    @Override
                    public void run() {

                        ActivityCompat.requestPermissions( MainActivity.this,
                                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                                MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION );

                    }
                }, 3000 );

            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions( this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION );

                // MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
            getLocation();
        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION )
        {
            if (grantResults [0]== PackageManager.PERMISSION_GRANTED)
            {
                getLocation();
            }else{
                Toast.makeText( this, "Please I need yor location to...", Toast.LENGTH_LONG ).show();

            }
        }
    }

    @Override
    public void onPlacesDataReceived(ArrayList<LocationModel> results_) {
//        results_.toString();
        final PlacesListAdapter adapter = new PlacesListAdapter( this );

        RecyclerView recyclerView = findViewById( R.id.places_list );
        recyclerView.setAdapter( adapter );

        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );


        mPlacesViewModel.getAllPlaces().observe( this, new Observer<List<Place>>() {
            @Override
            public void onChanged(@Nullable final List<Place> places) {
                // Update the cached copy of the words in the adapter.
                adapter.setPlaces( places );
            }
        } );

    }


    @SuppressLint("MissingPermission")
    private void getLocation() {
//        mFusedLocationClient.getLastLocation()
//                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
//                    @Override
//                    public void onSuccess(Location location) {
//                        // Got last known location. In some rare situations this can be null.
//                        if (location != null) {
//                            // Logic to handle location object
//                            double lat = location.getLatitude();
//                            double lng = location.getLongitude();
//                            mMyLocation = new Place("my Location",lat,lng,null,true,false,null);
//                        }
//                    }
//                });
//    }
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient( this );

        if (ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFusedLocationClient.getLastLocation().addOnSuccessListener( this, new OnSuccessListener<Location>() {


            @Override
            public void onSuccess(Location location) {

                if (location != null) {
                    double lat = location.getLatitude();
                    double lng = location.getLongitude();
                    mMyLocation = new Place( "my Location", lat, lng, null, true, false, null );
                    NetWorkDataProvider dataProvider = new NetWorkDataProvider();
                    dataProvider.getPlacesByLocation( mMyLocation.getLat(), mMyLocation.getLng(), MainActivity.this );



                }
            }
        } );
    }

}
