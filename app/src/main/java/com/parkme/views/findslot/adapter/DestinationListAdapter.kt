package com.parkme.views.findslot.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.parkme.R
import com.parkme.services.destination.Destination
import com.squareup.picasso.Picasso

/*
 * @created - 04/01/2020
 * @project - ParkMeMobile
 * @author  - Michael Mustapha
 */

class DestinationListAdapter(private val context: Context, private val destinations: ArrayList<Destination>) :
    RecyclerView.Adapter<DestinationListAdapter.DestinationViewHolder>() {

    override fun onBindViewHolder(holder: DestinationViewHolder, position: Int) {
        val destination = destinations[position]
        holder.setViewData(destination)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationViewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.item_destinations, parent, false)
        return DestinationViewHolder(context, layout)
    }

    override fun getItemCount(): Int {
        return destinations.size
    }

    /** ViewHolder to be passed to Adapter for data binding and rendering */
    class DestinationViewHolder(private val context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.destination_name)
        private val icon: ImageView = itemView.findViewById(R.id.destination_icon)

        fun setViewData(model: Destination) {
            name.text = model.name

            // Load image using Picasso by passing in the url
            Picasso.with(context).load(model.logo_url).fit().centerCrop()
                .placeholder(R.drawable.park_me_logo)
                .into(icon)
        }
    }
}