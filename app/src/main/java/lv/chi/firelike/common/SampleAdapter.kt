package lv.chi.firelike.common

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_sample.view.*
import lv.chi.firelike.R

class SampleAdapter(
    val items: List<String>,
    private val onIconClick: (View) -> Unit
) : RecyclerView.Adapter<SampleViewHolder>() {

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, position: Int) = SampleViewHolder(
        LayoutInflater.from(parent.context).inflate(
            if (getItemViewType(position) == 0) R.layout.item_sample else R.layout.item_sample_2,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.bind(items[position], onIconClick)
    }

    override fun getItemViewType(position: Int): Int {
        return position % 2
    }
}

class SampleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: String, onIconClick: (View) -> Unit) = with(itemView) {
        item_text.text = item
        item_image.setOnClickListener(onIconClick)
    }
}
