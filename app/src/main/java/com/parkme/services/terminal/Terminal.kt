package com.parkme.services.terminal

import com.google.gson.annotations.SerializedName

/*
 * @created - 04/01/2020
 * @project - ParkMeMobile
 * @author  - Michael Mustapha
 */

data class Terminal(
    @SerializedName("_id")
    val id: String,
    val name: String,
    val logo_url: String?
)