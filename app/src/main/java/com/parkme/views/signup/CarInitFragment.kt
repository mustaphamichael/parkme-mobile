package com.parkme.views.signup

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.parkme.R
import com.parkme.core.utils.ViewUtils
import com.parkme.core.config.ServiceErrorResponse
import com.parkme.services.car.Car
import com.parkme.services.driver.Driver
import com.parkme.services.driver.DriverImpl
import com.parkme.views.HomeActivity
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_add_car.*
import kotlinx.android.synthetic.main.fragment_add_driver.*
import retrofit2.HttpException

/*
 * @created - 27/01/2020
 * @project - ParkMeMobile
 * @author  - Michael Mustapha
 */

class CarInitFragment : Fragment() {
    private lateinit var driver: Driver
    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            // Get driver details from argument(s)
            driver = Gson().fromJson(it.getString(DRIVER_OBJ), Driver::class.java)
            println(driver)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_car, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        proceed_btn.setOnClickListener { performValidation() }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState != null) {
            with(savedInstanceState) {
                number_plate.setText(getString(NUMBER_PLATE))
                manufacturer.setText(getString(MANUFACTURER))
                model.setText(getString(MODEL))
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(NUMBER_PLATE, number_plate.text.toString())
        outState.putString(MANUFACTURER, manufacturer.text.toString())
        outState.putString(MODEL, model.text.toString())
    }

    /**
     * Perform validation on all entries
     */
    private fun performValidation() {
        val numberPlate = number_plate.text.toString()
        val carManufacturer = manufacturer.text.toString()
        val carModel = model.text.toString()

        var focusView: View? = null
        var cancel = false

        if (carModel.isBlank()) {
            model.error = getString(R.string.empty_field_error)
            focusView = driver_phone
            cancel = true
        }
        if (carManufacturer.isBlank()) {
            manufacturer.error = getString(R.string.empty_field_error)
            focusView = driver_name
            cancel = true
        }
        if (numberPlate.isBlank()) {
            number_plate.error = getString(R.string.empty_field_error)
            focusView = driver_name
            cancel = true
        }

        if (cancel) focusView!!.requestFocus()
        else {
            ViewUtils.hideKeyboard(activity!!) // Hide keyboard
            progress_bar.visibility = View.VISIBLE // Show progress bar
            driver.car = Car(numberPlate, carManufacturer, carModel)
            callAPI(driver)
        }
    }

    /**
     * Call Webservice
     */
    private fun callAPI(driver: Driver) {
        disposable.add(
            DriverImpl(context!!).signUp(driver)
                .subscribe({
                    startActivity(Intent(activity!!, HomeActivity::class.java))
                },
                    {
                        val error = (it as HttpException).response().errorBody()!!.string()
                        val errorObj = Gson().fromJson(error, ServiceErrorResponse::class.java)
                        displayError(errorObj)
                    })
        )
    }

    /**
     * Return error to user
     */
    private fun displayError(error: ServiceErrorResponse) {
        var message = error.message
        if (!error.errors.isNullOrEmpty()) {
            error.errors.forEach { message += it + "\n" }
        }
        error_text.text = message
        progress_bar.visibility = View.GONE // Hide progress bar
        error_text.visibility = View.VISIBLE // Show error text
    }

    companion object {
        private const val DRIVER_OBJ = "DRIVER_OBJ"
        const val NUMBER_PLATE = "NUMBER_PLATE"
        const val MANUFACTURER = "MANUFACTURER"
        const val MODEL = "MODEL"

        @JvmStatic
        fun newInstance(driver: Driver) =
            CarInitFragment().apply {
                arguments = Bundle().apply {
                    putString(DRIVER_OBJ, Gson().toJson(driver))
                }
            }
    }
}
