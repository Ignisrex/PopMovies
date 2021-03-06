package com.example.keane.popmovies;

import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements android.app.LoaderManager.LoaderCallbacks<List<Movie>> {



    private static final String API_KEY = ApiKey.get();
    private String sortOrder = "popular";
    private String BASE_REQUEST_URL= "https://api.themoviedb.org/3/movie/"+ sortOrder +"?api_key="+ API_KEY +"&language=en-US&page=1";
    private static final int MOVIE_LOADER_ID = 1;
    private MovieAdapter movieAdapter;
    private ArrayList<Movie> mMovies ;
    private android.app.LoaderManager loaderManager = getLoaderManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mMovies = new ArrayList<Movie>();
        RecyclerView rvPosters = (RecyclerView) findViewById(R.id.recycleView);
        movieAdapter = new MovieAdapter(this, mMovies);

        movieAdapter.setOnItemClickListener(new MovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Movie movie = mMovies.get(position);
                Intent detailIntent = new Intent(getApplicationContext(), DetailActivity.class);
                detailIntent.putExtra("poster", movie.getPoster());
                detailIntent.putExtra("title", movie.getTitle());
                detailIntent.putExtra("synopsis", movie.getSynopsis());
                detailIntent.putExtra("rating", movie.getRating());
                startActivity(detailIntent);
            }
        });

        rvPosters.setAdapter(movieAdapter);
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvPosters.setLayoutManager(gridLayoutManager);


        loaderManager.initLoader(MOVIE_LOADER_ID ,null, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.topRated) {
            sortOrder = "top_rated";
        }else if (id == R.id.popular){
            sortOrder = "popular";
        }
        BASE_REQUEST_URL = "https://api.themoviedb.org/3/movie/"+ sortOrder +"?api_key="+ API_KEY +"&language=en-US&page=1";
        loaderManager.restartLoader(MOVIE_LOADER_ID,null,this);

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<List<Movie>> onCreateLoader(int i, Bundle bundle) {
        return new MovieLoader(this,BASE_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> movies) {
        mMovies.clear();
        if (movies != null && !movies.isEmpty()){
            mMovies.addAll(movies);
        }
        movieAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {
        mMovies.clear();
        movieAdapter.notifyDataSetChanged();
    }
}
