package com.parkme.core.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.parkme.services.car.Car
import com.parkme.services.driver.Driver
import com.parkme.services.driver.DriverDao

/*
 * @created - 22/01/2020
 * @project - ParkMeMobile
 * @author  - Michael Mustapha
 */

@Database(
    entities = [
        Car::class,
        Driver::class
    ], version = 1, exportSchema = false
)
abstract class ParkMeRoom : RoomDatabase() {

    abstract fun driver(): DriverDao

    companion object {
        private var mInstance: ParkMeRoom? = null

        @Synchronized
        fun getInstance(context: Context): ParkMeRoom {
            if (mInstance == null) {
                mInstance = Room.databaseBuilder(context, ParkMeRoom::class.java, "parkme")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return mInstance!!
        }
    }
}