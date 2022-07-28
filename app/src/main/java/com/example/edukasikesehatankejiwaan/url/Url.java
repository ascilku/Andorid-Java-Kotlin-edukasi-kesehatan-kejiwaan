package com.example.edukasikesehatankejiwaan.url;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Url {
//    static  final  String URL = "http://192.168.233.47/";
    static  final  String URL = "https://2ae8-36-84-58-83.ap.ngrok.io/";
    public static final String BASE_URL = URL+"/edukasi/";

    private static Retrofit retrofit = null;
    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
