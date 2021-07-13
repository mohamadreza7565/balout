package com.rymo.ciel.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rymo.balout.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM tbl_user")
    fun get(): User?

    @Insert
    fun insert(user: User)

    @Delete
    fun delete(user: User)
}