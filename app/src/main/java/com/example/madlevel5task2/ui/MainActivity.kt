package com.example.madlevel5task2.ui

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.madlevel5task2.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add_game.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private val viewModel: GameViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        navController = findNavController(R.id.nav_host_fragment)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        val deleteMenuItem = menu.findItem(R.id.btnRemoveAll)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.FirstFragment -> {
                    fabAddGames.setOnClickListener {
                        supportActionBar?.setDisplayHomeAsUpEnabled(true)
                        supportActionBar?.setDisplayShowHomeEnabled(true)
                        supportActionBar?.setTitle(R.string.addGameTitle)
                        navController.navigate(R.id.action_FirstFragment_to_SecondFragment)
                    }

                    fabAddGames.setImageResource(android.R.drawable.ic_menu_edit)
                    deleteMenuItem.isVisible = true
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    supportActionBar?.setDisplayShowHomeEnabled(false)
                    supportActionBar?.setTitle(R.string.app_name)
                }
                R.id.SecondFragment -> {
                    deleteMenuItem.isVisible = false
                    val cal = Calendar.getInstance()

                    fabAddGames.setOnClickListener {
                        cal.set(Calendar.YEAR, tilAddYear.text.toString().toInt())
                        cal.set(Calendar.MONTH, tilAddMonth.text.toString().toInt())
                        cal.set(Calendar.DAY_OF_MONTH, tilAddDay.text.toString().toInt())

                        viewModel.insertGame(
                            tilTitle.text.toString(),
                            tilAddPlatform.text.toString(),
                            Date.from(cal.toInstant()),
                        )

                        navController.navigate(R.id.action_SecondFragment_to_FirstFragment)
                    }
                    fabAddGames.setImageResource(android.R.drawable.ic_menu_save)
                }
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btnRemoveAll -> {
                viewModel.deleteGame()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

}