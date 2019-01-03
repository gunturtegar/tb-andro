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
import com.guntur.aldy.cooking.models.Resep;
import com.guntur.aldy.cooking.models.ResepResponse;
import com.guntur.aldy.cooking.rest.ApiClient;
import com.guntur.aldy.cooking.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;
 // berfungsi merubah tampilan list dari recycler view
public class ResepRestAdapter extends RecyclerView.Adapter<ResepRestAdapter.ResepRestHolder> {

     // menyimpan data list resepResponse
    List<ResepResponse> dataset;

    // membuat object dan mengisi dataset
    public ResepRestAdapter(List<ResepResponse> dataset) {
        this.dataset = dataset;
    }

     // mengambil layout listResep
    @NonNull
    @Override
    public ResepRestHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_resep, parent,false);
        return new ResepRestHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ResepRestHolder holder, int i) {
        // mengambil class dari dataset index ke-i
        ResepResponse r = dataset.get(i);
//        holder.imgResep.setImageResource(r.getImage());
        // merubah text sesuai dengan dataset
        holder.txtJudul.setText(r.getJudul());
        holder.txtDeskripsi.setText(r.getDeskripsi());
        Picasso.with(holder.itemView.getContext()).load(r.getImage()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(holder.imgResep);
    }

    // membuat jumlah list yang dibutuhkan
    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class ResepRestHolder extends RecyclerView.ViewHolder {
    // membuat variable untuk menyimpan komponen layout
        ImageView imgResep;
        TextView txtJudul,txtDeskripsi;
        public ResepRestHolder(@NonNull View itemView) {
            super(itemView);

            // mengambil komponen dari layout
            imgResep = itemView.findViewById(R.id.imgResep);
            txtJudul = itemView.findViewById(R.id.txtJudul);
            txtDeskripsi = itemView.findViewById(R.id.txtDeskripsi);
        }
    }
}
