package com.vinaykumar.myapplication.service;



import com.vinaykumar.myapplication.model.MoviesDBResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @GET("movie/popular")
    Call<MoviesDBResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<MoviesDBResponse> getPopularMoviesPaging(@Query("api_key") String apiKey, @Query("page") long page);
}
