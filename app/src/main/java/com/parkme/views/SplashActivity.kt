package com.parkme.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.parkme.R
import com.parkme.core.config.SessionManager
import com.parkme.services.driver.DriverImpl
import com.parkme.views.signup.SignUpActivity
import io.reactivex.disposables.CompositeDisposable

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
            {
                if (SessionManager(this).hasLoggedIn()) startActivity(Intent(this, HomeActivity::class.java))
                else startActivity(Intent(this, SignUpActivity::class.java))
            }, 1000
        )
    }
}
