package com.fixsolservices.digibank.database.mainlogin

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long

    @Query("SELECT * FROM main_user WHERE id = :id")
    suspend fun getUser(id: Int): User

    @Query("SELECT * FROM main_user ")
    suspend fun getUser(): User

}