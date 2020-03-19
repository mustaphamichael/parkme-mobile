package com.parkme.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.parkme.R
import com.parkme.views.findslot.FindSlotActivity
import kotlinx.android.synthetic.main.activity_home.*

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
            startActivity(Intent(this, FindSlotActivity::class.java))
        }
    }
}
