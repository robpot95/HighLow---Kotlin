import android.content.Context
import android.content.SharedPreferences

class HighScorePrefs(context: Context) {
    private val sharedPrefs: SharedPreferences =  context.getSharedPreferences("high_score", Context.MODE_PRIVATE)

    init {
        // sharedPrefs.edit().clear().apply()
    }

    fun getScores(): List<String> {
        val scores = sharedPrefs.getStringSet("high_score", emptySet()) ?: emptySet()
        return scores.reversed()
    }

    fun addScore(score: Int) {
        val scores = sharedPrefs.getStringSet("high_score", emptySet()) ?: emptySet()
        val mutableScores = scores.toMutableSet<String>()
        mutableScores.add(score.toString())
        sharedPrefs.edit().putStringSet("high_score", mutableScores).apply()
    }
}