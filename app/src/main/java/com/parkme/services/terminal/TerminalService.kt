package com.parkme.services.terminal

import com.parkme.core.config.ServiceGenerator.Companion.BASE_PATH
import com.parkme.core.config.ServiceResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/*
 * @created - 11/02/2020
 * @project - ParkMeMobile
 * @author  - Michael Mustapha
 */

interface TerminalService {

    @GET("$BASE_PATH/terminals")
    fun getTerminals(): Single<ServiceResponse<ArrayList<Terminal>>>

    @GET("$BASE_PATH/terminals/{id}/freeslot")
    fun getFreeSlot(@Path("id") id: String): Single<ServiceResponse<Any>>
}