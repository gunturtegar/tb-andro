package com.guntur.aldy.cooking.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guntur.aldy.cooking.R;
import com.guntur.aldy.cooking.models.Bahan;

import java.util.ArrayList;

public class BahanAdapter extends RecyclerView.Adapter<BahanAdapter.BahanHolder> {
    ArrayList<Bahan> dataBahan;

    public BahanAdapter(ArrayList<Bahan> dataBahan) {
        this.dataBahan = dataBahan;
    }

    @NonNull
    @Override
    public BahanHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_bahan, parent,false);
        return new BahanHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull BahanHolder bahanHolder, int i) {
        Bahan b = dataBahan.get(i);
        bahanHolder.txtBahan.setText(b.getNama());
        bahanHolder.txtJumlah.setText(String.valueOf(b.getJumlah()));
        bahanHolder.txtSatuan.setText(b.getSatuan());
    }

    @Override
    public int getItemCount() {
        return dataBahan.size();
    }

    public class BahanHolder extends RecyclerView.ViewHolder{
        TextView txtBahan,txtJumlah,txtSatuan;
        public BahanHolder(@NonNull View itemView) {
            super(itemView);
            txtBahan = itemView.findViewById(R.id.txtBahan);
            txtJumlah = itemView.findViewById(R.id.txtJumlah);
            txtSatuan = itemView.findViewById(R.id.txtSatuan);
        }
    }
}
