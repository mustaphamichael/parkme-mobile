package com.parkme.views.findslot.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.parkme.R
import com.parkme.services.terminal.Terminal
import com.parkme.views.findslot.FindSlotActivity
import com.parkme.views.findslot.fragment.TerminalListFragment
import com.squareup.picasso.Picasso

/*
 * @created - 04/01/2020
 * @project - ParkMeMobile
 * @author  - Michael Mustapha
 */

class TerminalListAdapter(
    private val context: Context,
    private val listener: TerminalListFragment.TerminalListListener,
    private val terminals: ArrayList<Terminal>
) :
    RecyclerView.Adapter<TerminalListAdapter.TerminalViewHolder>() {

    override fun onBindViewHolder(holder: TerminalViewHolder, position: Int) {
        val terminal = terminals[position]
        holder.setViewData(terminal)

        holder.itemView.setOnClickListener {
            // Navigate to Slot Designation screen
            listener.onSelect(terminal.id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TerminalViewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.item_terminals, parent, false)
        return TerminalViewHolder(context, layout)
    }

    override fun getItemCount(): Int {
        return terminals.size
    }

    /** ViewHolder to be passed to Adapter for data binding and rendering */
    class TerminalViewHolder(private val context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.terminal_name)
        private val icon: ImageView = itemView.findViewById(R.id.terminal_icon)

        fun setViewData(model: Terminal) {
            name.text = model.name

            // Load image using Picasso by passing in the url
            Picasso.with(context).load(model.logo_url).fit().centerCrop()
                .placeholder(R.drawable.park_me_logo)
                .into(icon)
        }
    }
}