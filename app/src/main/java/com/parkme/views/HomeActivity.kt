package com.parkme.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.parkme.R
import com.parkme.views.findslot.FindSlotActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.parkme_dropdown_dialog.view.*

/*
 * @created - 04/01/2020
 * @project - ParkMeMobile
 * @author  - Michael Mustapha
 */

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        optionSelection()
    }

    /**
     * Handle user selection
     */
    private fun optionSelection() {
        // Let the driver select his destination
        find_slot.setOnClickListener {
            // Select a gate before the screen
            val builder = AlertDialog.Builder(this)
            val view = layoutInflater.inflate(R.layout.parkme_dropdown_dialog, null)
            view.dialog_message.text = getString(R.string.select_gate)
            view.dropdown.adapter =
                ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arrayOf("Gate 1", "Gate 2"))
            var gate = "0"
            view.dropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    gate = (position + 1).toString()
                }
            }
            builder.setView(view)
                .setPositiveButton("OK") { p0, _ ->
                    p0.cancel()
                    // Navigate to destination selection screen
                    val intent = Intent(this, FindSlotActivity::class.java)
                    intent.putExtra("entry_point", gate)
                    startActivity(intent)
                }
                .setNegativeButton("Cancel") { _, _ ->
                    // Do Nothing
                }
            val dialog = builder.create()
            dialog.setCancelable(false)
            dialog.show()
        }
    }
}
