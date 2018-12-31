package com.example.mapsappfirst.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mapsappfirst.R;
import com.example.mapsappfirst.data.Place;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlacesListAdapter extends RecyclerView.Adapter<PlacesListAdapter.PlacesViewHolder> {

    class PlacesViewHolder extends RecyclerView.ViewHolder {

        private final TextView placeNameTextView;
        private final TextView placeAddressTextView;
        private final ImageView placeImageView;

        private PlacesViewHolder(View itemView) {
            super(itemView);
            placeNameTextView = itemView.findViewById(R.id.recycleView_name );
            placeAddressTextView = itemView.findViewById( R.id.recycleView_address );
            placeImageView = itemView.findViewById( R.id.recycleView_imageView );
        }
    }

    private final LayoutInflater mInflater;
    private List<Place> mPlacesList; // Cached copy of words

    public PlacesListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public PlacesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new PlacesViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(PlacesViewHolder holder, int position) {
        if (mPlacesList != null) {
            Place current = mPlacesList.get(position);
            holder.placeNameTextView.setText(current.getName());
            holder.placeAddressTextView.setText( current.getAddress() );
            Picasso.get().load( "http://image.tmdb.org/t/p/original" + current.getPhoto() ).into(  );

        } else {
            // Covers the case of data not being ready yet.
            holder.placeNameTextView.setText("No Places");
        }
    }

    public void setPlaces(List<Place> places){
        mPlacesList = places;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mPlacesList != null)
            return mPlacesList.size();
        else return 0;
    }
}
