package com.parkme.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.parkme.R
import com.parkme.views.signup.SignUpActivity

/*
 * @created - 04/01/2020
 * @project - ParkMeMobile
 * @author  - Michael Mustapha
 */

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Add a little delay
        Handler().postDelayed(
            { startActivity(Intent(this, SignUpActivity::class.java)) }, 1000
        )
    }
}
