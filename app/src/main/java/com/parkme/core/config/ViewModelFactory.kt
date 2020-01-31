package com.parkme.core.config

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/*
 * @created - 28/01/2020
 * @project - ParkMeMobile
 * @author  - Michael Mustapha
 */

class ViewModelFactory<R>(private val creator: () -> R) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return creator as T
    }
}