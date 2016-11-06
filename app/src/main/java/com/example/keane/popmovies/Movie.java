package com.example.keane.popmovies;

/**
 * Created by keane on 11/3/2016.
 */

public class Movie {
    private String mTitle;
    private String mPoster;
    private String mSynopsis;
    private String mRating;
    private String mReleaseDate;

    public Movie(String title,String poster,String synopsis,String rating ,String releaseDate){
        mTitle = title;
        mPoster = poster;
        mSynopsis = synopsis;
        mRating = rating;
        mReleaseDate = releaseDate;
    }

    private String getTitle(){
        return mTitle;
    }

    private String getPoster() {
        return mPoster;
    }

    private String getSynopsis(){
        return mSynopsis;
    }

    private String getRating(){
        return mRating;
    }

    private String getReleaseDate(){
        return mReleaseDate;
    }

}
