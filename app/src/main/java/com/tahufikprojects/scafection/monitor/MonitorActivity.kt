package com.tahufikprojects.scafection.monitor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tahufikprojects.scafection.R
import com.tahufikprojects.scafection.controlling.ControllingActivity
import com.tahufikprojects.scafection.settings.SettingsActivity
import kotlinx.android.synthetic.main.activity_monitor.*

class MonitorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monitor)

        btn_control_monitor.setOnClickListener {
            var intent = Intent(this@MonitorActivity , ControllingActivity::class.java)
            startActivity(intent)
        }

        btn_setting_monitor.setOnClickListener {
            var intent = Intent(this@MonitorActivity , SettingsActivity::class.java)
            startActivity(intent)
        }

    }
}