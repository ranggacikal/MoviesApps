package com.belajar.moviesapps.genre;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.belajar.moviesapps.api.ConfigRetrofit;
import com.belajar.moviesapps.genre.model.ResponseGenre;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenreRepository {


    private MutableLiveData<ResponseGenre> dataGenre;

    public GenreRepository(){
        dataGenre = new MutableLiveData<>();
    }

    public void getGenre(){
        ConfigRetrofit.service.getGenre("fa1c6fbcb6eaa37520b128cba95bc0a3").enqueue(new Callback<ResponseGenre>() {
            @Override
            public void onResponse(Call<ResponseGenre> call, Response<ResponseGenre> response) {
                if (response.isSuccessful()){

                    if (response.body()!=null){
                        dataGenre.postValue(response.body());
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseGenre> call, Throwable t) {
                dataGenre.postValue(null);
            }
        });
    }

    public LiveData<ResponseGenre> getGenreLiveData(){
        return dataGenre;
    }

}
