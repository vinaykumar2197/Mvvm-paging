package com.vinaykumar.myapplication.paging;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.vinaykumar.myapplication.service.RetrofitInterface;

public class MovieDataSourceFactory extends DataSource.Factory {

    private MovieDataSource movieDataSource;
    private RetrofitInterface retrofitInterface;
    private Application application;
    private MutableLiveData<MovieDataSource> mutableLiveData;


    public MovieDataSourceFactory(RetrofitInterface retrofitInterface, Application application) {
        this.retrofitInterface = retrofitInterface;
        this.application = application;
        mutableLiveData = new MutableLiveData<>();
    }


    @Override
    public DataSource create() {
        movieDataSource = new MovieDataSource(retrofitInterface,application);
        mutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

    public  MutableLiveData<MovieDataSource> getMutableLiveData(){
        return mutableLiveData;
    }

}
