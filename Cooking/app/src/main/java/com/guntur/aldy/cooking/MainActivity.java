package com.guntur.aldy.cooking;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.guntur.aldy.cooking.adapters.ClickListener;
import com.guntur.aldy.cooking.adapters.RecyclerTouchListener;
import com.guntur.aldy.cooking.adapters.ResepRestAdapter;
import com.guntur.aldy.cooking.adapters.SessionManagement;
import com.guntur.aldy.cooking.models.Langkah;
import com.guntur.aldy.cooking.models.Resep;
import com.guntur.aldy.cooking.models.ResepResponse;
import com.guntur.aldy.cooking.models.ResultResepResponse;
import com.guntur.aldy.cooking.rest.ApiClient;
import com.guntur.aldy.cooking.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // mengambil recycler view
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    // variable untuk menyimpan API interface
    ApiInterface mApiInterface;

    // variable komponen layout
    EditText edtSearch;
    Button btnSearch;

    // variable untuk menyimpan sessionManagement
    SessionManagement sessionManagement;

    // menyimpan data list resep
    List<ResepResponse> listResep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // mengambil komponen view
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // mengambil komponen view
        mRecyclerView = (RecyclerView) findViewById(R.id.rvResep);
        edtSearch=(EditText) findViewById(R.id.edtSearch);
        btnSearch=(Button) findViewById(R.id.btnSearch);

        // membuat objek dari linier layout manager
        mLayoutManager = new LinearLayoutManager(this);

        // merubah layout manager manjadi linier layout manager
        mRecyclerView.setLayoutManager(mLayoutManager);

        // mengambil sessionManagement
        sessionManagement = new SessionManagement(this);


        final ArrayList<Resep> dataResep = new ArrayList<Resep>();

        TextView txtUsername = findViewById(R.id.txtUsername);
        txtUsername.setText(sessionManagement.getUserInformation().get(sessionManagement.KEY_EMAIL));

        ArrayList<Langkah> langkah1 = new ArrayList<Langkah>();

        langkah1.add(new Langkah(1,"Pecahkan Telur","Pecahkan telur 1 butir"));
        langkah1.add(new Langkah(2,"Tuangkan Tepung","Tuangkan tepung secara berkala"));
        dataResep.add(new Resep("Ayam_kecap","percobaan","Telur, Tepung",langkah1,R.drawable.ayamkecap));




//        mAdapter = new ResepAdapters(dataResep);
//        mRecyclerView.setAdapter(mAdapter);
        // memanggil fungsi refreshList
        refreshList();

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent i = new Intent(getApplicationContext(),DetailActivity.class);
                i.putExtra(DetailActivity.EXTRA_RESEP, listResep.get(position));
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String kata = edtSearch.getText().toString();
                refreshListSearch(kata);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshList();
    }

    // mengambil data dari server
    public void refreshList(){

        // mendapatkan client dari server
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        // memanggil fungsi getResep yang ada di ApiInterface
        Call<ResultResepResponse> mResep = mApiInterface.getResep();

        // jika server merespon
        mResep.enqueue(new Callback<ResultResepResponse>() {
            @Override
            public void onResponse(Call<ResultResepResponse> call,
                                   Response<ResultResepResponse> response) {
                // menulis pada log
                Log.d("Get Pembeli",response.body().getStatus());

                // mengambil result yang sudah di panggil
                listResep = response.body().getResult();

                // membuat ResepRestAdapter
                mAdapter = new ResepRestAdapter(listResep);

                // mengganti resep adapter
                mRecyclerView.setAdapter(mAdapter);
            }

            // jika server tidak merespon
            @Override
            public void onFailure(Call<ResultResepResponse> call, Throwable t) {

                // menulis pada log
                Log.d("Get Pembeli",t.getMessage());
            }
        });
    }
    public void refreshListSearch(String kata){
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResultResepResponse> mPembeliCall = mApiInterface.getResepFind(kata);
        mPembeliCall.enqueue(new Callback<ResultResepResponse>() {
            @Override
            public void onResponse(Call<ResultResepResponse> call,
                                   Response<ResultResepResponse> response) {
                Log.d("Get Pembeli",response.body().getStatus());
                listResep = response.body().getResult();
                mAdapter = new ResepRestAdapter(listResep);
                mRecyclerView.setAdapter(mAdapter);
            }
            @Override
            public void onFailure(Call<ResultResepResponse> call, Throwable t) {
                Log.d("Get Pembeli",t.getMessage());
            }
        });
    }
    public void refreshListKategori(String kategori){
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResultResepResponse> mPembeliCall = mApiInterface.getResepKategori(kategori);
        mPembeliCall.enqueue(new Callback<ResultResepResponse>() {
            @Override
            public void onResponse(Call<ResultResepResponse> call,
                                   Response<ResultResepResponse> response) {
                Log.d("Get Pembeli",response.body().getStatus());
                listResep = response.body().getResult();
                mAdapter = new ResepRestAdapter(listResep);
                mRecyclerView.setAdapter(mAdapter);
            }
            @Override
            public void onFailure(Call<ResultResepResponse> call, Throwable t) {
                Log.d("Get Pembeli",t.getMessage());
            }
        });
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.kat1) {
            refreshListKategori("1");
        } else if (id == R.id.kat2) {
            refreshListKategori("2");
        } else if (id == R.id.kat3) {
            refreshListKategori("3");
        } else if (id == R.id.kat4) {
            refreshListKategori("4");
        } else if (id == R.id.kat5) {
            refreshListKategori("5");



        }else if(id == R.id.nav_login){
            Intent i = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(i);
        }else if(id == R.id.nav_logout){
            sessionManagement.logoutUser();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
