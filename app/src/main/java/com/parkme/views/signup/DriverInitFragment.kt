package com.parkme.views.signup

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.parkme.R
import com.parkme.core.utils.ViewUtils
import com.parkme.services.driver.Driver
import kotlinx.android.synthetic.main.fragment_add_driver.*

/*
 * @created - 27/01/2020
 * @project - ParkMeMobile
 * @author  - Michael Mustapha
 */

class DriverInitFragment : Fragment() {

    private var listener: DriverFragmentListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_driver, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewUtils.hideKeyboard(activity!!) // Hide keyboard
        next_btn.setOnClickListener { performValidation() }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState != null) {
            with(savedInstanceState) {
                driver_name.setText(getString(DRIVER_NAME))
                driver_phone.setText(getString(DRIVER_PHONE))
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(DRIVER_NAME, driver_name.text.toString())
        outState.putString(DRIVER_PHONE, driver_phone.text.toString())
    }

    /**
     * Perform validation on all entries
     */
    private fun performValidation() {
        val driverName = driver_name.text.toString()
        val driverPhone = driver_phone.text.toString()

        var focusView: View? = null
        var cancel = false

        when {
            driverPhone.isBlank() -> {
                driver_phone.error = getString(R.string.empty_field_error)
                focusView = driver_phone
                cancel = true
            }
            driverPhone.substring(0, 1) == "0" -> {
                driver_phone.error = getString(R.string.zero_phone_error)
                focusView = driver_phone
                cancel = true
            }
            driverPhone.length != 10 -> {
                driver_phone.error = getString(R.string.invalid_phone)
                focusView = driver_phone
                cancel = true
            }
        }

        if (driverName.isBlank()) {
            driver_name.error = getString(R.string.empty_field_error)
            focusView = driver_name
            cancel = true
        }

        if (cancel) focusView!!.requestFocus()
        else {
            ViewUtils.hideKeyboard(activity!!) // Hide keyboard
            val driver = Driver("234$driverPhone", driverName)
            listener!!.onDriverCreate(driver)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is DriverFragmentListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement DriverFragmentListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface DriverFragmentListener {
        fun onDriverCreate(driver: Driver) // Pass driver object to activity
    }

    companion object {
        const val DRIVER_NAME = "DRIVER_NAME"
        const val DRIVER_PHONE = "DRIVER_PHONE"

        fun newInstance() = DriverInitFragment()
    }
}
