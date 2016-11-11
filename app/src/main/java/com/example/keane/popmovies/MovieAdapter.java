package com.example.keane.popmovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by keane on 11/3/2016.
 */

public class MovieAdapter extends ArrayAdapter<Movie> {

    public MovieAdapter(Context context, List<Movie> movies){
        super(context,0,movies);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listitemview = convertView;
        if(listitemview == null){
            listitemview = LayoutInflater.from(getContext()).inflate(R.layout.movie_list_item,parent,false);
        }

        final Movie currentMovie = getItem(position);

        ImageView moviePoster = (ImageView) listitemview.findViewById(R.id.poster_id);

        Picasso.with(getContext()).load(currentMovie.getPoster()).into(moviePoster);

        return listitemview;
    }
}
