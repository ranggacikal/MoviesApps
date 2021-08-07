package com.belajar.moviesapps.reviews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.belajar.moviesapps.R;
import com.belajar.moviesapps.SharedPreference.SharedPreferencedConfig;
import com.belajar.moviesapps.api.ConfigRetrofit;
import com.belajar.moviesapps.databinding.ActivityDetailMovieBinding;
import com.belajar.moviesapps.databinding.ActivityReviewBinding;
import com.belajar.moviesapps.reviews.model.ResponseReview;
import com.belajar.moviesapps.reviews.model.ResultsItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewActivity extends AppCompatActivity {

    private ActivityReviewBinding binding;

    private int page = 1;
    private int page_size = 10;
    private int total_page = 1;
    private List<ResultsItem> list = new ArrayList<>();
    LinearLayoutManager manager;
    ReviewAdapter adapter;

    private boolean isLoading = false;

    private SharedPreferencedConfig preferencedConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReviewBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        preferencedConfig = new SharedPreferencedConfig(ReviewActivity.this);

        getReview();

        manager = new LinearLayoutManager(ReviewActivity.this);
        binding.rvReviews.setHasFixedSize(true);
        binding.rvReviews.setLayoutManager(manager);
        adapter = new ReviewAdapter(ReviewActivity.this, list);
        binding.rvReviews.setAdapter(adapter);

        binding.rvReviews.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                int visibleItem = manager.getChildCount();
                int totalItem = adapter.getItemCount();
                int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();

                if (!isLoading && page < total_page) {

                    if (visibleItem + firstVisibleItemPosition >= totalItem) {

                        page++;
                        Log.d("checkScrolled", "page: " + page);
                        getReview();
                    }
                }
            }
        });

    }

    private void getReview() {

        binding.progressbar.setVisibility(View.VISIBLE);
        isLoading = true;

        ConfigRetrofit.service.getReviewsApi(preferencedConfig.getPreferenceIdMovie(),
                "fa1c6fbcb6eaa37520b128cba95bc0a3", String.valueOf(page))
                .enqueue(new Callback<ResponseReview>() {
                    @Override
                    public void onResponse(Call<ResponseReview> call, Response<ResponseReview> response) {
                        if (response.isSuccessful()){
                            binding.progressbar.setVisibility(View.GONE);

                            total_page = response.body().getTotalPages();
                            list = response.body().getResults();
                            if (list!=null) {

                                adapter.addList(list);
                            }
                            isLoading = false;
                        }else{
                            Toast.makeText(ReviewActivity.this, "Gagal Memuat Data", Toast.LENGTH_SHORT).show();
                            binding.progressbar.setVisibility(View.GONE);
                            isLoading = false;
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseReview> call, Throwable t) {
                        binding.progressbar.setVisibility(View.GONE);
                        isLoading = false;
                        Toast.makeText(ReviewActivity.this, "Terjadi Kesalahan Di server : "+t.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });

    }
}