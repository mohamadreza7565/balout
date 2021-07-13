package com.rymo.balout.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_news")
class Articles {
    @PrimaryKey(autoGenerate = true)
    var id:Int=0
    var author: String? = null
    var title: String? = null
    var description: String? = null
    var url: String? = null
    var urlToImage: String? = null
    var publishedAt: String? = null
    var content: String? = null
}