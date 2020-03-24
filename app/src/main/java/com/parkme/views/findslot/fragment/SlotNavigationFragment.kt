package com.parkme.views.findslot.fragment

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.parkme.BuildConfig
import com.parkme.R
import com.parkme.services.terminal.Slot
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_slot_navigation.*

/*
 * @created - 12/02/2020
 * @project - ParkMeMobile
 * @author  - Michael Mustapha
 */

class SlotNavigationFragment : Fragment() {

    private lateinit var slot: Slot

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { slot = Gson().fromJson(it.getString(SLOT_DATA), Slot::class.java) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_slot_navigation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Display image
        // TODO: Use dynamic gate number
        Picasso.with(context!!).load("$IMAGE_URL/gate/1/hub/${slot.hub}")
            .fit().into(slot_navigation_view)
        // Cancel -> Return to Home Activity
        activity!!.finish()
    }

    companion object {
        private const val SLOT_DATA = "SLOT_DATA"
        private const val IMAGE_URL = "${BuildConfig.SERVER_URL}/api/parkme/navigation/images"

        @JvmStatic
        fun newInstance(slot: Slot) =
            SlotNavigationFragment().apply {
                arguments = Bundle().apply { putString(SLOT_DATA, Gson().toJson(slot)) }
            }
    }
}