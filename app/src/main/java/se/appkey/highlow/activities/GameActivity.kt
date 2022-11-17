package se.appkey.highlow.activities

import Deck
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import se.appkey.highlow.R

class GameActivity : AppCompatActivity() {
    // Initialize
    private lateinit var btnHigh : Button
    private lateinit var btnLow : Button
    private lateinit var tvPoints : TextView
    private lateinit var ivPlayerCard : ImageView
    private lateinit var ivDealerCard : ImageView

    private var points : Int = 0
    private val deck = Deck()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        init()
    }

    private fun init() {
        btnHigh = findViewById(R.id.btnHigh)
        btnLow = findViewById(R.id.btnLow)
        tvPoints = findViewById(R.id.tvPoints)

        ivPlayerCard = findViewById(R.id.playerBackCard)
        ivDealerCard = findViewById(R.id.dealerBackCard)
        ivPlayerCard.setImageResource(R.drawable.card_back)
        ivDealerCard.setImageResource(R.drawable.card_back)

        btnHigh.setOnClickListener(clickListener)
        btnLow.setOnClickListener(clickListener)
    }

    private val clickListener: View.OnClickListener = View.OnClickListener { view ->
        val playerCard = deck.drawCard(ivPlayerCard)
        val dealerCard = deck.drawCard(ivDealerCard)
        when (view.id) {
            R.id.btnHigh -> {
                if (playerCard.getValue() > dealerCard.getValue()) {
                    points++
                } else {
                    points--
                }
            }
            R.id.btnLow -> {
                if (playerCard.getValue() < dealerCard.getValue()) {
                    points++
                } else {
                    points--
                }
            }
        }

        tvPoints.text = points.toString()
    }
}