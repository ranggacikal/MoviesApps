package com.belajar.moviesapps.detailMovie;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.belajar.moviesapps.R;
import com.belajar.moviesapps.SharedPreference.SharedPreferencedConfig;
import com.belajar.moviesapps.api.ConfigRetrofit;
import com.belajar.moviesapps.databinding.ActivityDetailMovieBinding;
import com.belajar.moviesapps.databinding.ActivityMovieBinding;
import com.belajar.moviesapps.detailMovie.model.GenresItem;
import com.belajar.moviesapps.detailMovie.model.ResponseDetailMovie;
import com.belajar.moviesapps.detailMovie.modelTrailer.ResponseTrailer;
import com.belajar.moviesapps.detailMovie.modelTrailer.ResultsItem;
import com.belajar.moviesapps.reviews.ReviewActivity;
import com.bumptech.glide.Glide;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMovieActivity extends AppCompatActivity {

    private ActivityDetailMovieBinding binding;
    private DetailMovieRepository detailMovieRepository;

    public String nama, image, rating, deskripsi, genre;

    String key, id;

    private SharedPreferencedConfig preferencedConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailMovieBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        preferencedConfig = new SharedPreferencedConfig(this);

        getDetailMovie();
        getTrailer();

        PushDownAnim.setPushDownAnimTo(binding.btnTontonTrailer)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(DetailMovieActivity.this, TrailerActivity.class);
                        intent.putExtra("key", key);
                        intent.putExtra("id", id);
                        intent.putExtra("nama", nama);
                        startActivity(intent);
                    }
                });

        PushDownAnim.setPushDownAnimTo(binding.btnLihatReview)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(DetailMovieActivity.this, ReviewActivity.class));
                    }
                });

    }

    private void getTrailer() {

        ConfigRetrofit.service.getTrailerApi(preferencedConfig.getPreferenceIdMovie(),
                "fa1c6fbcb6eaa37520b128cba95bc0a3").enqueue(new Callback<ResponseTrailer>() {
            @Override
            public void onResponse(Call<ResponseTrailer> call, Response<ResponseTrailer> response) {
                if (response.isSuccessful()){
                    if (response.body()!=null){

                        List<ResultsItem> resultsItems = response.body().getResults();

                        for (int a = 0; a<resultsItems.size(); a++){
                            key = resultsItems.get(a).getKey();
                            id = resultsItems.get(a).getId();
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseTrailer> call, Throwable t) {

            }
        });

    }

    public void getDetailMovie(){
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

                        String link_image = "https://image.tmdb.org/t/p/w500"+image;

                        Glide.with(DetailMovieActivity.this)
                                .load(link_image)
                                .error(R.mipmap.ic_launcher)
                                .into(binding.imgDetailMovie);

                        binding.textNamaDetailMovie.setText(nama);
                        binding.textGenreDetailMovie.setText(genre);
                        binding.textRatingDetailMovie.setText(rating);
                        binding.textSysnopsisDetailFilm.setText(deskripsi);

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseDetailMovie> call, Throwable t) {

            }
        });
    }
}