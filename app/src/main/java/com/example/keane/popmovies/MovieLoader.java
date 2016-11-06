package com.example.keane.popmovies;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;

import java.util.List;

/**
 * Created by keane on 11/3/2016.
 */

public class MovieLoader extends AsyncTaskLoader {

    private List<Movie> mMovieList;
    private Cursor mCursor;

    public MovieLoader(Context context, Cursor c){
        super(context);
        mCursor = c;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Movie> loadInBackground() {
        return null;
    }
}
