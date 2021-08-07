package com.belajar.moviesapps.listMovie;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.belajar.moviesapps.R;
import com.belajar.moviesapps.SharedPreference.SharedPreferencedConfig;
import com.belajar.moviesapps.detailMovie.DetailMovieActivity;
import com.belajar.moviesapps.listMovie.model.ItemsItem;
import com.bumptech.glide.Glide;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.ArrayList;
import java.util.List;

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.ListMovieViewHolder> {

    private List<ItemsItem> dataMovie = new ArrayList<>();

    Context context;

    private SharedPreferencedConfig preferencedConfig;

    public ListMovieAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ListMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ListMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListMovieViewHolder holder, int position) {

        preferencedConfig = new SharedPreferencedConfig(context);

        ItemsItem movie = dataMovie.get(position);
        String link_image = "https://image.tmdb.org/t/p/w500"+movie.getPosterPath();

        Glide.with(context)
                .load(link_image)
                .error(R.mipmap.ic_launcher)
                .into(holder.imgMovie);

        holder.txtNamaMovie.setText(movie.getOriginalTitle());

        PushDownAnim.setPushDownAnimTo(holder.itemView)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_MOVIE,
                                String.valueOf(movie.getId()));
                        context.startActivity(new Intent(context, DetailMovieActivity.class));
                    }
                });

    }

    @Override
    public int getItemCount() {
        return dataMovie.size();
    }

    public void setResult(List<ItemsItem> result){
        this.dataMovie = result;
        notifyDataSetChanged();
    }

    public class ListMovieViewHolder extends RecyclerView.ViewHolder {

        ImageView imgMovie;
        TextView txtNamaMovie;

        public ListMovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMovie = itemView.findViewById(R.id.img_item_movie);
            txtNamaMovie = itemView.findViewById(R.id.text_item_nama_movie);
        }
    }
}
