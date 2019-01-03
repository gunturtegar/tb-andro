package com.guntur.aldy.cooking.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.guntur.aldy.cooking.R;
import com.guntur.aldy.cooking.models.Resep;

import java.util.ArrayList;

public class ResepAdapters extends RecyclerView.Adapter<ResepAdapters.ResepHolder> {
    ArrayList<Resep> dataset;

    public ResepAdapters(ArrayList<Resep> dataset) {
        this.dataset = dataset;
    }

    @NonNull
    @Override
    public ResepHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_resep, parent,false);
        return new ResepHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ResepHolder holder, int i) {
        Resep r = dataset.get(i);
        holder.imgResep.setImageResource(r.getFoto());
        holder.txtJudul.setText(r.getJudul());
        holder.txtDeskripsi.setText(r.getDeskripsi());

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class ResepHolder extends RecyclerView.ViewHolder {

        ImageView imgResep;
        TextView txtJudul,txtDeskripsi;
        public ResepHolder(@NonNull View itemView) {
            super(itemView);
            imgResep = itemView.findViewById(R.id.imgResep);
            txtJudul = itemView.findViewById(R.id.txtJudul);
            txtDeskripsi = itemView.findViewById(R.id.txtDeskripsi);
        }
    }
}
