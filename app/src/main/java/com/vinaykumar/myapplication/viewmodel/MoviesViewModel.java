package com.vinaykumar.myapplication.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.vinaykumar.myapplication.model.Movies;
import com.vinaykumar.myapplication.model.MoviesRepository;
import com.vinaykumar.myapplication.paging.MovieDataSource;
import com.vinaykumar.myapplication.paging.MovieDataSourceFactory;
import com.vinaykumar.myapplication.service.RetrofitInstance;
import com.vinaykumar.myapplication.service.RetrofitInterface;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MoviesViewModel extends AndroidViewModel {

    MoviesRepository moviesRepository ;


    LiveData<MovieDataSource> movieDataSourceLiveData;
    private Executor executor;
    private LiveData<PagedList<Movies>> moviesPagedList;


    public MoviesViewModel(@NonNull Application application) {
        super(application);
        moviesRepository = new MoviesRepository(application);

        RetrofitInterface retrofitInterface = RetrofitInstance.getService();
        MovieDataSourceFactory movieDataSourceFactory = new MovieDataSourceFactory(retrofitInterface,application);
        movieDataSourceLiveData = movieDataSourceFactory.getMutableLiveData();

        PagedList.Config config = new PagedList.Config.Builder()
                                .setEnablePlaceholders(true)
                                .setInitialLoadSizeHint(10)
                                .setPageSize(20)
                                .setPrefetchDistance(4)
                                .build();

        executor = Executors.newFixedThreadPool(5);

        moviesPagedList = new LivePagedListBuilder<Long,Movies>(movieDataSourceFactory,config)
                        .setFetchExecutor(executor)
                        .build();

    }

   public LiveData<List<Movies> > getAllMovies(){
    return moviesRepository.getMovieData();
    }


    public LiveData<PagedList<Movies>> getMoviesPagedList() {
        return moviesPagedList;
    }
}
