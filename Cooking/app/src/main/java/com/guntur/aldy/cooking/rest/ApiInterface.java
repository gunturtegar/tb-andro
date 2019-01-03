package com.guntur.aldy.cooking.rest;

import com.guntur.aldy.cooking.models.ResultKategoriResponse;
import com.guntur.aldy.cooking.models.ResultResepResponse;
import com.guntur.aldy.cooking.models.ResultReviewResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by alhamdulillah on 10/23/16.
 */
public interface ApiInterface {
    // mendapatkan resep dari server menggunakan method GET
    @GET("Resep/resep")
    Call<ResultResepResponse> getResep();

    @GET("Resep/review/{id_resep}")
    Call<ResultReviewResponse> getReview(@Path("id_resep") int id_resep);

    @Multipart
    @POST("Resep/review")
    Call<ResultReviewResponse> postReview(@Part MultipartBody.Part file,
                                          @Part("id_resep") RequestBody id_resep,
                                          @Part("konten") RequestBody konten
    );
    @GET("Kategori/kategori")
    Call<ResultKategoriResponse> getKategori();
    @GET("Resep/resep/{kata}")
    Call<ResultResepResponse> getResepFind(@Path("kata") String kata);
    @GET("Resep/resepKategori/{kategori}")
    Call<ResultResepResponse> getResepKategori(@Path("kategori") String kategori);

}