package com.rymo.ciel.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rymo.balout.model.Articles

@Dao
interface NewsDao {

    @Query("SELECT * FROM tbl_news")
    fun getAll(): List<Articles>

    @Query("DELETE FROM tbl_news")
    fun nukeTable()

    @Insert
    fun insert(articles: Articles)

    @Delete
    fun delete(articles: Articles)
}