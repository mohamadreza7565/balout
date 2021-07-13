package com.rymo.ciel.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rymo.balout.model.Articles
import com.rymo.balout.model.User
import com.rymo.ciel.dao.NewsDao
import com.rymo.ciel.dao.UserDao

@Database(entities = [User::class,Articles::class], exportSchema = false, version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        fun getAppDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "db_salmoni")
                .allowMainThreadQueries()
                .build()
        }
    }


    abstract fun getUserDao(): UserDao
    abstract fun getNewsDao(): NewsDao
}