package com.interview.sample.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("User")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo("userId")
    val userId: String,
    @ColumnInfo("username")
    val username: String
)