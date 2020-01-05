package com.parkme.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.parkme.R
import com.parkme.core.utils.ViewUtils
import kotlinx.android.synthetic.main.activity_sign_up.*

/*
 * @created - 04/01/2020
 * @project - ParkMeMobile
 * @author  - Michael Mustapha
 */

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
//        proceed_btn.setOnClickListener { performValidation() }
        proceed_btn.setOnClickListener { startActivity(Intent(this, HomeActivity::class.java)) }

        // Hide keyboard
        ViewUtils.hideKeyboard(this)
    }

    /**
     * Perform validation on all entries
     */
    private fun performValidation() {
        val numberPlate = number_plate.text.toString()
        val driverName = driver_name.text.toString()
        val driverPhone = driver_phone.text.toString()

        var focusView: View? = null
        var cancel = false

        if (driverPhone.isBlank()) {
            driver_phone.error = getString(R.string.empty_field_error)
            focusView = driver_phone
            cancel = true
        }
        if (driverName.isBlank()) {
            driver_name.error = getString(R.string.empty_field_error)
            focusView = driver_name
            cancel = true
        }
        if (numberPlate.isBlank()) {
            number_plate.error = getString(R.string.empty_field_error)
            focusView = number_plate
            cancel = true
        }

        if (cancel) focusView!!.requestFocus()
        else {
            ViewUtils.hideKeyboard(this) // Hide keyboard

            // TODO: Pass detail to API
            // Navigate to Home Screen after successful data capture
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}
