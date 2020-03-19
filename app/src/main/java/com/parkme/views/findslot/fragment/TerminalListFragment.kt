package com.parkme.views.findslot.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.parkme.R
import com.parkme.core.utils.ViewUtils
import com.parkme.services.terminal.Terminal
import com.parkme.services.terminal.TerminalImpl
import com.parkme.views.findslot.adapter.TerminalListAdapter
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_terminal_list.*

/*
 * @created - 12/02/2020
 * @project - ParkMeMobile
 * @author  - Michael Mustapha
 */

class TerminalListFragment : Fragment() {
    private var listener: TerminalListListener? = null
    private var terminals: ArrayList<Terminal> = ArrayList()
    private lateinit var listAdapter: TerminalListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_terminal_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listAdapter = TerminalListAdapter(context!!, listener!!, terminals)

        // Set the RecyclerView's layout and adapter
        terminal_view.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(context!!)
            setHasFixedSize(true)
        }
        fetchDestinations()
    }

    /** Fetch the list of available terminals from API or local DB */
    private fun fetchDestinations() {
        CompositeDisposable().add(
            TerminalImpl().getTerminals()
                .subscribe({
                    progress_bar.visibility = View.GONE
                    updateView(it)
                }, {
                    error_text.text = ViewUtils.parseAPIError(it)
                    progress_bar.visibility = View.GONE
                    error_text.visibility = View.VISIBLE
                })
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is TerminalListListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement DriverFragmentListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface TerminalListListener {
        fun onSelect(terminalId: String)
    }

    companion object {

        @JvmStatic
        fun newInstance() = TerminalListFragment()
    }
}
