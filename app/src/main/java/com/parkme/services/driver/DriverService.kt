package com.parkme.services.driver

import com.parkme.core.config.ServiceGenerator.Companion.BASE_PATH
import com.parkme.core.config.ServiceResponse
import io.reactivex.Single
import retrofit2.http.*

/*
 * @created - 22/01/2020
 * @project - ParkMeMobile
 * @author  - Michael Mustapha
 */

/**
 * The API endpoints are called here
 */
interface DriverService {

    @GET("$BASE_PATH/drivers/{phone}")
    fun getDetail(@Path("phone") phone: String): Single<ServiceResponse<Driver>>

    @POST("$BASE_PATH/auth/login")
    fun login(@Body driver: Driver): Single<ServiceResponse<Driver>>

    @POST("$BASE_PATH/drivers")
    fun signUp(@Body driver: Driver): Single<ServiceResponse<Driver>>

    @PATCH("$BASE_PATH/drivers/{phone}")
    fun editDetail(@Path("phone") phone: String, @Body driver: Driver): Single<ServiceResponse<Driver>>
}