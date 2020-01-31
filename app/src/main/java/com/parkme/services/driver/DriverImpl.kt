package com.parkme.services.driver

import android.content.Context
import com.parkme.core.db.ParkMeRoom
import com.parkme.core.config.ServiceGenerator
import com.parkme.core.config.ServiceResponse
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/*
 * @created - 27/01/2020
 * @project - ParkMeMobile
 * @author  - Michael Mustapha
 */

class DriverImpl(context: Context) {

    private val service = ServiceGenerator().createService(DriverService::class.java)
    private val mDbInstance = ParkMeRoom.getInstance(context)
    private val driverDB = mDbInstance.driver()
    private val disposable = CompositeDisposable()

    /**
     * Perform sign up
     */
    fun signUp(driver: Driver): Single<Driver> {
        return service.signUp(driver)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.data }
            .doOnSuccess { storeDriverDetail(it) }
    }

    /**
     * Save driver's detail in local db
     */
    private fun storeDriverDetail(driver: Driver) {
        driverDB.insert(driver)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io()).subscribe()
    }

    /**
     * Check if driver's detail is saved
     */
    fun getDetail(phone: String): Observable<Driver> {
        // Fetch driver's detail from the server
        service.getDetail(phone)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { storeDriverDetail(it.data) } // Save the data into the local db
            .subscribe()

        // Return the saved data to the UI
        return driverDB.getDriver()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toObservable()

    }
}