package com.parkme.core.utils

import android.app.Activity
import android.view.WindowManager
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
     * Parse errors returned from webservice
     * @param throwable - Error returned from HTTP client
     * @return String?
     */
    fun parseAPIError(throwable: Throwable): String? {
        var message: String

        if (throwable is HttpException) {
            val error = throwable.response().errorBody()!!.string()
            val errorObj = Gson().fromJson(error, ServiceErrorResponse::class.java)

            message = errorObj.message
            if (!errorObj.errors.isNullOrEmpty()) {
                errorObj.errors.forEach { message += it + "\n" }
            }
        } else message = throwable.message.toString()
        return message
    }
}