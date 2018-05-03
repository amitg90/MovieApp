package com.example.amit.movieapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MovieDetail extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        MovieInfo movieInfo;

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        int position = intent.getIntExtra(MovieApp.EXTRA_MESSAGE, -1);

        if (position == -1)
        {
            Toast.makeText(this, "Invalid Position entered", Toast.LENGTH_LONG);
            return;
        }

        movieInfo = MovieDB.movieInfoArrayList.get(position);

        // Capture the layout's TextView and set the string as its text
        ImageView imageView;
        TextView textView;
        String imageUri;
        imageView = findViewById(R.id.path_tv);
        imageUri = MovieDbUtils.IMAGE_BASE_URL + movieInfo.path;
        Picasso.with(this)
                .load(imageUri)
                .into(imageView);

        textView = findViewById(R.id.title_tv);
        textView.setText(movieInfo.title);

        textView = findViewById(R.id.release_date_tv);
        textView.setText(movieInfo.release_date);

        textView = findViewById(R.id.vote_avg_tv);
        textView.setText(movieInfo.vote_average);

        textView = findViewById(R.id.overview_tv);
        textView.setText(movieInfo.overview);
    }
}
