package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import se.appkey.highlow.R

class ScoreRecycleAdapter(private val scores: List<String>) :
    RecyclerView.Adapter<ScoreRecycleAdapter.ViewHolder>() {
    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        var itemTextView: TextView = view.findViewById(R.id.tvScoreItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_score, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = scores.elementAt(position)
        holder.itemTextView.text = item
    }

    override fun getItemCount(): Int {
        return scores.size
    }
}