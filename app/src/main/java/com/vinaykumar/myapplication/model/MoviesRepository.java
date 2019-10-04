package com.vinaykumar.myapplication.model;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;


import com.vinaykumar.myapplication.R;
import com.vinaykumar.myapplication.service.RetrofitInstance;
import com.vinaykumar.myapplication.service.RetrofitInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesRepository {

    ArrayList<Movies> moviesArrayList = new ArrayList<>();

    MutableLiveData<List<Movies>>  mutableLiveData = new MutableLiveData<>();

    private Application application;

    public MoviesRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Movies>>  getMovieData(){

        RetrofitInterface retrofitInterface = RetrofitInstance.getService();

        Call<MoviesDBResponse> call = retrofitInterface.getPopularMovies(application.getApplicationContext().getString(R.string.api_key));

        call.enqueue(new Callback<MoviesDBResponse>() {
            @Override
            public void onResponse(Call<MoviesDBResponse> call, Response<MoviesDBResponse> response) {

                if(response.isSuccessful()) {
                    MoviesDBResponse moviesDBResponse = response.body();

                    if(moviesDBResponse!=null && moviesDBResponse.getMoviess()!=null){
                        moviesArrayList =(ArrayList<Movies>) moviesDBResponse.getMoviess();

                        mutableLiveData.setValue(moviesArrayList);
                    }
                }

            }

            @Override
            public void onFailure(Call<MoviesDBResponse> call, Throwable t) {

            }
        });


        return mutableLiveData;


    }

}
