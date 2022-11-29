package se.appkey.highlow.activities

import HighScorePrefs
import adapters.ScoreRecycleAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import se.appkey.highlow.R

class HighScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_high_score)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        init()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun init() {
        val highScorePrefs = HighScorePrefs(this)
        val recyclerView: RecyclerView = findViewById(R.id.rvScore)
        val scoreRecycleAdapter = ScoreRecycleAdapter(highScorePrefs.getScore())
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = scoreRecycleAdapter
        scoreRecycleAdapter.notifyDataSetChanged()
    }


}