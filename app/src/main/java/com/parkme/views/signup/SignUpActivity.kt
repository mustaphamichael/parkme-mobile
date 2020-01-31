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

    private var mSavedInstanceState: Bundle? = null
    private lateinit var driverFragment: DriverInitFragment
    private lateinit var carFragment: CarInitFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        mSavedInstanceState = savedInstanceState
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
        if (mSavedInstanceState != null) {
            carFragment = supportFragmentManager.findFragmentByTag(CAR_TAG) as CarInitFragment
            return
        }
        val transaction = supportFragmentManager.beginTransaction()
        carFragment = CarInitFragment.newInstance(driver)

        // Navigate to initial car creation screen
        println("Recreated!!!")
        transaction.replace(R.id.fragment_container, carFragment, CAR_TAG)
            .addToBackStack(DRIVER_TAG)
            .commit()
    }

    companion object {
        const val DRIVER_TAG = "DRIVER_TAG"
        const val CAR_TAG = "CAR_TAG"
    }
}
