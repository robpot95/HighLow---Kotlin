package se.appkey.highlow.activities

import Deck
import HighScorePrefs
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import se.appkey.highlow.R
import kotlin.collections.ArrayList


class GameActivity : AppCompatActivity() {
    // Initialize
    private lateinit var tvHearts : TextView
    private lateinit var btnHigh : Button
    private lateinit var btnLow : Button
    private lateinit var tvPoints : TextView
    private lateinit var ivPlayerCard : ImageView
    private lateinit var ivDealerCard : ImageView

    enum class GameState {
        NONE, WIN, LOST, DRAW
    }

    private var gameState = GameState.NONE
    private val lives: MutableList<String> = ArrayList()
    private var score : Int = 0

    private val deck = Deck()
    private lateinit var highScorePrefs: HighScorePrefs
    private var toast : Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
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
        tvHearts = findViewById(R.id.tvHearts)
        btnHigh = findViewById(R.id.btnHigh)
        btnLow = findViewById(R.id.btnLow)
        tvPoints = findViewById(R.id.tvPoints)

        ivPlayerCard = findViewById(R.id.playerBackCard)
        ivDealerCard = findViewById(R.id.dealerBackCard)
        ivPlayerCard.setImageResource(R.drawable.card_back)
        ivDealerCard.setImageResource(R.drawable.card_back)

        highScorePrefs = HighScorePrefs(this)
        deck.init(resources, packageName)

        val heartEmoji = String(Character.toChars(0x1F60A))
        for (i in 1..5) {
            lives.add(heartEmoji)
            tvHearts.append(heartEmoji)
        }

        btnHigh.setOnClickListener(clickListener)
        btnLow.setOnClickListener(clickListener)
    }

    private val clickListener: View.OnClickListener = View.OnClickListener { view ->
        val playerCard = deck.drawCard(ivPlayerCard)
        val dealerCard = deck.drawCard(ivDealerCard)
        when (view.id) {
            R.id.btnHigh -> {
                gameState = if (playerCard.getValue() > dealerCard.getValue()) {
                    GameState.WIN
                } else if (playerCard.getValue() < dealerCard.getValue()) {
                    GameState.LOST
                } else {
                    GameState.DRAW
                }
            }
            R.id.btnLow -> {
                gameState = if (playerCard.getValue() < dealerCard.getValue()) {
                    GameState.WIN
                } else if (playerCard.getValue() > dealerCard.getValue()) {
                    GameState.LOST
                } else {
                    GameState.DRAW
                }
            }
        }
        when (gameState) {
            GameState.WIN -> {
                showToast("You win")
                score++
                tvPoints.text = score.toString()
            }
            GameState.LOST -> {
                showToast("You lost")
                lives.removeLast()
                if (lives.isEmpty()) {
                    highScorePrefs.addScore(score)
                    showToast("Congrats! You got $score points")
                    finish()
                    return@OnClickListener
                }
                tvHearts.text = lives.joinToString(separator = "")
            }
            GameState.DRAW -> showToast("It was a draw")
            else -> {}
        }

        btnHigh.isEnabled = false
        btnLow.isEnabled = false
        Handler().postDelayed({
            ivPlayerCard.setImageResource(R.drawable.card_back)
            ivDealerCard.setImageResource(R.drawable.card_back)
            btnHigh.isEnabled = true
            btnLow.isEnabled = true
        }, 100)
    }

    private fun showToast(message: String) {
        toast?.cancel()
        toast = Toast.makeText(
            applicationContext,
            message,
            Toast.LENGTH_SHORT
        )
        toast?.show()
    }
}