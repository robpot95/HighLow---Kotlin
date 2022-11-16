package se.appkey.highlow.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import se.appkey.highlow.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        val playButton = findViewById<Button>(R.id.btnPlay)
        playButton.setOnClickListener {
            // Go to next screen
        }
    }
}