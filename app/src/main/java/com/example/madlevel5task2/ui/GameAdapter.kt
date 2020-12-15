package com.example.madlevel5task2.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel5task2.model.GameLog
import com.example.madlevel5task2.R
import kotlinx.android.synthetic.main.item_games.view.*
import java.text.DateFormatSymbols
import java.util.*

class GameAdapter(private val gameLogs: List<GameLog>) :
    RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun databind(gameLog: GameLog) {
            val calendar: Calendar = Calendar.getInstance();
            calendar.time = gameLog.releaseDate
            val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
            val monthOfYear = DateFormatSymbols().months[calendar.get(Calendar.MONTH) - 1]
            val year = calendar.get(Calendar.YEAR)


            itemView.tvGameTitle.text = gameLog.title
            itemView.tvPlatform.text = gameLog.platform
            itemView.TvDate.text =
                String.format(
                    "Release: %s %s %s",
                    dayOfWeek,
                    monthOfYear,
                    year
                )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_games, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(gameLogs[position])
    }

    override fun getItemCount(): Int {
        return gameLogs.size
    }
}