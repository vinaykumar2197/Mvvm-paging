package com.vinaykumar.myapplication.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vinaykumar.myapplication.R;
import com.vinaykumar.myapplication.databinding.ActivityMovieDetailedBinding;
import com.vinaykumar.myapplication.model.Movies;

public class MovieDetailedActivity extends AppCompatActivity {

    private ActivityMovieDetailedBinding activityMovieDetailedBinding;
    private Movies movies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detailed);

        activityMovieDetailedBinding = DataBindingUtil.setContentView(this,R.layout.activity_movie_detailed);

        Intent intent = getIntent();

        if(intent.hasExtra("movie_model")){
            movies = intent.getParcelableExtra("movie_model");
            activityMovieDetailedBinding.setMovie(movies);

        }
    }
}
