package com.parkme.views.findslot.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.parkme.R
import com.parkme.core.utils.DialogUtils
import com.parkme.core.utils.ViewUtils
import com.parkme.services.terminal.Slot
import com.parkme.services.terminal.TerminalImpl
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_slot_designation.*

/*
 * @created - 12/02/2020
 * @project - ParkMeMobile
 * @author  - Michael Mustapha
 */

class SlotDesignationFragment : Fragment() {

    private lateinit var terminalId: String
    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { terminalId = it.getString(TERMINAL_ID)!! }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_slot_designation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getFreeSlot()
    }

    /**
     * Fetch available slot from server
     */
    private fun getFreeSlot() {
        disposable.add(
            TerminalImpl().getFreeSlot(terminalId)
                .subscribe({
                    // Update the UI
                    progress_bar.visibility = View.GONE
                    message.text = it.message
                    message.visibility = View.VISIBLE
                    handleDriverDecision(it.data)
                }, {
                    error_text.text = ViewUtils.parseAPIError(it)
                    progress_bar.visibility = View.GONE
                    error_text.visibility = View.VISIBLE
                })
        )
    }

    /**
     * Handle driver's decision when allocated slot [Accept | Decline]
     */
    private fun handleDriverDecision(slot: Slot?) {

        if (slot == null) {
            // Display default button if not slot is available
            default_btn.visibility = View.VISIBLE
            default_btn.setOnClickListener {
                activity!!.finish()
            }
        } else {
            decision_button.visibility = View.VISIBLE
            accept_btn.setOnClickListener {
                disposable.add(TerminalImpl().handleDriverDecision(slot)
                    .subscribe({
                        // TODO: Display physical navigation
                        Toast.makeText(context!!, it.message, Toast.LENGTH_SHORT).show()
                        activity!!.finish()
                    }, {
                        // Display the error message on a dialog
                        val error = ViewUtils.parseAPIError(it)
                        DialogUtils.showMessageDialog(activity!!, error!!) { activity!!.finish() }
                    }
                    ))
            }
            decline_btn.setOnClickListener { activity!!.finish() }
        }
    }

    companion object {
        private const val TERMINAL_ID = "TERMINAL_ID"

        @JvmStatic
        fun newInstance(terminalId: String) =
            SlotDesignationFragment().apply {
                arguments = Bundle().apply { putString(TERMINAL_ID, terminalId) }
            }
    }
}