package com.rymo.balout.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_user")
class User {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    lateinit var name: String

}
