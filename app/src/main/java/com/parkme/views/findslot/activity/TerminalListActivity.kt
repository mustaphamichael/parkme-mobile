package com.parkme.views.findslot.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.parkme.R
import com.parkme.core.utils.ViewUtils
import com.parkme.services.terminal.Terminal
import com.parkme.services.terminal.TerminalImpl
import com.parkme.views.findslot.adapter.TerminalListAdapter
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_terminal_list.*

/*
 * @created - 04/01/2020
 * @project - ParkMeMobile
 * @author  - Michael Mustapha
 */

class TerminalListActivity : AppCompatActivity() {
    private var terminals: ArrayList<Terminal> = ArrayList()
    private lateinit var listAdapter: TerminalListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terminal_list)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.find_slot)

        listAdapter = TerminalListAdapter(this, terminals)

        // Set the RecyclerView's layout and adapter
        terminal_view.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(this@TerminalListActivity)
            setHasFixedSize(true)
        }
        fetchDestinations()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed() // Handle Backward Navigation
        return super.onOptionsItemSelected(item)
    }

    /** Fetch the list of available terminals from API or local DB */
    private fun fetchDestinations() {
        CompositeDisposable().add(
            TerminalImpl().getTerminals()
                .subscribe({
                    progress_bar.visibility = View.GONE
                    updateView(it)
                }, { ViewUtils.displayAPIError(it, error_text, progress_bar) })
        )
    }

    /**
     * Update the RecyclerView
     *
     * @param list - An ArrayList of available terminals from webservice
     */
    private fun updateView(list: ArrayList<Terminal>) {
        if (list.isNotEmpty()) {
            instruction.visibility = View.VISIBLE
            // Populate the adapter's data set
            list.forEach { dest -> if (!terminals.contains(dest)) terminals.add(dest) }

            // Update the adapter for data changes
            listAdapter.notifyDataSetChanged()
        } else {
            instruction.visibility = View.GONE
            error_text.text = getString(R.string.empty_terminal_message)
            error_text.visibility = View.VISIBLE
        }
    }
}