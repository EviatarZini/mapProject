package com.example.mapsappfirst.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.example.mapsappfirst.data.Place;
import com.example.mapsappfirst.data.PlaceDao;
import com.example.mapsappfirst.data.PlaceRoomDataBase;

import java.util.List;

public class PlaceRepository {

    private PlaceDao mPlaceDao;
    private LiveData<List<Place>> mAllPlaces;

    public PlaceRepository(Application application) {
        PlaceRoomDataBase db = PlaceRoomDataBase.getDatabase( application );

        mPlaceDao = db.placeDao();
        mAllPlaces = mPlaceDao.getAllPlaces();
    }

    public LiveData<List<Place>> getAllPlaces() {

        return mAllPlaces;
    }
//public  LiveData <List<Word>> deleteMovies (Application application){
//        WordRoomDatabase db = WordRoomDatabase.getDatabase( application );
//        mWordDao = db.wordDao();
//        mAllWords = mWordDao.deleteAll();
//}

    public void insert(Place place_) {

        new InsertAsyncTask( mPlaceDao ).execute( place_ );
    }
    public void insert(List<Place> placeList_) {
        for (Place p : placeList_) {
            insert( p );
        }
    }

    public void deleteLastSearch() {


        DeleteLastSearch deleteLastSearch = new DeleteLastSearch( mPlaceDao );
        deleteLastSearch.execute(  );

    }


    private class DeleteLastSearch extends AsyncTask <Void ,Void,Void > {
        private PlaceDao mAsyncTaskDao;

        DeleteLastSearch (PlaceDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {

        mAsyncTaskDao.deleteAll();
        Log.d("Test"," mAsyncTaskDao.deleteLastSearch();");

        return null;
        }
    }
    private  class InsertAsyncTask extends AsyncTask<Place, Void, Void> {

        private PlaceDao mAsyncTaskDao;

        InsertAsyncTask(PlaceDao dao) {
            mAsyncTaskDao = dao;

        }

        @Override
        protected Void doInBackground(final Place... params) {
            mAsyncTaskDao.insert( params[0] );
            Log.d("Test"," Inset Place;" + params[0].getName());

            return null;
        }
    }


}
