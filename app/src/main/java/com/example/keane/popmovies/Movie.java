package com.example.keane.popmovies;

/**
 * Created by keane on 11/3/2016.
 */

public class Movie {
    private String mTitle;
    private String mPoster;
    private String mSynopsis;
    private float mRating;
    private String mReleaseDate;

    public Movie(String title,String poster,String synopsis,float rating ,String releaseDate){
        mTitle = title;
        mPoster = poster;
        mSynopsis = synopsis;
        mRating = rating;
        mReleaseDate = releaseDate;
    }

    public String getTitle(){
        return mTitle;
    }

    public String getPoster() {
        return mPoster;
    }

    public String getSynopsis(){
        return mSynopsis;
    }

    public float getRating(){
        return mRating;
    }

    public String getReleaseDate(){
        return mReleaseDate;
    }

}
