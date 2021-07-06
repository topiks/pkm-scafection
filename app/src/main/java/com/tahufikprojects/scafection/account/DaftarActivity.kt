package com.tahufikprojects.scafection.account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tahufikprojects.scafection.R
import kotlinx.android.synthetic.main.activity_daftar.*
import java.util.prefs.Preferences

class DaftarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar)

        btn_login_from_daftar.setOnClickListener {
            var intent = Intent(this@DaftarActivity , LoginActivity::class.java)
            startActivity(intent)
        }
    }
}