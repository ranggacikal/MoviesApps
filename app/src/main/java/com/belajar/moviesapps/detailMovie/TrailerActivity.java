package com.belajar.moviesapps.detailMovie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.belajar.moviesapps.R;
import com.belajar.moviesapps.databinding.ActivityDetailMovieBinding;
import com.belajar.moviesapps.databinding.ActivityTrailerBinding;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

public class TrailerActivity extends AppCompatActivity {

    private ActivityTrailerBinding binding;

    String id = "";
    String key = "";
    String nama = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTrailerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        id = getIntent().getStringExtra("id");
        key = getIntent().getStringExtra("key");
        nama = getIntent().getStringExtra("nama");

        binding.textNamaTrailer.setText(nama);

        getLifecycle().addObserver(binding.youtubePlayerView);

        binding.youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(key, 0);
            }
        });
    }
}