package com.example.edukasikesehatankejiwaan.session;

import android.content.Context;
import android.content.SharedPreferences;

public class Session_akun {
    SharedPreferences pref , pref1;
    SharedPreferences.Editor editor, editor1;
    Context context;
    int private_mode = 0;
    private static final String PREF_NAME= "biodata1";
    private static final String PREF_NAME1= "biodata2";


    public Session_akun(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, private_mode);
        editor = pref.edit();

        pref1 = context.getSharedPreferences(PREF_NAME1, private_mode);
        editor1 = pref1.edit();

    }

    public void setIdAkun (String idakun){
        editor.putString("idakun", idakun);
        editor.commit();
    }

    public String getIdAkun () {
        return pref.getString("idakun", null);
    }


    public void setId (String idakun){
        editor.putString("id", idakun);
        editor.commit();
    }

    public String getId () {
        return pref.getString("id", null);
    }


//    ------------------------------------------



    public void setIdpassword(String password){
        editor1.putString("password", password);
        editor1.commit();
    }

    public String getIdpassword() {
        return pref1.getString("password", null);
    }




    public void logOut(){
        editor.clear();
        editor.commit();

        editor1.clear();
        editor1.commit();


    }
}

