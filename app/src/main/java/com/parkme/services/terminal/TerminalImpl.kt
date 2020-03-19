package com.parkme.services.terminal

import com.parkme.core.config.ServiceGenerator
import com.parkme.core.config.ServiceResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/*
 * @created - 11/02/2020
 * @project - ParkMeMobile
 * @author  - Michael Mustapha
 */

class TerminalImpl {
    private val service = ServiceGenerator().createService(TerminalService::class.java)

    fun getTerminals(): Single<ArrayList<Terminal>> {
        return service.getTerminals()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.data }
    }

    fun getFreeSlot(terminalId: String): Single<ServiceResponse<Slot>> {
        return service.getFreeSlot(terminalId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it }
    }

    fun handleDriverDecision(body: Slot): Single<ServiceResponse<Any>> {
        return service.handleDriverDecision(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it }
    }
}