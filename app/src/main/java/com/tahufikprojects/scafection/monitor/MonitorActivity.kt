package com.tahufikprojects.scafection.monitor

import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.tahufikprojects.scafection.R
import com.tahufikprojects.scafection.controlling.ControllingActivity
import com.tahufikprojects.scafection.settings.SettingsActivity
import com.tahufikprojects.scafection.utils.Preferences
import kotlinx.android.synthetic.main.activity_monitor.*
import java.text.SimpleDateFormat
import java.util.*

class MonitorActivity : AppCompatActivity() {

    lateinit var preferences: Preferences
    lateinit var mDatabase : DatabaseReference
    lateinit var tankLoad: String
    lateinit var jarak: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monitor)

        preferences = Preferences(this)

        mDatabase = FirebaseDatabase.getInstance().getReference("Sensor")

        var c = Calendar.getInstance()
        var df = SimpleDateFormat("E, d MMMM yyyy"  )
        var formattedDate: String = df.format(c.time)


        val namaSaya = preferences.getValues("nama");
        val ptSaya = preferences.getValues("pt");
        val idSaya = preferences.getValues("id").toString();

        val halloText = findViewById<TextView>(R.id.textView9)
        val perusahaanText = findViewById<TextView>(R.id.textView11)
        var tankLoadText = findViewById<TextView>(R.id.textView14)
        var customProgressBar = findViewById<ProgressBar>(R.id.progressBar)
        var dateTimeDisplay = findViewById<TextView>(R.id.textView12)

        halloText.setText("Hallo " + namaSaya).toString()
        perusahaanText.setText(ptSaya).toString()
        dateTimeDisplay.setText(formattedDate).toString()

        btn_control_monitor.setOnClickListener {
            var intent = Intent(this@MonitorActivity , ControllingActivity::class.java)
            startActivity(intent)
        }

        btn_setting_monitor.setOnClickListener {
            var intent = Intent(this@MonitorActivity , SettingsActivity::class.java)
            startActivity(intent)
        }

        mDatabase.child(idSaya).child("berat").addValueEventListener(object : ValueEventListener
        {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val post = dataSnapshot.getValue()
                tankLoad = post.toString()
                tankLoadText.setText(tankLoad).toString()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                tankLoadText.setText("Database Error").toString()

            }
        })

        mDatabase.child(idSaya).child("jarak").addValueEventListener(object : ValueEventListener
        {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val post = dataSnapshot.getValue()
                jarak = post.toString()
                customProgressBar.setProgress(jarak.toInt())
            }

            override fun onCancelled(databaseError: DatabaseError) {
                tankLoadText.setText("Database Error").toString()

            }
        })

    }
}