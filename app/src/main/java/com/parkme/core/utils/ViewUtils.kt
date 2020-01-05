package com.parkme.core.utils

import android.app.Activity
import android.view.WindowManager

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
}