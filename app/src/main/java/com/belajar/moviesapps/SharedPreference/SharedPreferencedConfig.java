package com.belajar.moviesapps.SharedPreference;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencedConfig {

    public static final String PREFERENCE_ID_GENRE = "prefIdGenre";
    public static final String PREFERENCE_NAMA_GENRE = "prefNamaGenre";
    public static final String PREFERENCE_MOVIE_APP = "prefMovieApps";
    public static final String PREFERENCE_ID_MOVIE = "prefIdMovie";
    public static final String PREFERENCE_NAMA_MOVIE = "prefNamaMovie";
    public static final String PREFERENCE_IMG_MOVIE = "prefImgMovie";
    public static final String PREFERENCE_RATING_MOVIE = "prefRatingMovie";
    public static final String PREFERENCE_DESKRIPSI_MOVIE = "prefDeskripsiMovie";
    public static final String PREFERENCE_GENRE_MOVIE = "prefGenreMovie";

    SharedPreferences preferences;
    SharedPreferences.Editor preferencesEditor;

    public SharedPreferencedConfig(Context context) {

        preferences = context.getSharedPreferences(PREFERENCE_MOVIE_APP, Context.MODE_PRIVATE);
        preferencesEditor = preferences.edit();
    }

    public void savePrefString(String keyPref, String value){
        preferencesEditor.putString(keyPref, value);
        preferencesEditor.commit();
    }

    public String getPreferenceIdGenre(){
        return preferences.getString(PREFERENCE_ID_GENRE, "");
    }

    public String getPreferenceNamaGenre(){
        return preferences.getString(PREFERENCE_NAMA_GENRE, "");
    }

    public String getPreferenceIdMovie(){
        return preferences.getString(PREFERENCE_ID_MOVIE, "");
    }

    public String getPreferenceNamaMovie(){
        return preferences.getString(PREFERENCE_NAMA_MOVIE, "");
    }

    public String getPreferenceImgMovie(){
        return preferences.getString(PREFERENCE_IMG_MOVIE, "");
    }

    public String getPreferenceRatingMovie(){
        return preferences.getString(PREFERENCE_RATING_MOVIE, "");
    }

    public String getPreferenceDeskripsiMovie(){
        return preferences.getString(PREFERENCE_DESKRIPSI_MOVIE, "");
    }

    public String getPreferenceGenreMovie(){
        return preferences.getString(PREFERENCE_GENRE_MOVIE, "");
    }

}
