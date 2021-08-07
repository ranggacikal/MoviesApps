package com.belajar.moviesapps.listMovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.belajar.moviesapps.R;
import com.belajar.moviesapps.SharedPreference.SharedPreferencedConfig;
import com.belajar.moviesapps.databinding.ActivityMainBinding;
import com.belajar.moviesapps.databinding.ActivityMovieBinding;
import com.belajar.moviesapps.genre.MainActivity;
import com.belajar.moviesapps.listMovie.model.ResponseListMovie;

public class MovieActivity extends AppCompatActivity {

    private ActivityMovieBinding binding;

    ListMovieViewModel listMovieViewModel;
    ListMovieAdapter adapter;

    private SharedPreferencedConfig preferencedConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        preferencedConfig = new SharedPreferencedConfig(this);

        binding.textNamaGenreMovie.setText(preferencedConfig.getPreferenceNamaGenre());

        adapter = new ListMovieAdapter(MovieActivity.this);
        listMovieViewModel = ViewModelProviders.of(this).get(ListMovieViewModel.class);
        listMovieViewModel.init();

        listMovieViewModel.getMovieResponseLiveData().observe(this, new Observer<ResponseListMovie>() {
            @Override
            public void onChanged(ResponseListMovie responseListMovie) {
                if (responseListMovie!=null){
                    adapter.setResult(responseListMovie.getItems());
                }
            }
        });

        GridLayoutManager manager = new GridLayoutManager(MovieActivity.this, 2,
                GridLayoutManager.VERTICAL, false);
        binding.rvMovie.setLayoutManager(manager);
        binding.rvMovie.setHasFixedSize(true);
        binding.rvMovie.setAdapter(adapter);

        loadDataMovie();
    }

    private void loadDataMovie() {

        listMovieViewModel.getDataMovie();

    }
}