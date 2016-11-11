package com.example.keane.popmovies;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by keane on 11/11/2016.
 */

public class QueryUtils {
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    public static List<Movie> extractMovies(String jsonResponse){
        if (TextUtils.isEmpty(jsonResponse)){
            return null;
        }

        ArrayList<Movie> movies = new ArrayList<>();

        try{
            JSONObject baseJsonResponse = new JSONObject(jsonResponse);
            JSONArray results = baseJsonResponse.getJSONArray("results");
            for (int i =0; i<results.length();i++){

                JSONObject currentMovie = results.getJSONObject(i);
                String title = currentMovie.getString("title");
                String poster = currentMovie.getString("poster_path");
                String synopsis = currentMovie.getString("overview");
                float rating =(float) currentMovie.getDouble("vote_average");
                String releaseDate= currentMovie.getString("release_date");
                movies.add(new Movie(title,poster,synopsis,rating,releaseDate));

            }

        }catch(JSONException e){
            Log.e(LOG_TAG,"Problem parsing json",e);
        }
        return movies;
    }

    public static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output= new StringBuilder();
        if (inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null){
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    public static String makeHttpRequest(URL url) throws IOException{
        String jsonResponse = "";
        if (url == null){
            return jsonResponse;
        }
        HttpURLConnection urlConnection =null;
        InputStream inputStream = null;
        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();
            if(urlConnection.getResponseCode() == 200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
        } catch (IOException e){
            Log.e(LOG_TAG,"I/O exception while trying to read from server");
            return null;
        }
        return jsonResponse;
    }

    public static URL createUrl(String stringUrl){

        URL url =null;
        try {
            url = new URL(stringUrl);

        }catch (MalformedURLException exception){
            Log.e(LOG_TAG,"Error creating url",exception);
        }
        return url;
    }



}
