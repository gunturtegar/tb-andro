package com.guntur.aldy.cooking.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guntur.aldy.cooking.R;
import com.guntur.aldy.cooking.models.Langkah;

import java.util.ArrayList;

public class LangkahAdapter extends RecyclerView.Adapter<LangkahAdapter.LangkahHolder> {
    ArrayList<Langkah> dataLangkah;

    public LangkahAdapter(ArrayList<Langkah> dataLangkah) {
        this.dataLangkah = dataLangkah;
    }

    @NonNull
    @Override
    public LangkahHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_langkah, parent,false);
        return new LangkahHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull LangkahHolder langkahHolder, int i) {
        Langkah l = dataLangkah.get(i);
        langkahHolder.txtNo.setText(String.valueOf(l.getNo()));
        langkahHolder.txtJudul.setText(l.getJudul());
        langkahHolder.txtDeskripsi.setText(l.getKeterangan());
    }

    @Override
    public int getItemCount() {
        return dataLangkah.size();
    }

    public class LangkahHolder extends RecyclerView.ViewHolder{
        TextView txtNo,txtJudul,txtDeskripsi;
        public LangkahHolder(@NonNull View itemView) {
            super(itemView);
            txtNo = itemView.findViewById(R.id.txtNo);
            txtJudul = itemView.findViewById(R.id.txtJudul);
            txtDeskripsi = itemView.findViewById(R.id.txtDeskripsi);
        }
    }
}
