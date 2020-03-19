package com.parkme.services.terminal

import com.google.gson.annotations.SerializedName

/*
 * @created - 19/03/2020
 * @project - ParkMeMobile
 * @author  - Michael Mustapha
 */

data class Slot(
    @SerializedName("_id")
    val id: String,
    val status: Int,
    val tag: String,
    val hub: String
)