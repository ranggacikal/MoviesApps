package com.belajar.moviesapps.genre;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.belajar.moviesapps.genre.model.ResponseGenre;

public class GenreViewModel extends AndroidViewModel {

    private GenreRepository genreRepository;
    private LiveData<ResponseGenre> genreResponseLiveData;

    public GenreViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){
        genreRepository = new GenreRepository();
        genreResponseLiveData = genreRepository.getGenreLiveData();
    }

    public void getDataGenre(){
        genreRepository.getGenre();
    }

    public LiveData<ResponseGenre> getGenreResponseLiveData(){
        return genreResponseLiveData;
    }

}
