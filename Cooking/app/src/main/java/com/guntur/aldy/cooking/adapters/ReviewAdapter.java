package com.guntur.aldy.cooking.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.guntur.aldy.cooking.R;
import com.guntur.aldy.cooking.models.ReviewResponse;
import com.guntur.aldy.cooking.rest.ApiClient;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewHolder> {
    List<ReviewResponse> listReview;

    public ReviewAdapter(List<ReviewResponse> listReview) {
        this.listReview = listReview;
    }

    @NonNull
    @Override
    public ReviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_review, viewGroup,false);
        return new ReviewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewHolder reviewHolder, int i) {
        ReviewResponse rr = listReview.get(i);
        reviewHolder.txtKonten.setText(rr.getKonten());
        Picasso.with(reviewHolder.itemView.getContext()).load(ApiClient.BASE_URL+"application/"+rr.getPhoto_id()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(reviewHolder.imgPhoto);

    }

    @Override
    public int getItemCount() {
        return listReview.size();
    }

    public class ReviewHolder extends RecyclerView.ViewHolder{
        ImageView imgPhoto;
        TextView txtKonten;
        public ReviewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.imgPhoto);
            txtKonten = itemView.findViewById(R.id.txtKonten);
        }
    }
}
