import android.content.res.Resources
import android.content.res.TypedArray
import android.util.Log
import se.appkey.highlow.R

class Deck {
    private val cards = mutableListOf<Card>()
    private val discardedCards = mutableListOf<Card>()

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
        for (i in 0 until cardsArray.length()) {
            val resourceId: Int = cardsArray.getResourceId(i, -1)
            if (resourceId != -1) {
                cards.add(Card((i % 13) + 2, resourceId))
            }
        }

        cardsArray.recycle()
        cards.shuffle()
    }

    fun drawCard() : Card {
        Log.d("MyPrint", cards.size.toString())
        if (cards.isEmpty()) {
            cards.addAll(discardedCards)
            cards.shuffle()
            discardedCards.clear()
        }

        val card = cards.first()
        discardedCards.add(card)
        cards.removeFirst()
        return card
    }
}