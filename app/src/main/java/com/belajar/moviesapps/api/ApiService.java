package com.belajar.moviesapps.api;

import com.belajar.moviesapps.detailMovie.model.ResponseDetailMovie;
import com.belajar.moviesapps.detailMovie.modelTrailer.ResponseTrailer;
import com.belajar.moviesapps.genre.model.ResponseGenre;
import com.belajar.moviesapps.listMovie.model.ResponseListMovie;
import com.belajar.moviesapps.reviews.model.ResponseReview;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("genre/movie/list")
    Call<ResponseGenre> getGenre(@Query("api_key") String api_key);

    @GET("list/{id_genre}")
    Call<ResponseListMovie> getMovieApi(@Path("id_genre") String id_genre,
                                        @Query("api_key") String api_key);

    @GET("movie/{id_movie}")
    Call<ResponseDetailMovie> getDetailMovieApi(@Path("id_movie") String id_movie,
                                                @Query("api_key") String api_key);

    @GET("movie/{id_movie}/videos")
    Call<ResponseTrailer> getTrailerApi(@Path("id_movie") String id_movie,
                                        @Query("api_key") String api_key);

    @GET("movie/{id_movie}/reviews")
    Call<ResponseReview> getReviewsApi(@Path("id_movie") String id_movie,
                                       @Query("api_key") String api_key,
                                       @Query("page") String page);

}
