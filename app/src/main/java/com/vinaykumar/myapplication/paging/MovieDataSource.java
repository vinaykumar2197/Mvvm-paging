package com.vinaykumar.myapplication.paging;

import android.app.Application;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.vinaykumar.myapplication.R;
import com.vinaykumar.myapplication.model.Movies;
import com.vinaykumar.myapplication.model.MoviesDBResponse;
import com.vinaykumar.myapplication.service.RetrofitInstance;
import com.vinaykumar.myapplication.service.RetrofitInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Long,Movies> {

    private RetrofitInterface retrofitInterface;
    private Application application;

    public MovieDataSource(RetrofitInterface retrofitInterface, Application application) {
        this.retrofitInterface = retrofitInterface;
        this.application = application;
    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, Movies> callback) {
        retrofitInterface = RetrofitInstance.getService();
        Call<MoviesDBResponse>  call = retrofitInterface.getPopularMoviesPaging(application.getResources().getString(R.string.api_key),1);
        call.enqueue(new Callback<MoviesDBResponse>() {
            @Override
            public void onResponse(Call<MoviesDBResponse> call, Response<MoviesDBResponse> response) {
                MoviesDBResponse moviesDBResponse = response.body();
                ArrayList<Movies> moviesArrayList = new ArrayList<>();
                if(moviesDBResponse!=null && moviesDBResponse.getMoviess()!=null){
                    moviesArrayList =(ArrayList<Movies>) moviesDBResponse.getMoviess();
                    callback.onResult(moviesArrayList,null, (long) 2);
                }
            }

            @Override
            public void onFailure(Call<MoviesDBResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Movies> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Movies> callback) {
        retrofitInterface = RetrofitInstance.getService();
        Call<MoviesDBResponse> call = retrofitInterface.getPopularMoviesPaging(application.getResources().getString(R.string.api_key),params.key);

        call.enqueue(new Callback<MoviesDBResponse>() {
            @Override
            public void onResponse(Call<MoviesDBResponse> call, Response<MoviesDBResponse> response) {
                MoviesDBResponse moviesDBResponse = response.body();
                ArrayList<Movies>  moviesArrayList = new ArrayList<>();
                if(moviesDBResponse!=null && moviesDBResponse.getMoviess()!=null){
                    moviesArrayList = (ArrayList<Movies>) moviesDBResponse.getMoviess();
                    callback.onResult(moviesArrayList,params.key+1);
                }
            }

            @Override
            public void onFailure(Call<MoviesDBResponse> call, Throwable t) {

            }
        });

    }
}
