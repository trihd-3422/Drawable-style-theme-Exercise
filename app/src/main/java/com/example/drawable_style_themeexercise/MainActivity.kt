package com.example.drawable_style_themeexercise

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.drawable_style_themeexercise.databinding.ActivityMainBinding
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity() {
    private val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    private var mScore1 by Delegates.notNull<Int>()
    private var mScore2 by Delegates.notNull<Int>()
    private val STATE_SCORE_1 = "Team 1 Score"
    private val STATE_SCORE_2 = "Team 2 Score"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if(savedInstanceState != null){
            mScore1 = savedInstanceState.getInt(STATE_SCORE_1)
            mScore2 = savedInstanceState.getInt(STATE_SCORE_2)
            binding.score1.text = mScore1.toString()
            binding.score2.text = mScore2.toString()
        } else {
            mScore1 = binding.score1.text.toString().toInt()
            mScore2 = binding.score2.text.toString().toInt()
        }

        binding.increaseTeam1.setOnClickListener {
            mScore1 += 1
            binding.score1.text = mScore1.toString()
        }
        binding.decreaseTeam1.setOnClickListener {
            if(mScore1 > 0) {
                mScore1 -= 1
            }
            binding.score1.text = mScore1.toString()
        }
        binding.increaseTeam2.setOnClickListener {
            mScore2 += 1
            binding.score2.text = mScore2.toString()
        }
        binding.decreaseTeam2.setOnClickListener {
            if(mScore2 > 0) {
                mScore2 -= 1
            }
            binding.score2.text = mScore2.toString()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        //Change the label of the menu based on the state of the app
        val nightMode = AppCompatDelegate.getDefaultNightMode()
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu?.findItem(R.id.night_mode)?.setTitle(R.string.day_mode)
        } else {
            menu?.findItem(R.id.night_mode)?.setTitle(R.string.night_mode)
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Check if the correct item was clicked
        if(item.itemId == R.id.night_mode){
            //Get the night mode state of the app
            val nightMode = AppCompatDelegate.getDefaultNightMode()
            //Set the theme mode for the restarted activity
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }

            //Recreate the activity for the theme change to take effect
            recreate()

            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(STATE_SCORE_1, mScore1)
        outState.putInt(STATE_SCORE_2, mScore2)
        super.onSaveInstanceState(outState)
    }
}