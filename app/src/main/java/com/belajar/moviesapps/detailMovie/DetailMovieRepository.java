package com.belajar.moviesapps.detailMovie;

import android.content.Context;
import android.util.Log;

import com.belajar.moviesapps.SharedPreference.SharedPreferencedConfig;
import com.belajar.moviesapps.api.ConfigRetrofit;
import com.belajar.moviesapps.detailMovie.model.GenresItem;
import com.belajar.moviesapps.detailMovie.model.ResponseDetailMovie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMovieRepository {

    private SharedPreferencedConfig preferencedConfig;
    Context context;

    public String nama, image, rating, deskripsi, genre;

    public DetailMovieRepository(Context context) {
        this.context = context;
    }

    public void getDetailMovie(){
        preferencedConfig = new SharedPreferencedConfig(context);
        Log.d("getDetail", "id: "+preferencedConfig.getPreferenceIdMovie());
        ConfigRetrofit.service.getDetailMovieApi(preferencedConfig.getPreferenceIdMovie(),
                "fa1c6fbcb6eaa37520b128cba95bc0a3").enqueue(new Callback<ResponseDetailMovie>() {
            @Override
            public void onResponse(Call<ResponseDetailMovie> call, Response<ResponseDetailMovie> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){

                        nama = response.body().getOriginalTitle();
                        image = response.body().getPosterPath();
                        rating = String.valueOf(response.body().getVoteAverage());
                        deskripsi = response.body().getOverview();
                        List<GenresItem> genresItems = response.body().getGenres();
                        ArrayList<String> listGenre = new ArrayList<>();

                        for (int a = 0; a<genresItems.size(); a++){

                            listGenre.add(genresItems.get(a).getName());

                        }

                        genre = listGenre.toString();

                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_NAMA_MOVIE, nama);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_IMG_MOVIE, image);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_RATING_MOVIE, rating);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_DESKRIPSI_MOVIE, deskripsi);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_GENRE_MOVIE, genre);

                        Log.d("getDetail", "nama: "+nama);
                        Log.d("getDetail", "img: "+image);
                        Log.d("getDetail", "rating: "+rating);
                        Log.d("getDetail", "deskripsi: "+deskripsi);
                        Log.d("getDetail", "genre: "+genre);

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseDetailMovie> call, Throwable t) {

            }
        });
    }

}
