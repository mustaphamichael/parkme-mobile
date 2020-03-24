package com.parkme.views.findslot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.parkme.R
import com.parkme.services.terminal.Slot
import com.parkme.views.findslot.fragment.SlotDesignationFragment
import com.parkme.views.findslot.fragment.SlotNavigationFragment
import com.parkme.views.findslot.fragment.TerminalListFragment

/*
 * @created - 12/02/2020
 * @project - ParkMeMobile
 * @author  - Michael Mustapha
 */

class FindSlotActivity : AppCompatActivity(),
    TerminalListFragment.TerminalListListener, SlotDesignationFragment.SlotDesignationListener {
    private lateinit var listFragment: TerminalListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.find_slot)

        if (savedInstanceState != null) {
            listFragment = supportFragmentManager.findFragmentByTag(LIST_TAG) as TerminalListFragment
            return
        }
        listFragment = TerminalListFragment.newInstance()

        if (!listFragment.isInLayout) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, listFragment, LIST_TAG)
                .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed() // Handle Backward Navigation
        return super.onOptionsItemSelected(item)
    }

    override fun onSelect(terminalId: String) {
        // Navigate to slot designation screen
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, SlotDesignationFragment.newInstance(terminalId), SLOT_TAG)
            .addToBackStack(LIST_TAG)
            .commit()
    }

    override fun displayNavigation(slot: Slot) {
        // Display to slot navigation screen
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, SlotNavigationFragment.newInstance(slot), NAVIGATION_TAG)
            .addToBackStack(SLOT_TAG)
            .commit()
    }

    companion object {
        const val LIST_TAG = "LIST_TAG"
        const val SLOT_TAG = "SLOT_TAG"
        const val NAVIGATION_TAG = "NAVIGATION_TAG"
    }
}