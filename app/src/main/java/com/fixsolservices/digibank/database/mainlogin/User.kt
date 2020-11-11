package com.fixsolservices.digibank.database.mainlogin

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "main_user")
data class User(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
//    val address: Address? = null,
    val email: String = "",
    val name: String = "",
    val username: String = ""
)

/*

data class Address(
    val city: String,
    val geo: Geo,
    val street: String,
    val suite: String,
    val zipcode: String
)

data class Geo(
    val lat: String,
    val lng: String
)*/
