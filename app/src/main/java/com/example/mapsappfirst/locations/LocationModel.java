package com.example.mapsappfirst.locations;

import java.util.List;

public class LocationModel {

    private String name;
    //open now may need to be in a class OpeningHours with private boolean open now
    private OpeningHours open_now;
    private String vicinity;
    private Geometry geometry;
    private List <Photo> photos;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OpeningHours getOpen_now() {
        return open_now;
    }

    public void setOpen_now(OpeningHours open_now) {
        this.open_now = open_now;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}
