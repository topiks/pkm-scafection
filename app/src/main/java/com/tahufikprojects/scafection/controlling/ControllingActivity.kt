package com.tahufikprojects.scafection.controlling

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.tahufikprojects.scafection.R
import com.tahufikprojects.scafection.monitor.MonitorActivity
import com.tahufikprojects.scafection.settings.SettingsActivity
import com.tahufikprojects.scafection.utils.Preferences
import kotlinx.android.synthetic.main.activity_controlling.*
import kotlinx.android.synthetic.main.activity_monitor.*
import java.text.SimpleDateFormat
import java.util.*

class ControllingActivity : AppCompatActivity() {

    lateinit var mDatabaseReference: DatabaseReference
    lateinit var mFirebaseDatabase: FirebaseDatabase
    lateinit var mDatabase: DatabaseReference

    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_controlling)

        var c = Calendar.getInstance()
        var df = SimpleDateFormat("E, d MMMM yyyy"  )
        var formattedDate: String = df.format(c.time)

        var dateTimeDisplay = findViewById<TextView>(R.id.textView33)
        dateTimeDisplay.setText(formattedDate).toString()

        var manualOn = findViewById<RadioButton>(R.id.radioButton3)
        var manualOff = findViewById<RadioButton>(R.id.radioButton4)
        var autoValve = findViewById<RadioButton>(R.id.radioButton2)

        var manualOnMotor = findViewById<RadioButton>(R.id.radioButton6)
        var manualOffMotor = findViewById<RadioButton>(R.id.radioButton8)
        var autoMotor = findViewById<RadioButton>(R.id.radioButton5)

        mFirebaseDatabase = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mDatabaseReference = mFirebaseDatabase.getReference("Control")

        preferences = Preferences(this)

        val myId = preferences.getValues("id").toString()

        val namaSaya = preferences.getValues("nama");
        val ptSaya = preferences.getValues("pt");

        val halloText = findViewById<TextView>(R.id.textView30)
        val perusahaanText = findViewById<TextView>(R.id.textView32)

        halloText.setText("Hallo " + namaSaya).toString()
        perusahaanText.setText(ptSaya).toString()

        valve.setOnCheckedChangeListener { radioGroup, i ->
            if(radioButton2.isChecked())
            {
                preferences.setValues("valve", "1")
                mDatabaseReference.child(myId).child("Valve").child("auto").setValue("1")
                mDatabaseReference.child(myId).child("Valve").child("on").setValue("0")
                mDatabaseReference.child(myId).child("Valve").child("off").setValue("0")
            }

            else if(radioButton3.isChecked())
            {
                preferences.setValues("valve", "2")
                mDatabaseReference.child(myId).child("Valve").child("auto").setValue("0")
                mDatabaseReference.child(myId).child("Valve").child("on").setValue("1")
                mDatabaseReference.child(myId).child("Valve").child("off").setValue("0")
            }

            else if(radioButton4.isChecked())
            {
                preferences.setValues("valve", "3")
                mDatabaseReference.child(myId).child("Valve").child("auto").setValue("0")
                mDatabaseReference.child(myId).child("Valve").child("on").setValue("0")
                mDatabaseReference.child(myId).child("Valve").child("off").setValue("1")
            }
        }

        if(preferences.getValues("valve").equals("1"))
            autoValve.setChecked(true)
        else if(preferences.getValues("valve").equals("2"))
            manualOn.setChecked(true)
        else if(preferences.getValues("valve").equals("3"))
            manualOff.setChecked(true)

        motor.setOnCheckedChangeListener { radioGroup, i ->
            if(radioButton5.isChecked())
            {
                preferences.setValues("motor", "1")
                mDatabaseReference.child(myId).child("Motor").child("auto").setValue("1")
                mDatabaseReference.child(myId).child("Motor").child("on").setValue("0")
                mDatabaseReference.child(myId).child("Motor").child("off").setValue("0")
            }

            else if(radioButton6.isChecked())
            {
                preferences.setValues("motor", "2")
                mDatabaseReference.child(myId).child("Motor").child("auto").setValue("0")
                mDatabaseReference.child(myId).child("Motor").child("on").setValue("1")
                mDatabaseReference.child(myId).child("Motor").child("off").setValue("0")
            }

            else if(radioButton8.isChecked())
            {
                preferences.setValues("motor", "3")
                mDatabaseReference.child(myId).child("Motor").child("auto").setValue("0")
                mDatabaseReference.child(myId).child("Motor").child("on").setValue("0")
                mDatabaseReference.child(myId).child("Motor").child("off").setValue("1")
            }
        }

        if(preferences.getValues("motor").equals("1"))
            autoMotor.setChecked(true)
        else if(preferences.getValues("motor").equals("2"))
            manualOnMotor.setChecked(true)
        else if(preferences.getValues("motor").equals("3"))
            manualOffMotor.setChecked(true)

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