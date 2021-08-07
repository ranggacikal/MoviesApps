package com.belajar.moviesapps.genre;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.belajar.moviesapps.R;
import com.belajar.moviesapps.SharedPreference.SharedPreferencedConfig;
import com.belajar.moviesapps.genre.model.GenresItem;
import com.belajar.moviesapps.listMovie.MovieActivity;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.ArrayList;
import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreViewHolder> {

    private List<GenresItem> dataGenre = new ArrayList<>();
    Context context;

    public GenreAdapter(Context context) {
        this.context = context;
    }

    private SharedPreferencedConfig preferencedConfig;

    @NonNull
    @Override
    public GenreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_genre, parent, false);
        return new GenreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreViewHolder holder, int position) {
        GenresItem genre = dataGenre.get(position);

        preferencedConfig = new SharedPreferencedConfig(context);

        holder.txtNama.setText(genre.getName());

        PushDownAnim.setPushDownAnimTo(holder.itemView)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_GENRE,
                                String.valueOf(genre.getId()));
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_NAMA_GENRE,
                                String.valueOf(genre.getName()));
                        context.startActivity(new Intent(context, MovieActivity.class));
                    }
                });
    }

    @Override
    public int getItemCount() {
        return dataGenre.size();
    }

    public void setResults(List<GenresItem> results){
        this.dataGenre = results;
        notifyDataSetChanged();
    }

    public class GenreViewHolder extends RecyclerView.ViewHolder {

        TextView txtNama;

        public GenreViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.text_item_nama_genre);
        }
    }
}
