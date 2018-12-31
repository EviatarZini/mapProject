package com.example.mapsappfirst.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mapsappfirst.R;
import com.example.mapsappfirst.data.IPlacesDataReceived;
import com.example.mapsappfirst.locations.LocationModel;
import com.example.mapsappfirst.network.NetWorkDataProvider;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, IPlacesDataReceived {

    private MapFragment mMapFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.map_fragment );
        NetWorkDataProvider dataProvider = new NetWorkDataProvider();
        dataProvider.getPlacesByLocation( 32.14611,34.8519,this );

        mMapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map_fragment);
        mMapFragment.getMapAsync(this);

    }



    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(32.085300, 34.781769))
                .title("Marker"));

        MyGoogleMap myGoogleMap = new MyGoogleMap(googleMap);
        myGoogleMap.setMapType(MyGoogleMap.MapType.MAP_TYPE_HYBRID);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(32.085300, 34.781769), 1));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        //googleMap.setMyLocationEnabled(true);
    }

    @Override
    public void onPlacesDataReceived(ArrayList<LocationModel> results_) {

        results_.toString();
    }
}
