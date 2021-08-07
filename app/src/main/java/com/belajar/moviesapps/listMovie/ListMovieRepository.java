package com.belajar.moviesapps.listMovie;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.belajar.moviesapps.SharedPreference.SharedPreferencedConfig;
import com.belajar.moviesapps.api.ConfigRetrofit;
import com.belajar.moviesapps.listMovie.model.ResponseListMovie;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListMovieRepository {

    private SharedPreferencedConfig preferencedConfig;

    private MutableLiveData<ResponseListMovie> dataMovie;
    Context context;

    public ListMovieRepository(Context context){
        dataMovie = new MutableLiveData<>();
        this.context = context;
    }

    public void getMovie(){

        preferencedConfig = new SharedPreferencedConfig(context);

        ConfigRetrofit.service.getMovieApi(preferencedConfig.getPreferenceIdGenre(),
                "fa1c6fbcb6eaa37520b128cba95bc0a3").enqueue(new Callback<ResponseListMovie>() {
            @Override
            public void onResponse(Call<ResponseListMovie> call, Response<ResponseListMovie> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){

                        dataMovie.postValue(response.body());

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseListMovie> call, Throwable t) {
                dataMovie.postValue(null);
            }
        });

    }

    public LiveData<ResponseListMovie> getMovieLiveData(){
        return dataMovie;
    }

}
