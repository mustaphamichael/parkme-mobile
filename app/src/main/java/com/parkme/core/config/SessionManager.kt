package com.parkme.core.config

import android.content.Context
import com.google.gson.Gson
import com.parkme.services.driver.Driver

/*
 * @created - 31/01/2020
 * @project - ParkMeMobile
 * @author  - Michael Mustapha
 */

class SessionManager(private val context: Context) {

    private val pref = context.getSharedPreferences("com.parkme", Context.MODE_PRIVATE)

    /**
     * Save driver's session
     */
    fun saveSession(driver: Driver) {
        pref.edit().putString(DRIVER_PREF, Gson().toJson(driver)).apply()
    }

    private fun getSession(): Driver {
        return Gson().fromJson(pref.getString(DRIVER_PREF, "{}"), Driver::class.java)
    }

    /**
     * Check if the driver has signed in before
     */
    fun hasLoggedIn(): Boolean {
        return getSession().phone.isNotBlank()
    }

    /**
     * Clear user's session
     */
    fun clearSession() {
        // This might not be needed as there is no log in of any sort
    }

    companion object {
        const val DRIVER_PREF = "DRIVER_PREF"
    }


}