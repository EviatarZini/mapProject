package com.example.mapsappfirst.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName ="place_table" )

public class Place {

    public Place(@NonNull String mName, @NonNull Double mLat, @NonNull Double mLng, String mPhoto, boolean mIsLastSearch, boolean mIsFavorite, @NonNull String mAddress) {
        this.mName = mName;
        this.mLat = mLat;
        this.mLng = mLng;
        this.mPhoto = mPhoto;
        this.mIsLastSearch = mIsLastSearch;
        this.mIsFavorite = mIsFavorite;
        this.mAddress = mAddress;
    }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private long ID;

    @NonNull
    @ColumnInfo(name = "name")
    private String mName;

    @NonNull
    @ColumnInfo(name = "lat")
    private Double mLat;

    @NonNull
    @ColumnInfo(name = "lng")
    private Double mLng;

    @ColumnInfo(name = "photo")
    private String mPhoto;

    @ColumnInfo(name = "isLastSearch")
    private boolean mIsLastSearch;

    @ColumnInfo(name = "isFavorite")
    private boolean mIsFavorite;

    @NonNull
    @ColumnInfo(name = "address")
    private String mAddress;


    @NonNull
    public long getID() {
        return ID;
    }

    public void setID(@NonNull long ID) {
        this.ID = ID;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    public void setName(@NonNull String mName) {
        this.mName = mName;
    }

    @NonNull
    public Double getLat() {
        return mLat;
    }

    public void setLat(@NonNull Double mLat) {
        this.mLat = mLat;
    }

    @NonNull
    public Double getLng() {
        return mLng;
    }

    public void setLng(@NonNull Double mLng) {
        this.mLng = mLng;
    }

    public String getPhoto() {
        return mPhoto;
    }

    public void setPhoto(String mPhoto) {
        this.mPhoto = mPhoto;
    }

    public boolean isIsLastSearch() {
        return mIsLastSearch;
    }

    public void setIsLastSearch(boolean mIsLastSearch) {
        this.mIsLastSearch = mIsLastSearch;
    }

    public boolean isIsFavorite() {
        return mIsFavorite;
    }

    public void setIsFavorite(boolean mIsFavorite) {
        this.mIsFavorite = mIsFavorite;
    }

    @NonNull
    public String getAddress() {
        return mAddress;
    }

    public void setAddress(@NonNull String mAddress) {
        this.mAddress = mAddress;
    }
}