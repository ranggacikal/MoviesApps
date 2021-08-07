package com.belajar.moviesapps.genre;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.belajar.moviesapps.R;
import com.belajar.moviesapps.databinding.ActivityMainBinding;
import com.belajar.moviesapps.genre.model.ResponseGenre;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    GenreViewModel genreViewModel;
    GenreAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        adapter = new GenreAdapter(MainActivity.this);

        genreViewModel = ViewModelProviders.of(this).get(GenreViewModel.class);
        genreViewModel.init();

        genreViewModel.getGenreResponseLiveData().observe(this, new Observer<ResponseGenre>() {
            @Override
            public void onChanged(ResponseGenre responseGenre) {
                if (responseGenre != null){
                    adapter.setResults(responseGenre.getGenres());
                }
            }
        });

        GridLayoutManager manager = new GridLayoutManager(MainActivity.this, 2, GridLayoutManager.VERTICAL, false);
        binding.rvGenreMovie.setLayoutManager(manager);
        binding.rvGenreMovie.setHasFixedSize(true);
        binding.rvGenreMovie.setAdapter(adapter);

        loadGenre();
    }

    private void loadGenre() {

        genreViewModel.getDataGenre();

    }
}