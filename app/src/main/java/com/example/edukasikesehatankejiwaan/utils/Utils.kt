package com.example.edukasikesehatankejiwaan.utils

import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.example.edukasikesehatankejiwaan.R

object Utils {
    fun encodeEmail(userEmail: String): String {
        return userEmail.replace(".", ",")
    }

    fun encodeEmailComa(userEmail: String): String {
        return userEmail.replace(",", ".")
    }

    fun showAlert(layoutInflater : LayoutInflater, context : Context, i : Long) {
        val view = layoutInflater.inflate(R.layout.progress_bar, null)
        val alert = AlertDialog.Builder(context, R.style.CustomDialog)
        alert.setView(view)
        alert.setCancelable(false)
        val dialog = alert.create()
        dialog.setOnShowListener {
            Handler().postDelayed({
                it.dismiss()
            },i)
        }
        dialog.show()
    }
}