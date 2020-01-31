package com.parkme.views.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.parkme.R
import com.parkme.services.driver.Driver

/*
 * @created - 04/01/2020
 * @project - ParkMeMobile
 * @author  - Michael Mustapha
 */

class SignUpActivity : AppCompatActivity(), DriverInitFragment.DriverFragmentListener {

    private lateinit var driverFragment: DriverInitFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        if (savedInstanceState != null) {
            driverFragment = supportFragmentManager.findFragmentByTag(DRIVER_TAG) as DriverInitFragment
            return
        }
        driverFragment = DriverInitFragment.newInstance()

        if (!driverFragment.isInLayout) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, driverFragment, DRIVER_TAG)
                .commit()
        }
    }

    override fun onDriverCreate(driver: Driver) {
        val transaction = supportFragmentManager.beginTransaction()

        // Navigate to initial car creation screen
        transaction.replace(R.id.fragment_container, CarInitFragment.newInstance(driver), CAR_TAG)
            .addToBackStack(DRIVER_TAG)
            .commit()
    }

    companion object {
        const val DRIVER_TAG = "DRIVER_TAG"
        const val CAR_TAG = "CAR_TAG"
    }
}
