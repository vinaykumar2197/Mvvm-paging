package com.vinaykumar.myapplication.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vinaykumar.myapplication.R;
import com.vinaykumar.myapplication.adapter.MoviesAdapter;
import com.vinaykumar.myapplication.model.Movies;
import com.vinaykumar.myapplication.viewmodel.MoviesViewModel;
import com.vinaykumar.myapplication.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //private ArrayList<Movies> moviesArrayList ;
    private PagedList<Movies> pagedList;
    private RecyclerView recyclerView;
    private MoviesAdapter moviesAdapter;
    private MoviesViewModel moviesViewModel;
    private ActivityMainBinding activityMainBinding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);

        getPopularMovies();

    }

    public void getPopularMovies(){
        moviesViewModel.getMoviesPagedList().observe(this, new Observer<PagedList<Movies>>() {
            @Override
            public void onChanged(@Nullable PagedList<Movies> movies) {
                pagedList = movies;
                showRecyclerViewData();
            }
        });
    }

    public void showRecyclerViewData(){
        recyclerView = activityMainBinding.recyclerViewMovies;
        moviesAdapter = new MoviesAdapter(this);
        moviesAdapter.submitList(pagedList);

        if(this.getResources().getConfiguration().orientation==Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        }
        else{
            recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        }


        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(moviesAdapter);
        moviesAdapter.notifyDataSetChanged();
    }
}
