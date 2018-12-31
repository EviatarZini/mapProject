package com.example.mapsappfirst.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PlaceDao {

    @Insert
    void insert(Place place_);

    @Delete
    void deleteNew (Place place_);


    @Query("DELETE FROM place_table")
    void deleteAll();

    @Query("DELETE FROM place_table WHERE isFavorite = 0")
    void deleteLastSearch();

    @Query( "DELETE FROM place_table WHERE name= :place" )
    void deleteByName (String place);

    @Query( "DELETE FROM place_table WHERE id= :ID_" )
    void deleteById (long ID_);

    @Query("SELECT * from place_table ORDER BY name ASC")

    LiveData<List<Place>> getAllPlaces();


}
