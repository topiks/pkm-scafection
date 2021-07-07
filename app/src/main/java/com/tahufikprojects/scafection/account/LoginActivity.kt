package com.tahufikprojects.scafection.account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import com.tahufikprojects.scafection.R
import com.tahufikprojects.scafection.monitor.MonitorActivity
import com.tahufikprojects.scafection.utils.Preferences
import kotlinx.android.synthetic.main.activity_daftar.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var inputID: String
    lateinit var inputPass: String

    lateinit var mDatabase : DatabaseReference
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mDatabase = FirebaseDatabase.getInstance().getReference("User")

        preferences = Preferences(this)

        if(preferences.getValues("masuk").equals("1"))
        {
            finishAffinity()
            var goLogin = Intent(this@LoginActivity, MonitorActivity::class.java)
            startActivity(goLogin)
        }

        btn_daftar_dari_login.setOnClickListener {
            finishAffinity()
            preferences.setValues("daftar", "0")
            var intent = Intent(this@LoginActivity , DaftarActivity::class.java)
            startActivity(intent)
        }

        btn_masuk.setOnClickListener {
            inputID = editTextTextPersonName2.text.toString()
            inputPass = editTextTextPersonName4.text.toString()

            if(inputID.equals(""))
            {
                editTextTextPersonName2.error = "Silahkan Tulis ID Anda"
                editTextTextPersonName2.requestFocus()
            }

            else if(inputPass.equals(""))
            {
                editTextTextPersonName4.error = "Silahkan Tulis Password Anda"
                editTextTextPersonName4.requestFocus()
            }

            else
            {
                pushLogin(inputID, inputPass);
            }

        }


    }

    private fun pushLogin(inputID: String, inputPass: String) {
        mDatabase.child(inputID).addValueEventListener(object: ValueEventListener
        {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@LoginActivity, "database error", Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                var user = snapshot.getValue(User::class.java)
                if(user == null)
                {
                    Toast.makeText(this@LoginActivity, "Nomor ID Tidak Ditemukan", Toast.LENGTH_LONG).show()
                }

                else
                {
                    if(user.pass.equals(inputPass))
                    {
                        preferences.setValues("id", user.id.toString())
                        preferences.setValues("nama", user.nama.toString())
                        preferences.setValues("pt", user.pt.toString())
                        preferences.setValues("pass", user.pass.toString())

                        var intent = Intent(this@LoginActivity, MonitorActivity::class.java)
                        startActivity(intent)
                        preferences.setValues("masuk", "1")
                        finishAffinity()
                    }

                    else
                    {
                        Toast.makeText(this@LoginActivity, "ID/Password Anda Salah", Toast.LENGTH_LONG).show()
                    }

                }
            }
        })
    }
}