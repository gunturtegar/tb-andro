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

public class RegisterActivity extends AppCompatActivity {

    // untuk mendeklarasikan variable dari layout
    EditText edtNama, edtEmail, edtPassword;
    Button buttonRegister;

    // variable untuk menyimpan data sessionManagement
    SessionManagement sessionManagement;
    UserHelper userHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // mengisi variable dengan komponen yang ada di view
        edtNama = findViewById(R.id.editNama);
        edtEmail = findViewById(R.id.editEmail);
        edtPassword = findViewById(R.id.editPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
        // membuat objek dari sessionManagement dan userHelper
        sessionManagement = new SessionManagement(getApplicationContext());
        userHelper = new UserHelper(this);


        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // jika button register di klik maka akan mengambil nama email dan password
                String nama = edtNama.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                // validasi jika kosong
                if(email.matches("") || password.matches("") || email.trim().isEmpty()|| password.trim().isEmpty()){
                    // mengeluarkan pesan jika kosong
                    Toast.makeText(RegisterActivity.this,"Isi Email dan Password",Toast.LENGTH_LONG).show();
                    return;
                }else{
                    // menambah data ke ddatabase
                    userHelper.addUser(nama,email,password);
                    // membuat session berupa email dan password
                    sessionManagement.createLoginSession(email,password);
                }
                // pindah aktifiti
                goToActivity();
            }

            private void goToActivity() {
                // pindah ke aktifiti main
                Intent mIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(mIntent);
            }
        });
    }
}
