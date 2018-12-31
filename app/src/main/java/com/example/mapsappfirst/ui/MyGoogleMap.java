package com.example.mapsappfirst.ui;

import com.google.android.gms.maps.GoogleMap;

public class MyGoogleMap {

    public enum MapType
    {
        MAP_TYPE_NONE,
        MAP_TYPE_NORMAL,
        MAP_TYPE_SATELLITE,
        MAP_TYPE_TERRAIN,
        MAP_TYPE_HYBRID

    }
    private GoogleMap mGoogleMap;
    public MyGoogleMap (GoogleMap map_)
    {
        mGoogleMap = map_;
    }

    public void setMapType (MapType mapType_)
    {
        mGoogleMap.setMapType(mapType_.ordinal());

    }



}
