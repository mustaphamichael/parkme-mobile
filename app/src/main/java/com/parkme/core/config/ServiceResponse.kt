package com.parkme.core.config

/*
 * @created - 22/01/2020
 * @project - ParkMeMobile
 * @author  - Michael Mustapha
 */

/**
 * Application's API Response model
 */
data class ServiceResponse<T>(
    val message: String,
    val data: T
)