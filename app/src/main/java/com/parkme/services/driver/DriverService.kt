package com.parkme.services.driver

import com.parkme.core.config.ServiceGenerator.Companion.BASE_PATH
import com.parkme.core.config.ServiceResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

/*
 * @created - 22/01/2020
 * @project - ParkMeMobile
 * @author  - Michael Mustapha
 */

/**
 * The API endpoints are called here
 */
interface DriverService {

    @POST("$BASE_PATH/auth/login")
    fun login(@Body driver: Driver): Single<ServiceResponse<Driver>>

    @POST("$BASE_PATH/drivers")
    fun signUp(@Body driver: Driver): Single<ServiceResponse<Driver>>

    @PATCH("$BASE_PATH/drivers/{phone}")
    fun editDetail(@Path("phone") phone: String, @Body driver: Driver): Single<ServiceResponse<Driver>>
}