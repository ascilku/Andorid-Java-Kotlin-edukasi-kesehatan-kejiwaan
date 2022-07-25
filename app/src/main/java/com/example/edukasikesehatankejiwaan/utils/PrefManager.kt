package com.example.edukasikesehatankejiwaan.utils

import android.content.Context
import android.content.SharedPreferences

class PrefManager {

    private fun getSharedPreference(context: Context): SharedPreferences {
        return context.getSharedPreferences("Data", 0)
    }

    fun setStatusLog(context: Context, status: Boolean) {
        val editor = getSharedPreference(context).edit()
        editor.putBoolean("statusLog", status)
        editor.apply()
    }

    fun getStatusLog(context: Context): Boolean {
        return getSharedPreference(context).getBoolean("statusLog", false)
    }

    fun setUserName(context: Context, nama: String?){
        val editor = getSharedPreference(context).edit()
        editor.putString("n", nama)
        editor.apply()
    }

    fun getUserName(context: Context): String? {
        return getSharedPreference(context).getString("n", "")
    }

    fun setUserStatus(context: Context, s: String?){
        val editor = getSharedPreference(context).edit()
        editor.putString("s", s)
        editor.apply()
    }

    fun getUserStatus(context: Context): String? {
        return getSharedPreference(context).getString("s", "")
    }

    fun setUserId(context: Context, id: String?){
        val editor = getSharedPreference(context).edit()
        editor.putString("id", id)
        editor.apply()
    }

    fun getUserId(context: Context): String? {
        return getSharedPreference(context).getString("id", "")
    }

    fun setUserLink(context: Context, link: String?){
        val editor = getSharedPreference(context).edit()
        editor.putString("link", link)
        editor.apply()
    }

    fun getUserLink(context: Context): String? {
        return getSharedPreference(context).getString("link", "")
    }

    fun setUserToken(context: Context, token: String?){
        val editor = getSharedPreference(context).edit()
        editor.putString("token", token)
        editor.apply()
    }

    fun getUserToken(context: Context): String? {
        return getSharedPreference(context).getString("token", "")
    }


    fun clear(context: Context) {
        val editor = getSharedPreference(context).edit()
//        editor.remove("Data")
//        editor.remove("nickName")
        editor.remove("statusLogin")
        editor.apply()
    }

}