package com.example.madlevel5task2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.madlevel5task2.dao.GameDao
import com.example.madlevel5task2.model.GameLog

@Database(entities = [GameLog::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GameRoomDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao

    companion object {
        private val DATABASE_NAME = "GAME_DATABASE"

        @Volatile
        private var databaseInstance: GameRoomDatabase? = null

        fun getDatabase(context: Context): GameRoomDatabase? {
            if (databaseInstance == null) {
                synchronized(GameRoomDatabase::class.java) {
                    if (databaseInstance == null) {
                        databaseInstance = Room.databaseBuilder(
                            context.applicationContext,
                            GameRoomDatabase::class.java, DATABASE_NAME
                        ).build()
                    }
                }
            }
            return databaseInstance
        }
    }
}