package com.parkme.services.driver

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.parkme.services.car.Car

/*
 * @created - 04/01/2020
 * @project - ParkMeMobile
 * @author  - Michael Mustapha
 */

@Entity(tableName = "driver")
data class Driver(
    @PrimaryKey
    var phone: String,
    var name: String,
    @Ignore
    var car: Car? = null,
    @Ignore
    var cars: ArrayList<Car> = arrayListOf()
) {
    // Constructor added to handle Room Ignore case ;(
    constructor() : this("", "", null, cars = arrayListOf<Car>())
}