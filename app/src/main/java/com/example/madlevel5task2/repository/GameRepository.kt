package com.example.madlevel5task2.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.madlevel5task2.dao.GameDao
import com.example.madlevel5task2.model.GameLog
import com.example.madlevel5task2.database.GameRoomDatabase

class GameRepository(context: Context) {
    private val gameDao: GameDao

    init {
        val database = GameRoomDatabase.getDatabase(context)
        gameDao = database!!.gameDao()
    }

    fun allGames(): LiveData<List<GameLog>> {
        return gameDao.allGames()
    }

    suspend fun insertGame(gameLog: GameLog) {
        gameDao.insertGame(gameLog)
    }

    suspend fun deleteGameBacklog() {
        gameDao.deleteGames()
    }
}