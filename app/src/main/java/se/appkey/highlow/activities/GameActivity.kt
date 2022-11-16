package se.appkey.highlow.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import se.appkey.highlow.R
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    // Initialize
    private lateinit var btnHigh : Button
    private lateinit var btnLow : Button
    private lateinit var tvPoints : TextView
    private lateinit var ivPlayerCard : ImageView
    private lateinit var ivDealerCard : ImageView

    private val random : Random = Random
    private var points : Int = 0
    private val cards = intArrayOf(
        R.drawable.hearts2,
        R.drawable.hearts3,
        R.drawable.hearts4,
        R.drawable.hearts5,
        R.drawable.hearts6,
        R.drawable.hearts7,
        R.drawable.hearts8,
        R.drawable.hearts9,
        R.drawable.hearts10,
        R.drawable.hearts12,
        R.drawable.hearts13,
        R.drawable.hearts14,
        R.drawable.hearts15
    )

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
        val playerCard = randomCard(ivPlayerCard)
        val dealerCard = randomCard(ivDealerCard)
        when (view.id) {
            R.id.btnHigh -> {
                if (playerCard > dealerCard) {
                    points++
                } else {
                    points--
                }
            }
            R.id.btnLow -> {
                if (playerCard < dealerCard) {
                    points++
                } else {
                    points--
                }
            }
        }

        tvPoints.text = points.toString()
    }

    private fun randomCard(image: ImageView) : Int {
        val randomNumber = random.nextInt(cards.size)
        image.setImageResource(cards[randomNumber])
        return randomNumber
    }
}