package com.example.edukasikesehatankejiwaan.apiinterface;


import com.example.edukasikesehatankejiwaan.artikelnew.Value;
import com.example.edukasikesehatankejiwaan.artikelnew.ValueTambahArtikel;
import com.example.edukasikesehatankejiwaan.login.ValueLogin;
import com.example.edukasikesehatankejiwaan.lokasi.ValueLokasi;
import com.example.edukasikesehatankejiwaan.tempat.ValueTempat;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface Api_Interface {

    @GET("lihatSemuaArtikel.php")
    Call<Value> getUser();


    @FormUrlEncoded
    @POST("tambahArtikel.php")
    Call<ValueTambahArtikel> tambahArtikel(
            @Field("judul") String judul,
            @Field("deksripsi") String deksripsi,
            @Field("pengarang") String pengarang,
            @Field("url") String url
    );

    @GET("lihatlokasi.php")
    Call<ValueLokasi> getLokasi();

    @FormUrlEncoded
    @POST("tambahLokasi.php")
    Call<ValueTambahArtikel> tambahLokasi(
            @Field("lokasi") String lokasi
    );

    @FormUrlEncoded
    @POST("lihatTempat.php")
    Call<ValueTempat> getTempat(@Field("id_lokasi") String id_lokasi);

    @FormUrlEncoded
    @POST("tambahTempat.php")
    Call<ValueTambahArtikel> tambahTempat(
            @Field("id_lokasi") String id_lokasi,
            @Field("nama_tempat") String nama_tempat,
            @Field("alamat") String alamat,
            @Field("jam") String jam,
            @Field("telepon") String telepon
    );

    @FormUrlEncoded
    @POST("register.php")
    Call<ValueTambahArtikel> tambahUser(
            @Field("username") String username,
            @Field("password") String password,
            @Field("status") String status
    );

    @FormUrlEncoded
    @POST("login.php")
    Call<ValueLogin> login(
            @Field("username") String username,
            @Field("password") String password
    );

//    @FormUrlEncoded
//    @POST("login.php")
//    Call<Value_Login> login(@Field("nomor") String email, @Field("password") String password);
//

//
//    @FormUrlEncoded
//    @POST("lihat_perkara_saya.php")
//    Call<Value_Perkara_Saya> getDataPerkaraSaya(@Field("id") String id);

//    @FormUrlEncoded
//    @POST("update_perkara.php")
//    Call<Value_AturJadwal> aturJadwal(@Field("id") String id, @Field("jadwal_perkara") String jadwal_perkara);
//

//
//    @FormUrlEncoded
//    @POST("gantiStatusSidang.php")
//    Call<Value_Ganti_Status> gantiStatusSidang(@Field("status") String status, @Field("id_perkara") String id_perkara);
//
//    @FormUrlEncoded
//    @POST("ganti_tunda.php")
//    Call<Value_Ganti_Status> gantiTunda(@Field("status") String status, @Field("id_perkara") String id_perkara, @Field("alasan") String alasan, @Field("akses1") String akses1);

//    @FormUrlEncoded
//    @POST("tambahJadwal.php")
//    Call<Value_Ganti_Status> tambahJadwal(
//                                            @Field("nama_pengguna") String nama_pengguna,
//                                            @Field("nomor") String nomor,
//                                            @Field("nik") String nik,
//                                            @Field("password") String password,
//                                            @Field("alamat") String alamat,
//                                            @Field("jenis_perkara") String jenis_perkara,
//                                            @Field("keterangan") String keterangan,
//                                            @Field("tgl") String tgl,
//                                            @Field("waktu") String waktu
//    );
//
//    @FormUrlEncoded
//    @POST("tambah_gugat.php")
//    Call<Value_Gugat> tambahgugat(
//            @Field("id_perkara") String id_perkara,
//            @Field("nama_pengguna") String nama_pengguna,
//            @Field("nomor") String nomor,
//            @Field("nik") String nik,
//            @Field("password") String password,
//            @Field("alamat") String alamat
//           );
}

