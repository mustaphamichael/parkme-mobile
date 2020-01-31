package com.parkme.services.driver

import androidx.room.Dao
import androidx.room.Query
import com.parkme.core.db.BaseDAO
import io.reactivex.Flowable

/*
 * @created - 22/01/2020
 * @project - ParkMeMobile
 * @author  - Michael Mustapha
 */

@Dao
interface DriverDao: BaseDAO<Driver> {

    /**
     * Get Driver's information
     * Since we have just a user, limit the result to just the first user
     */
    @Query("SELECT * FROM driver LIMIT 1")
    fun getDriver(): Flowable<Driver>
}