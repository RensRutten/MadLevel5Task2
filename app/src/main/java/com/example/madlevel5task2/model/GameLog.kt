package com.example.madlevel5task2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "GameTable")
class GameLog(
    @ColumnInfo(name = "Title") var title: String,
    @ColumnInfo(name = "Platform") var platform: String,
    @ColumnInfo(name = "ReleaseDate") var releaseDate: Date,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID") var id: Long? = null
)