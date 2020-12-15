package com.example.madlevel5task2.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madlevel5task2.model.GameLog
import com.example.madlevel5task2.repository.GameRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class GameViewModel(application: Application) : AndroidViewModel(application) {
    private val mainScope = CoroutineScope(Dispatchers.Main)
    private val gameRepository = GameRepository(application.applicationContext)

    val gameLogs: LiveData<List<GameLog>> = gameRepository.allGames()
    val error = MutableLiveData<String>()
    val success = MutableLiveData<Boolean>()

    fun insertGame(
        title: String,
        platform: String,
        releaseDate: Date
    ) {
        val gameLog = GameLog(
            title = title,
            platform = platform,
            releaseDate = releaseDate
        )

        if (isValid(gameLog)) {
            mainScope.launch {
                withContext(Dispatchers.IO) {
                    gameRepository.insertGame(gameLog)
                }
                success.value = true
            }
        }
    }

    fun deleteGame() {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                gameRepository.deleteGameBacklog()
            }
            success.value = true
        }
    }

    private fun isValid(gameLog: GameLog): Boolean {
        return when {
            gameLog.title.isBlank() -> {
                error.value = "Title must not be empty!"
                false
            }
            gameLog.platform.isBlank() -> {
                error.value = "Platform must not be empty!"
                false
            }
            gameLog.releaseDate.toString().isBlank() -> {
                error.value = "Release date must not be empty!"
                false
            }
            else -> true
        }
    }
}