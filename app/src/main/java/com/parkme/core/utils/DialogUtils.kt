package com.parkme.core.utils

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.parkme.R
import kotlinx.android.synthetic.main.parkme_custom_message_dialog.view.*

/*
 * @created - 19/03/2020
 * @project - ParkMeMobile
 * @author  - Michael Mustapha
 */

object DialogUtils {

    /**
     * Show Dialog with message only and carry out an optional task e.g Navigate to another screen
     */
    fun showMessageDialog(activity: Activity, message: String, doTask: () -> Unit? = {}) {
        val builder = AlertDialog.Builder(activity)
        val view = activity.layoutInflater.inflate(R.layout.parkme_custom_message_dialog, null)
        view.dialog_message.text = message
        builder.setView(view)
            .setPositiveButton("OK") { p0, p1 ->
                p0.cancel()
                doTask()
            }
        val dialog = builder.create()
        dialog.setCancelable(false)
        dialog.show()
    }
}