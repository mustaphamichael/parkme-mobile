package com.parkme.views.findslot.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.parkme.R
import com.parkme.services.destination.Destination
import com.parkme.views.findslot.adapter.DestinationListAdapter
import kotlinx.android.synthetic.main.activity_destination_list.*

/*
 * @created - 04/01/2020
 * @project - ParkMeMobile
 * @author  - Michael Mustapha
 */

class DestinationListActivity : AppCompatActivity() {
    private var destinations: ArrayList<Destination> = ArrayList()
    private lateinit var listAdapter: DestinationListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination_list)

        supportActionBar?.title = getString(R.string.find_slot)

        listAdapter = DestinationListAdapter(this, destinations)

        // Set the RecyclerView's layout and adapter
        destination_view.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(this@DestinationListActivity)
            setHasFixedSize(true)
        }
        fetchDestinations()
    }

    /** Fetch the list of available destinations from API or local DB */
    private fun fetchDestinations() {
        // TODO: Fetch destinations from APIs
        // TEST with Static data

        val list: ArrayList<Destination> = ArrayList() // Destination list received from API
        list.add(
            Destination(
                "we-1",
                "Chicken Republic",
                "https://pbs.twimg.com/profile_images/425609383188652032/lNsL4bhO.jpeg"
            )
        )
        list.add(
            Destination(
                "wf-1",
                "Film House",
                "http://www.google.com"
            )
        )
        list.add(
            Destination(
                "wg-1",
                "Airbnb",
                "https://press.airbnb.com/wp-content/uploads/sites/4/2017/01/airbnb_vertical_lockup_web.png?fit=2096,1048"
            )
        )
        list.add(
            Destination(
                "wh-1",
                "Google Market",
                "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png"
            )
        )
        updateView(list)
    }

    /**
     * Update the RecyclerView
     *
     * @param list - An ArrayList of available destinations from webservice
     */
    private fun updateView(list: ArrayList<Destination>) {
        // Populate the adapter's data set
        list.forEach { dest -> if (!destinations.contains(dest)) destinations.add(dest) }

        // Update the adapter for data changes
        listAdapter.notifyDataSetChanged()
    }
}
