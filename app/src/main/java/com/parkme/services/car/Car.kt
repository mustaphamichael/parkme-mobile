package com.parkme.services.car

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
 * @created - 27/01/2020
 * @project - ParkMeMobile
 * @author  - Michael Mustapha
 */

@Entity(tableName = "car")
data class Car(
    @PrimaryKey
    var numberPlate: String = "",
    var manufacturer: String = "",
    var model: String = ""
)