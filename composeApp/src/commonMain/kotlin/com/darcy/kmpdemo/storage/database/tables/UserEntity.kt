package com.darcy.kmpdemo.storage.database.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String = "",
    val nickName: String = "",
    val age: Int = 0,
    val sex: Int = 0,
    val avatar: String = "",
    val createdTime: Long = 0,
    val updatedTime: Long = 0,
    val deletedTime: Long = 0,
){}
