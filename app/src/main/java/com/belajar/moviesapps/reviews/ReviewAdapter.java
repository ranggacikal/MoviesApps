package com.belajar.moviesapps.reviews;

import android.content.Context;
import android.content.pm.LabeledIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.belajar.moviesapps.R;
import com.belajar.moviesapps.reviews.model.AuthorDetails;
import com.belajar.moviesapps.reviews.model.ResultsItem;
import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    Context context;
    List<ResultsItem> dataReview;

    public ReviewAdapter(Context context, List<ResultsItem> dataReview) {
        this.context = context;
        this.dataReview = dataReview;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {

        String img = dataReview.get(position).getAuthorDetails().getAvatarPath();
        String link_image = "https://image.tmdb.org/t/p/w500"+img;

        Glide.with(context)
                .load(link_image)
                .into(holder.imgReview);

        holder.txtNama.setText(dataReview.get(position).getAuthor());
        holder.txtIsi.setText(dataReview.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return dataReview.size();
    }

    public void addList(List<ResultsItem> list_data){
        dataReview.addAll(list_data);
        notifyDataSetChanged();
    }

    public void clear(){
        dataReview.clear();
        notifyDataSetChanged();
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imgReview;
        TextView txtNama;
        ReadMoreTextView txtIsi;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            imgReview = itemView.findViewById(R.id.img_review);
            txtNama = itemView.findViewById(R.id.text_item_nama_review);
            txtIsi = itemView.findViewById(R.id.text_item_isi_review);
        }
    }
}
