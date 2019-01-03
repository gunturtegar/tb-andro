package com.guntur.aldy.cooking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.guntur.aldy.cooking.adapters.UserHelper;
import com.guntur.aldy.cooking.adapters.SessionManagement;

public class LoginActivity extends AppCompatActivity {
    // variable untuk menyimpan komponen view di layout login
    EditText edtEmail, edtPassword;
    Button buttonLogin, buttonRegister, buttonHome;
    // variable untuk menyimpan objek dari sessionManagement dan userHelper
    SessionManagement sessionManagement;
    UserHelper userHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // untuk mengambil komponen dari layout berdasarkan ID
        edtEmail = findViewById(R.id.editEmail);
        edtPassword = findViewById(R.id.editPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);
        buttonHome = findViewById(R.id.buttonHome);

        // membuat objek dari sessionManagement disimpan ke sessionManagement
        sessionManagement = new SessionManagement(getApplicationContext());
        userHelper = new UserHelper(this);


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // digunakan untuk mengambil data yang di inputkan
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                // validasi
                if(email.matches("") || password.matches("") || email.trim().isEmpty()|| password.trim().isEmpty()){
                    // menampilkan pesan 'kosong'
                    Toast.makeText(LoginActivity.this,"Isi Email dan Password",Toast.LENGTH_LONG).show();
                    return;
                }else{
                    // cek email dan password terdaftar di database
                    if (userHelper.autentikasi(email,password)){
                        // jika terdaftar maka akan mengisi session
                        sessionManagement.createLoginSession(email,password);
                        // pindah halaman
                        goToActivity();
                    }else{
                        // jika tdk terdaftar makan menampilkan pesan "usrnm tidak tedaftar"
                        Toast.makeText(LoginActivity.this,"Username tidak terdaftar",Toast.LENGTH_LONG).show();
                    }

                }
            }

            private void goToActivity() {
                // pindah ke aktifiti lain
                Intent mIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(mIntent);
            }
        });


        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });

        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);
            }
        });


    }
}
