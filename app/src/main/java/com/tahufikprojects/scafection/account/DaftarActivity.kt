package com.tahufikprojects.scafection.account

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.tahufikprojects.scafection.R
import com.tahufikprojects.scafection.utils.Preferences
import kotlinx.android.synthetic.main.activity_daftar.*


class DaftarActivity : AppCompatActivity() {

    lateinit var inputID:String
    lateinit var inputNama:String
    lateinit var inputPT:String
    lateinit var inputPass:String

    lateinit var mDatabaseReference: DatabaseReference
    lateinit var mFirebaseDatabase: FirebaseDatabase
    lateinit var mDatabase: DatabaseReference

    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar)

        mFirebaseDatabase = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mDatabaseReference = mFirebaseDatabase.getReference("User")

        preferences = Preferences(this)

        if(preferences.getValues("daftar").equals("1"))
        {
            finishAffinity()
            var goLogin = Intent(this@DaftarActivity, LoginActivity::class.java)
            startActivity(goLogin)
        }

        btn_login_from_daftar.setOnClickListener {
            finishAffinity()
            preferences.setValues("daftar", "1")
            var intent = Intent(this@DaftarActivity , LoginActivity::class.java)
            startActivity(intent)
        }

        buttonDaftar.setOnClickListener {

            inputID = editTextTextPersonName.text.toString()
            inputNama = editTextTextPersonName8.text.toString()
            inputPT = editTextTextPersonName9.text.toString()
            inputPass = editTextTextPersonName11.text.toString()

            if(inputID.equals(""))
            {
                editTextTextPersonName.error = "Silhakan isi no KTP/ID Anda"
                editTextTextPersonName.requestFocus()
            }
            else if(inputNama.equals(""))
            {
                editTextTextPersonName8.error = "Silhakan isi Nama Anda"
                editTextTextPersonName8.requestFocus()
            }
            else if(inputPT.equals(""))
            {
                editTextTextPersonName9.error = "Silhakan isi Nama Perusahaan Anda"
                editTextTextPersonName9.requestFocus()
            }
            else if(inputPass.equals(""))
            {
                editTextTextPersonName11.error = "Silhakan isi Password Anda"
                editTextTextPersonName11.requestFocus()
            }
            else
            {
                saveDataBaru(inputID, inputNama, inputPT, inputPass);
                preferences.setValues("daftar", "1")
            }
        }
    }

    private fun saveDataBaru(inputID: String, inputNama: String, inputPT: String, inputPass: String)
    {
        var user = User()
        user.id = inputID;
        user.nama = inputNama;
        user.pt = inputPT;
        user.pass = inputPass;

        if(inputID != null)
            cekDataID(inputID, user);

    }

    private fun cekDataID(inputID: String, data: User)
    {
        mDatabaseReference.child(inputID).addValueEventListener(object : ValueEventListener
        {
            override fun onCancelled(databaseError: DatabaseError)
            {
                Toast.makeText(this@DaftarActivity, "database error", Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                var user = snapshot.getValue(User::class.java)
                if(user == null)
                {
                    mDatabaseReference.child(inputID).setValue(data)

                    var intent = Intent(this@DaftarActivity, LoginActivity::class.java)
                    startActivity(intent)

                    finishAffinity()
                }

                else
                {
                    Toast.makeText(this@DaftarActivity, "Akun sudah dibuat", Toast.LENGTH_LONG).show()
                }
            }
        }
        )
    }
}