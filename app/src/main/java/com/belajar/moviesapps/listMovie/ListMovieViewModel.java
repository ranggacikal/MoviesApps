package com.belajar.moviesapps.listMovie;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.belajar.moviesapps.listMovie.model.ResponseListMovie;

public class ListMovieViewModel extends AndroidViewModel {

    private ListMovieRepository listMovieRepository;
    private LiveData<ResponseListMovie> movieResponseLiveData;

    public ListMovieViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){
        listMovieRepository = new ListMovieRepository(getApplication().getApplicationContext());
        movieResponseLiveData = listMovieRepository.getMovieLiveData();
    }

    public void getDataMovie(){
        listMovieRepository.getMovie();
    }

    public LiveData<ResponseListMovie> getMovieResponseLiveData(){
        return movieResponseLiveData;
    }
}
