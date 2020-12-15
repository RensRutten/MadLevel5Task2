package com.example.madlevel5task2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.madlevel5task2.model.GameLog
import com.example.madlevel5task2.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_games.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GamesFragment : Fragment() {
    private var gameLogs: ArrayList<GameLog> = arrayListOf()

    private lateinit var gameAdapter: GameAdapter

    private val viewModel: GameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_games, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRv()
        observeAddGames()
        observeDeleteGame()
    }

    private fun initRv() {
        gameAdapter = GameAdapter(gameLogs)
        rvGames.adapter = gameAdapter
        rvGames.layoutManager = LinearLayoutManager(activity)
    }

    private fun observeAddGames() {
        viewModel.gameLogs.observe(viewLifecycleOwner, { logs ->
            this@GamesFragment.gameLogs.clear()
            this@GamesFragment.gameLogs.addAll(logs)
            gameAdapter.notifyDataSetChanged()
        })
    }

    private fun observeDeleteGame() {
        viewModel.success.observe(viewLifecycleOwner, { logs ->
            Snackbar.make(View(context), "Success", Snackbar.LENGTH_LONG)
        })
    }
}