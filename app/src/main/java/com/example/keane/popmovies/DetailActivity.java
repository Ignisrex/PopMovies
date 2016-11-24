package com.example.keane.popmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private ImageView posterView;
    private TextView titleView;
    private TextView synopsisView;
    private TextView ratingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String posterUrl= getIntent().getStringExtra("poster");
        String title = getIntent().getStringExtra("title");
        String synopsis = getIntent().getStringExtra("synopsis");
        String rating = "" + getIntent().getFloatExtra("rating",0);

        posterView =(ImageView) findViewById(R.id.poster);
        titleView = (TextView) findViewById(R.id.title);
        synopsisView = (TextView) findViewById(R.id.synopsis);
        ratingView = (TextView) findViewById(R.id.ratings);

        String imageUrl = "http://image.tmdb.org/t/p/w185/"+posterUrl;
        Picasso.with(getApplicationContext()).load(imageUrl).into(posterView);
        titleView.setText(title);
        synopsisView.setText(synopsis);
        ratingView.setText(rating);


    }

}
