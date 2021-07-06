package com.tahufikprojects.scafection.account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tahufikprojects.scafection.R
import com.tahufikprojects.scafection.monitor.MonitorActivity
import kotlinx.android.synthetic.main.activity_daftar.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_masuk.setOnClickListener {
            var intent = Intent(this@LoginActivity , MonitorActivity::class.java)
            startActivity(intent)
        }


    }
}