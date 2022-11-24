import android.content.res.Resources
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.widget.ImageView
import se.appkey.highlow.R
import kotlin.random.Random


class Deck {
    private val cards = mutableListOf<Card>()
    private val random : Random = Random

    fun init(resource: Resources, packageName: String) {
        // Dynamic get assets, but resource intensive. Not recommended way
        /*val cardPrefix = arrayOf<String>("clubs_of", "diamonds_of", "hearts_of", "spades_of")
        for (prefix in cardPrefix) {
            for (index in 2..14) {
                val resourceId = resource.getIdentifier("${prefix}_$index", "drawable", packageName)
                cards.add(Card(index, resourceId))
            }
        }*/

        val cardsArray : TypedArray = resource.obtainTypedArray(R.array.cards)
        for (i in 0..cardsArray.length()) {
            val resourceId: Int = cardsArray.getResourceId(i, -1)
            if (resourceId != -1) {
                cards.add(Card(i, resourceId))
            }
        }
    }

    fun drawCard(image : ImageView) : Card {
        val card = cards[random.nextInt(cards.size)]
        image.setImageResource(card.getResourceId())
        return card
    }
}