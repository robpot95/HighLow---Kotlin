package se.appkey.highlow.activities

import android.content.Intent
import android.os.Bundle
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
        playButton.setOnClickListener {
            startActivity(Intent(this, GameActivity::class.java))
        }
    }
}