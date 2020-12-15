package com.example.madlevel5task2.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.madlevel5task2.model.GameLog

@Dao
interface GameDao {
    @Insert
    suspend fun insertGame(GameDao: GameLog)

    @Query("SELECT * FROM GameTable")
    fun allGames(): LiveData<List<GameLog>>

    @Query("DELETE FROM GameTable")
    suspend fun deleteGames()
}