package com.tahufikprojects.scafection.settings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tahufikprojects.scafection.R
import com.tahufikprojects.scafection.account.LoginActivity
import com.tahufikprojects.scafection.monitor.MonitorActivity
import com.tahufikprojects.scafection.utils.Preferences
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        preferences = Preferences(this)

        btn_log_out.setOnClickListener {
            preferences.setValues("id", "")
            preferences.setValues("nama", "")
            preferences.setValues("pt", "")
            preferences.setValues("pass", "")
            preferences.setValues("masuk", "0")

            var i = Intent(this@SettingsActivity, LoginActivity::class.java)
            startActivity(i)

            finishAffinity()
        }

        button2.setOnClickListener {
            var i = Intent(this@SettingsActivity, MonitorActivity::class.java)
            startActivity(i)

        }

    }
}