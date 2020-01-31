package com.parkme.core.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import io.reactivex.Completable

/*
 * @created - 27/01/2020
 * @project - ParkMeMobile
 * @author  - Michael Mustapha
 */

@Dao
interface BaseDAO<T> {

    /**
     * Handle generic inserts for all DAOs
     */
    @Insert(onConflict = REPLACE)
    fun insert(model: T): Completable
}