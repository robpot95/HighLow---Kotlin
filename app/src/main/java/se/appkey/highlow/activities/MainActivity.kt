package se.appkey.highlow.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import se.appkey.highlow.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        val playButton = findViewById<Button>(R.id.btnPlay)
        val howToPlayButton = findViewById<Button>(R.id.btnHowToPlay)
        playButton.setOnClickListener(clickListener)
        howToPlayButton.setOnClickListener(clickListener)
    }

    private val clickListener: View.OnClickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.btnPlay -> {
                startActivity(Intent(this, GameActivity::class.java))
            }
            R.id.btnHowToPlay -> {
                startActivity(Intent(this, HowToPlayActivity::class.java))
            }
        }
    }
}