package com.parkme.core.utils

import android.app.Activity
import android.view.View
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.TextView
import com.google.gson.Gson
import com.parkme.core.config.ServiceErrorResponse
import retrofit2.HttpException

/*
 * @created - 05/01/2020
 * @project - ParkMeMobile
 * @author  - Michael Mustapha
 */

/**
 * Utility object for views across the project
 */
object ViewUtils {

    /**
     * Hides phone's soft keyboard
     *
     * @param activity
     */
    fun hideKeyboard(activity: Activity) {
        activity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    /**
     * Display errors returned from webservice
     *
     * @param throwable - Error returned from HTTP client
     * @param errorText - TextView to render the error message on
     * @param progressBar
     */
    fun displayAPIError(throwable: Throwable, errorText: TextView?, progressBar: ProgressBar?) {
        val error = (throwable as HttpException).response().errorBody()!!.string()
        val errorObj = Gson().fromJson(error, ServiceErrorResponse::class.java)

        var message = errorObj.message
        if (!errorObj.errors.isNullOrEmpty()) {
            errorObj.errors.forEach { message += it + "\n" }
        }
        errorText?.text = message
        progressBar?.visibility = View.GONE // Hide progress bar
        errorText?.visibility = View.VISIBLE // Show error text
    }
}