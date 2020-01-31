package com.parkme.core.config

/*
 * @created - 30/01/2020
 * @project - ParkMeMobile
 * @author  - Michael Mustapha
 */

data class ServiceErrorResponse (
    val message: String,
    val errors: ArrayList<String>
)