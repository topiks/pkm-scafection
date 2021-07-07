package com.tahufikprojects.scafection.controlling

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tahufikprojects.scafection.R
import com.tahufikprojects.scafection.monitor.MonitorActivity
import com.tahufikprojects.scafection.settings.SettingsActivity
import kotlinx.android.synthetic.main.activity_controlling.*
import kotlinx.android.synthetic.main.activity_monitor.*

class ControllingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_controlling)

        btn_dahboard_control.setOnClickListener {
            var intent = Intent(this@ControllingActivity , MonitorActivity::class.java)
            startActivity(intent)
        }

        btn_setting_control.setOnClickListener {
            var intent = Intent(this@ControllingActivity , SettingsActivity::class.java)
            startActivity(intent)
        }
    }
}