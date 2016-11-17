package com.example.keane.popmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by keane on 11/3/2016.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private Context mContext;
    private List<Movie> mMovies;

    public MovieAdapter(Context context, List<Movie> movies){
        mMovies = movies;
        mContext = context;
    }

    private Context getContext(){
        return mContext;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView posterImage;

        public ViewHolder(View viewItem){
            super(viewItem);

            posterImage = (ImageView) viewItem.findViewById(R.id.poster_id);
        }
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View posterView = inflater.inflate(R.layout.movie_list_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(posterView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieAdapter.ViewHolder holder, int position) {
        Movie movie = mMovies.get(position);

        ImageView imageView = holder.posterImage;
        String imageUrl = "http://image.tmdb.org/t/p/w185/"+ movie.getPoster();
        Picasso.with(getContext()).load(imageUrl).into(imageView);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }
}
