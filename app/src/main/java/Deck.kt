import android.widget.ImageView
import se.appkey.highlow.R
import kotlin.random.Random

class Deck {
    private val cards = mutableListOf(Card(2, R.drawable.hearts2), Card(3, R.drawable.hearts3), Card(4, R.drawable.hearts4), Card(5, R.drawable.hearts5), Card(6, R.drawable.hearts6), Card(7, R.drawable.hearts7), Card(8, R.drawable.hearts8), Card(9, R.drawable.hearts9), Card(10, R.drawable.hearts10), Card(12, R.drawable.hearts12), Card(13, R.drawable.hearts13), Card(14, R.drawable.hearts14), Card(15, R.drawable.hearts15))
    private val random : Random = Random

    fun drawCard(image : ImageView) : Card {
        val card = cards[random.nextInt(cards.size)]
        image.setImageResource(card.getResourceId())
        return card
    }
}