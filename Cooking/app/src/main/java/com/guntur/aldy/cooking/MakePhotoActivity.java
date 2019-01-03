package com.guntur.aldy.cooking;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.guntur.aldy.cooking.adapters.ResepRestAdapter;
import com.guntur.aldy.cooking.models.KategoriResponse;
import com.guntur.aldy.cooking.models.ResultKategoriResponse;
import com.guntur.aldy.cooking.models.ResultResepResponse;
import com.guntur.aldy.cooking.models.ResultReviewResponse;
import com.guntur.aldy.cooking.rest.ApiClient;
import com.guntur.aldy.cooking.rest.ApiInterface;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MakePhotoActivity extends AppCompatActivity {

    Context mContext;
    ImageView mImageView;
    Button btAddPhotoId, btAddBack, btAddData;
    EditText edtAddNamaPembeli, edtKonten;

    TextView tvAddMessage;
    String imagePath = "";
    // untuk menyimpan request image picture
    private static final int REQUEST_IMAGE_PICTURE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_photo);

        mContext = getApplicationContext();
        mImageView = (ImageView) findViewById(R.id.imgAddPhotoId);
        btAddPhotoId = (Button) findViewById(R.id.btAddPhotoId);
        edtAddNamaPembeli = (EditText) findViewById(R.id.edtAddNamaPembeli);
        edtKonten = (EditText) findViewById(R.id.edtKonten);
        btAddData = (Button) findViewById(R.id.btAddData);
        btAddBack = (Button) findViewById(R.id.btAddBack);
        tvAddMessage = (TextView) findViewById(R.id.tvAddMessage);



        // mendapatkan intent dari detail activity
        Intent intent = getIntent();


        // set resep menjadi sesuai dengan id_resep
        edtAddNamaPembeli.setText(intent.getStringExtra("id_resep"));

        // kalau di pencet button insert maka akan tampil
        btAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // mendapatkan client dari server
                ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
                // membuat variable untuk menyimpan gambar
                MultipartBody.Part body = null;
                if (!imagePath.isEmpty()){
                    //File creating from selected URL
                    File file = new File(imagePath);
                    // create RequestBody instance from file
                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    // MultipartBody.Part is used to send also the actual file name
                    body = MultipartBody.Part.createFormData("photo_id", file.getName(), requestFile);
                }

                // menyimpan nama
                RequestBody reqNama = MultipartBody.create(MediaType.parse("multipart/form-data"), (edtAddNamaPembeli.getText().toString().isEmpty())?"":edtAddNamaPembeli.getText().toString());

                RequestBody reqKonten = MultipartBody.create(MediaType.parse("multipart/form-data"), (edtKonten.getText().toString().isEmpty())?"":edtKonten.getText().toString());
                // untuk memanggil post review
                Call<ResultReviewResponse> mPembeliCall = mApiInterface.postReview(body , reqNama , reqKonten);
                mPembeliCall.enqueue(new Callback<ResultReviewResponse>() {
                    // jika merespon akan menampilkan
                    @Override
                    public void onResponse(Call<ResultReviewResponse> call, Response<ResultReviewResponse> response) {
                        Log.d("Insert Retrofit",response.body().getStatus());
                        Toast.makeText(MakePhotoActivity.this,"Pesan Insert ",Toast.LENGTH_LONG).show();
                        tvAddMessage.setText("Insert Berhasil");
                    }
                    @Override
                    public void onFailure(Call<ResultReviewResponse> call, Throwable t) {
                        Log.d("Insert Retrofit", t.getMessage());
                        Toast.makeText(MakePhotoActivity.this,"Pesan Insert ",Toast.LENGTH_LONG).show();
                        tvAddMessage.setText("Insert Berhasil");
                    }
                });
            }
        });

        // membuka galeri
        btAddPhotoId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_PICK);
                Intent intentChoose = Intent.createChooser(galleryIntent, "Pilih Gambar Untuk Di upload");
                startActivityForResult(intentChoose, 10);
            }
        });

        //button back
        btAddBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // jika gambar yang di pilih di galeri berhasil di ambil, maka
        if (resultCode == RESULT_OK && requestCode == 10) {
            // jika data gagal di ambil menampilkan pesan
            if (data == null) {
                Toast.makeText(mContext, "Gambar Gagal Di load",
                        Toast.LENGTH_LONG).show();
                return;
            }

            // mendapatka Uri
            Uri selectedImage = data.getData();

            // mengambil semua data yang ada di media store/galeri
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn,
                    null, null, null);

            // mencari data sesuai dengan Urinya
            if (cursor != null) {
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                // mengambil alamat tempat gambar
                imagePath = cursor.getString(columnIndex);

                // mengganti gambar sesuai dengan gambar yang di pilih
                Picasso.with(mContext).load(new File(imagePath)).fit().into(mImageView);
                Glide.with(mContext).load(new File(imagePath)).into(mImageView);
                cursor.close();
            } else {
                Toast.makeText(mContext, "Gambar Gagal Di load",
                        Toast.LENGTH_LONG).show();
            }
        }

        // jika mengambil foto, maka
        if (requestCode == REQUEST_IMAGE_PICTURE && resultCode== RESULT_OK){
            // mendapatkan data foto kamera
            Bundle extras = data.getExtras();
            // mendapatkan hasil jepretan berupa bitmap
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            // merubah gambar sesuai dengan bitmap
            mImageView.setImageBitmap(imageBitmap);

            // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
            Uri tempUri = getImageUri(getApplicationContext(), imageBitmap);

            // CALL THIS METHOD TO GET THE ACTUAL PATH
            File finalFile = new File(getRealPathFromURI(tempUri));

            imagePath = getRealPathFromURI(tempUri);
        }
    }

    public void takePicture(View view){
        Intent imageTakeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (imageTakeIntent.resolveActivity(getPackageManager())  != null ){
            startActivityForResult(imageTakeIntent,REQUEST_IMAGE_PICTURE);
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        String path = "";
        if (getContentResolver() != null) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);
                cursor.close();
            }
        }
        return path;
    }

}
