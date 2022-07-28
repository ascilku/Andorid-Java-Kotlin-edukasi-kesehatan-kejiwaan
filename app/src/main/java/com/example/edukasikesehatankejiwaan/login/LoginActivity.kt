package com.example.edukasikesehatankejiwaan.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import com.example.edukasikesehatankejiwaan.MainActivity
import com.example.edukasikesehatankejiwaan.R
import com.example.edukasikesehatankejiwaan.SplashScren
import com.example.edukasikesehatankejiwaan.apiinterface.Api_Interface
import com.example.edukasikesehatankejiwaan.artikelnew.Arikel
import com.example.edukasikesehatankejiwaan.artikelnew.ValueTambahArtikel
import com.example.edukasikesehatankejiwaan.databinding.ActivityBiodataBinding
import com.example.edukasikesehatankejiwaan.model.DataUser
import com.example.edukasikesehatankejiwaan.session.Session_akun
import com.example.edukasikesehatankejiwaan.url.Url
import com.example.edukasikesehatankejiwaan.utils.Cons.DB
import com.example.edukasikesehatankejiwaan.utils.Cons.kodeUniq
import com.example.edukasikesehatankejiwaan.utils.Cons.prefManager
import com.example.edukasikesehatankejiwaan.utils.Utils.encodeEmail
import com.example.edukasikesehatankejiwaan.utils.Utils.showAlert
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class LoginActivity : AppCompatActivity() {
    lateinit var b : ActivityBiodataBinding
    lateinit var bottomSheetDialog : BottomSheetDialog
    lateinit var inflater: LayoutInflater

    var model_logins: List<ModelLogin> = ArrayList<ModelLogin>()
    var session_akun: Session_akun? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityBiodataBinding.inflate(layoutInflater)
        setContentView(b.root)
        inflater = layoutInflater
        bottomSheetDialog = BottomSheetDialog(
            this@LoginActivity, R.style.BottomSheetDialogTheme
        )

        //        Button login_daftar = findViewById(R.id.login_daftar);
        session_akun = Session_akun(this)

        if (session_akun!!.idAkun != null) {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }

        onClick()
    }

    override fun onStart() {
        super.onStart()
        if (prefManager.getStatusLog(baseContext)){
            startActivity(Intent(this, SplashScren::class.java))
            finish()
        }
    }
    private fun onClick() {
        b.mSiswa.setOnClickListener {
            val bView = LayoutInflater.from(applicationContext).inflate(
                R.layout.bottom_sheet_login_siswa_layout, findViewById<LinearLayout>(R.id.bottomSheet)
            )

            bView.findViewById<Button>(R.id.mGuruu).setOnClickListener {
                val nama = bView.findViewById<AppCompatEditText>(R.id.edtNameS).text.toString()
                val pass = bView.findViewById<AppCompatEditText>(R.id.edtPassS).text.toString()
//                Toast.makeText(applicationContext, "test $nama \n $pass", Toast.LENGTH_SHORT).show()
                if (nama == ""){
                    Toast.makeText(applicationContext, "harap masukkan email", Toast.LENGTH_SHORT).show()
                }
                else if (!nama.contains("@gmail.com")){
                    Toast.makeText(applicationContext, "harap masukkan alamat email yang valid", Toast.LENGTH_SHORT).show()
                }
                else if (nama.contains("@gmail.com")){
//                    val encodeEmail : String = encodeEmail(nama.toLowerCase())
//                    val link = encodeEmail+pass
//                    cekData(link, nama, pass)
                    aksesLogin(nama, pass)
                }
                bottomSheetDialog.dismiss()
            }

            bottomSheetDialog.setContentView(bView)
            bottomSheetDialog.show()
        }

//        b.mGuru.setOnClickListener {
//            val bView = LayoutInflater.from(applicationContext).inflate(
//                R.layout.bottom_sheet_login_guru_layout, findViewById<LinearLayout>(R.id.bottomSheet)
//            )
//
//            bView.findViewById<Button>(R.id.mGuruu).setOnClickListener {
//                val nama = bView.findViewById<AppCompatEditText>(R.id.edtNamee).text.toString()
//                val pass = bView.findViewById<AppCompatEditText>(R.id.edtPass).text.toString()
//                if (nama == ""){
//                    Toast.makeText(applicationContext, "harap masukkan email", Toast.LENGTH_SHORT).show()
//                } else {
//                    val encodeEmail : String = encodeEmail(nama.toLowerCase())
//                    val link = encodeEmail+pass
//                    cekData(link, nama, pass)
//                }
//                bottomSheetDialog.dismiss()
//            }
//
//            bottomSheetDialog.setContentView(bView)
//            bottomSheetDialog.show()
//        }

        b.daftarS.setOnClickListener {
            val bView = LayoutInflater.from(applicationContext).inflate(
                R.layout.bottom_sheet_daftar_siswa_layout, findViewById<LinearLayout>(R.id.bottomSheet)
            )

            bView.findViewById<Button>(R.id.mGuruu).setOnClickListener {
                val nama = bView.findViewById<AppCompatEditText>(R.id.edtDfNameS).text.toString()
                val pass = bView.findViewById<AppCompatEditText>(R.id.edtDfPassS).text.toString()
//                Toast.makeText(applicationContext, "test $nama \n $pass", Toast.LENGTH_SHORT).show()
                if (!nama.contains("@gmail.com")){
                    Toast.makeText(applicationContext, "harap masukkan alamat email yang valid", Toast.LENGTH_SHORT).show()
                }
                else {
//                    val encodeEmail : String = encodeEmail(nama.toLowerCase())
//                    val link = encodeEmail+pass
//                    if (nama.isEmpty() && pass.isEmpty()){
//                        Toast.makeText(applicationContext, "harap lengkapi data", Toast.LENGTH_SHORT).show()
//                    }
//                    val status = "siswa"
//                    insertData(nama, pass, link, status)
//                    bottomSheetDialog.dismiss()

                    upload(nama, pass, "Siswa")
                }

            }

            bottomSheetDialog.setContentView(bView)
            bottomSheetDialog.show()
        }

        b.daftarG.setOnClickListener {
            val bView = LayoutInflater.from(applicationContext).inflate(
                R.layout.bottom_sheet_daftar_guru_layout, findViewById<LinearLayout>(R.id.bottomSheet)
            )

            bView.findViewById<Button>(R.id.mGuruu).setOnClickListener {
                val nama = bView.findViewById<AppCompatEditText>(R.id.edtDfNameS).text.toString()
                val pass = bView.findViewById<AppCompatEditText>(R.id.edtDfPassS).text.toString()
//                Toast.makeText(applicationContext, "test $nama \n $pass", Toast.LENGTH_SHORT).show()
                if (!nama.contains("@gmail.com")){
                    Toast.makeText(applicationContext, "harap masukkan alamat email yang valid", Toast.LENGTH_SHORT).show()
                }
                else {
//                    val encodeEmail : String = encodeEmail(nama.toLowerCase())
//                    val link = encodeEmail+pass
//                    if (nama.isEmpty() && pass.isEmpty()){
//                        Toast.makeText(applicationContext, "harap lengkapi data", Toast.LENGTH_SHORT).show()
//                    }
//                    val status = "siswa"
//                    insertData(nama, pass, link, status)
//                    bottomSheetDialog.dismiss()

                    upload(nama, pass, "Guru")
                }

            }

            bottomSheetDialog.setContentView(bView)
            bottomSheetDialog.show()
        }

        b.daftarA.setOnClickListener {
            val bView = LayoutInflater.from(applicationContext).inflate(
                R.layout.bottom_sheet_daftar_admin_layout, findViewById<LinearLayout>(R.id.bottomSheet)
            )

            bView.findViewById<Button>(R.id.mGuruu).setOnClickListener {
                val nama = bView.findViewById<AppCompatEditText>(R.id.edtDfNameS).text.toString()
                val pass = bView.findViewById<AppCompatEditText>(R.id.edtDfPassS).text.toString()
//                Toast.makeText(applicationContext, "test $nama \n $pass", Toast.LENGTH_SHORT).show()
                if (!nama.contains("@gmail.com")){
                    Toast.makeText(applicationContext, "harap masukkan alamat email yang valid", Toast.LENGTH_SHORT).show()
                }
                else {
//                    val encodeEmail : String = encodeEmail(nama.toLowerCase())
//                    val link = encodeEmail+pass
//                    if (nama.isEmpty() && pass.isEmpty()){
//                        Toast.makeText(applicationContext, "harap lengkapi data", Toast.LENGTH_SHORT).show()
//                    }
//                    val status = "siswa"
//                    insertData(nama, pass, link, status)
//                    bottomSheetDialog.dismiss()

                    upload(nama, pass, "Admin")
                }

            }

            bottomSheetDialog.setContentView(bView)
            bottomSheetDialog.show()
        }

//        b.daftarG.setOnClickListener {
//            val bView = LayoutInflater.from(applicationContext).inflate(
//                R.layout.bottom_sheet_daftar_guru_layout, findViewById<LinearLayout>(R.id.bottomSheet)
//            )
//
//            bView.findViewById<Button>(R.id.mGuruu).setOnClickListener {
//                val nama = bView.findViewById<AppCompatEditText>(R.id.edtDfNameG).text.toString()
//                val pass = bView.findViewById<AppCompatEditText>(R.id.edtDfPassG).text.toString()
//                val uniq = bView.findViewById<AppCompatEditText>(R.id.edtUniq).text.toString()
//
//                val encodeEmail : String = encodeEmail(nama.toLowerCase())
//                val link = encodeEmail+pass
//                if (nama.isEmpty() || pass.isEmpty() || uniq.isEmpty()){
//                    Toast.makeText(applicationContext, "harap lengkapi data", Toast.LENGTH_SHORT).show()
//                }
//
//                if (uniq == kodeUniq){
//                    val status = "guru"
//                    insertData(nama, pass, link, status)
//                }else Toast.makeText(applicationContext, "kode uniq anda salah", Toast.LENGTH_SHORT).show()
//
//                bottomSheetDialog.dismiss()
//            }
//
//            bottomSheetDialog.setContentView(bView)
//            bottomSheetDialog.show()
//        }

    }

    private fun cekData(link: String, nama: String, pass: String) {
        showAlert(layoutInflater, this, 500)
        DB.child("Login")
            .orderByChild("link")
            .equalTo(link)
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()){
//                        prefManager.setStatusLog(baseContext, true)
                        prefManager.setUserName(baseContext, nama)
                        prefManager.setUserLink(baseContext,link)
//                        prefManager.setUserStatus(baseContext, )
                        startActivity(Intent(this@LoginActivity, SplashScren::class.java))
                        finish()
                    }else{
                        Toast.makeText(applicationContext, "anda belum terdaftar", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(applicationContext, "koneksi anda buruk", Toast.LENGTH_SHORT).show()
                }

            })
    }

    private fun insertData(nama: String, pass: String, link: String, status: String) {
        showAlert(layoutInflater, this, 2000)
        val dataUser = DataUser(
            nama,pass,link,status
        )
        DB.child("Login/$link")
            .setValue(dataUser)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(applicationContext, "Data berhasil di masukkan", Toast.LENGTH_SHORT).show()
                    prefManager.setUserLink(baseContext,link)
                }else{
                    Toast.makeText(applicationContext, "Gagal memasukkan data", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener{
                Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
            }
    }

    fun upload(s_judul: String?, s_deksripsi: String? , s_status: String?) {
//        Toast.makeText(this, s_judul+ " " +s_deksripsi,Toast.LENGTH_SHORT).show();
        val mApiInterface = Url.getClient().create(
            Api_Interface::class.java
        )
        val call = mApiInterface.tambahUser(s_judul, s_deksripsi, s_status)
        call.enqueue(object : Callback<ValueTambahArtikel?> {
            override fun onResponse(
                call: Call<ValueTambahArtikel?>,
                response: Response<ValueTambahArtikel?>
            ) {
                Toast.makeText(applicationContext, "Berhasil Mendaftar", Toast.LENGTH_SHORT).show()
                if (response.body() != null) {
                    val value = response.body()!!.value
                    if (value == "1") {
                        val home = Intent(applicationContext, LoginActivity::class.java)
                        startActivity(home)
                        finish()
                        Toast.makeText(applicationContext, "Berhasil Mendaftar", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(applicationContext, "Gagal Input Data", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    Toast.makeText(applicationContext, "Respons Tidak Ada", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<ValueTambahArtikel?>, t: Throwable) {}
        })
    }

    fun aksesLogin(terimaEmail: String?, terimaPassword: String?) {
//        Toast.makeText(this, session_akun?.getIdAkun(), Toast.LENGTH_SHORT).show();
        val mApiInterface = Url.getClient().create(Api_Interface::class.java)
        val call: Call<ValueLogin> = mApiInterface.login(terimaEmail, terimaPassword)
        call.enqueue(object : Callback<ValueLogin?> {
            override fun onResponse(call: Call<ValueLogin?>, response: Response<ValueLogin?>) {
                if (response.body() != null) {
                    val value: String = response.body()!!.getValue()
                    model_logins = response.body()!!.getPesan()
                    if (value == "1") {
                        val akses = "true"
                        val model_login: ModelLogin = model_logins.get(0)
                        val usernamee: String = model_login.getUsername()
                        val status: String = model_login.getStatus()
                        val id: String = model_login.getId()

                        Toast.makeText(applicationContext, usernamee+ " " +status, Toast.LENGTH_SHORT).show();
                        session_akun?.setIdAkun(usernamee)
                        session_akun?.setIdpassword(status)
                        session_akun?.setId(id)
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
//                        session_akun.setIdAkses(aksesLogin)
//                        session_akun.setIdpassword(akses)
//                        session_akun.setNama(nama)
//                        session_akun.setEmail(nik)
//                        session_akun.setImg(nomor)
//                        if (aksesLogin == "admin") {
//                            val home = Intent(applicationContext, Home::class.java)
//                            startActivity(home)
//                            finish()
//                            Toast.makeText(this@Login, "Selamat Admin", Toast.LENGTH_SHORT).show()
//                        } else {
//                            val home = Intent(applicationContext, Home_User::class.java)
//                            startActivity(home)
//                            finish()
//                            Toast.makeText(this@Login, "Selamat Datang", Toast.LENGTH_SHORT).show()
//                        }
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Akun Yang Anda Masukkan Salah",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(applicationContext, "Respons Tidak Ada", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ValueLogin?>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(applicationContext, "Jaringan Error", Toast.LENGTH_SHORT).show()
            }
        })
    }

}