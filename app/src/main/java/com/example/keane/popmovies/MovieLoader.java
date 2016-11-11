package com.example.keane.popmovies;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by keane on 11/3/2016.
 */

public class MovieLoader extends AsyncTaskLoader {
    private static final String LOG_TAG = MovieLoader.class.getSimpleName();

    private List<Movie> mMovieList;
    private String mUrl;

    public MovieLoader(Context context, String url){
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Movie> loadInBackground() {
        if (mUrl == null){
            return null;
        }

        URL url = QueryUtils.createUrl(mUrl);
        String jsonResponse = "";
        try{
            jsonResponse=QueryUtils.makeHttpRequest(url);
        }catch (IOException e){
            Log.e(LOG_TAG,"Error making request");
        }

        List<Movie> movies = QueryUtils.extractMovies(jsonResponse);
        return movies;
    }

}
