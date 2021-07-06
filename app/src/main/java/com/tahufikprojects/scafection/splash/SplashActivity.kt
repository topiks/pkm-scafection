package com.tahufikprojects.scafection.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.tahufikprojects.scafection.R
import com.tahufikprojects.scafection.account.DaftarActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // tahan activity selama 5 detik, kemudian lempar ke activity berikutnya
        var handler = Handler()
        handler.postDelayed(
            {
                var intent = Intent(this@SplashActivity, DaftarActivity::class.java)
                startActivity(intent)
                finish()
            }, 3000
        )
    }
}