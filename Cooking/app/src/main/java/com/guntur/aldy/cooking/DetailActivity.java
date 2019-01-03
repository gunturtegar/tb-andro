package com.guntur.aldy.cooking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.guntur.aldy.cooking.adapters.LangkahAdapter;
import com.guntur.aldy.cooking.adapters.ReviewAdapter;
import com.guntur.aldy.cooking.adapters.SessionManagement;
import com.guntur.aldy.cooking.models.Langkah;
import com.guntur.aldy.cooking.models.ResepResponse;
import com.guntur.aldy.cooking.models.ResultReviewResponse;
import com.guntur.aldy.cooking.models.ReviewResponse;
import com.guntur.aldy.cooking.rest.ApiClient;
import com.guntur.aldy.cooking.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_RESEP = "extra_resep";
    TextView txtJudul,txtDeskripsi,txtBahan;
    ImageView imgGambar;
    Button btnAddReview;
    SessionManagement sessionManagement;
    ResepResponse resep;
    // membuat recylcerView
    private RecyclerView rv_bahan,rv_langkah,rv_review;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        btnAddReview = findViewById(R.id.btnBack);
        txtJudul = findViewById(R.id.txtJudul);
        txtDeskripsi = findViewById(R.id.txtDeskripsi);
        imgGambar = findViewById(R.id.imgGambar);
        txtBahan = findViewById(R.id.txtBahan);
        rv_langkah = findViewById(R.id.rv_langkah);
        rv_review = findViewById(R.id.rv_review);

        sessionManagement = new SessionManagement(this);
        // membuat layoutManager
        mLayoutManager = new LinearLayoutManager(this);
        // set layout manager
//        rv_bahan.setLayoutManager(mLayoutManager);
        rv_langkah.setLayoutManager(mLayoutManager);
        rv_review.setLayoutManager(new LinearLayoutManager(this));
//
        // mengambil intent dari MainActivity
        Intent intent = getIntent();
        // mengambil EXTRA_RESEP dari MainActivity
        resep = (ResepResponse) intent.getSerializableExtra(EXTRA_RESEP);
        // mengganti variable dari data resep
        txtJudul.setText(resep.getJudul());
        txtDeskripsi.setText(resep.getDeskripsi());
        txtBahan.setText(resep.getBahan());
        // membuat variabe list langkah
        ArrayList<Langkah> listLangkah = new ArrayList<Langkah>();
        // memisahkan string dengan pemisah ::
        String[] listBahanData = resep.getLangkah().split("::");
        for (int i = 0; i < listBahanData.length; i++){
            listLangkah.add(new Langkah(i+1,listBahanData[i],""));
        }
//        mAdapter = new BahanAdapter(resep.getBahan());
//        rv_bahan.setAdapter(mAdapter);
        // mengeset adapter berupa langkah adapter
        mAdapter = new LangkahAdapter(listLangkah);
        rv_langkah.setAdapter(mAdapter);
//
//        imgGambar.setImageResource(resep.getFoto());
        // mengganti gambar dengan fungsi picasso (berdasarkan URL)
        Picasso.with(getApplicationContext()).load(resep.getImage()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(imgGambar);
        // fungsi addReview
        btnAddReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pindah activity
                Intent i = new Intent(DetailActivity.this,MakePhotoActivity.class);
                // menyisipkan extra pada intent bernama id_resep dengan value id_resep
                i.putExtra("id_resep",resep.getId());
                // jika blm login tidak dapat menambahkan review
                if (sessionManagement.isLoggedIn()){
                    // jika iya pindah halaman
                    startActivity(i);
                }else{
                    // jika belum menampilan pesan
                    Toast.makeText(DetailActivity.this,"Harus Login Terlebih Dahulu",Toast.LENGTH_LONG).show();
                }
//                finish();
            }
        });

        refreshReview();


    }

    // intinya sama seperti GET di main activity tapi unutk GET review sesuai id_resep
    public void refreshReview(){
        ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResultReviewResponse> mPembeliCall = mApiInterface.getReview(Integer.valueOf(resep.getId()));
        mPembeliCall.enqueue(new Callback<ResultReviewResponse>() {
            @Override
            public void onResponse(Call<ResultReviewResponse> call,
                                   Response<ResultReviewResponse> response) {
                Log.d("Get Review",response.body().getStatus());
                List<ReviewResponse> listReview = response.body().getResult();
                mAdapter = new ReviewAdapter(listReview);
                rv_review.setAdapter(mAdapter);
//                Toast.makeText(DetailActivity.this,"MELBU "+response.body().getMessage(),Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<ResultReviewResponse> call, Throwable t) {
                Log.d("Get Review",t.getMessage());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshReview();
    }
}
