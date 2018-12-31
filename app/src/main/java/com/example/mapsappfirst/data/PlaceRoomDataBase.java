package com.example.mapsappfirst.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

@Database( entities = {Place.class},version = 1)

public abstract class PlaceRoomDataBase extends RoomDatabase {

    public abstract PlaceDao placeDao();

    private static volatile PlaceRoomDataBase INSTANCE;


    public static PlaceRoomDataBase getDatabase(final Context context) {

        if (INSTANCE == null) {
            // synchronized means that the code can work 1 thread at a time
            synchronized (PlaceRoomDataBase.class) {

                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PlaceRoomDataBase.class, "word_database")
                            .build();
                }
            }

        }
        return INSTANCE;
    }




}
