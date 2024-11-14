package dayanegs.fiap.dayane_santana_rm96067.viewmodel

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import dayanegs.fiap.dayane_santana_rm96067.R
import dayanegs.fiap.dayane_santana_rm96067.model.ItemModel

class ItemsAdapter :
    RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {

    private var items = listOf<ItemModel>()

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val textView: TextView = view.findViewById(R.id.textViewItem)!!
        private val textView2: TextView = view.findViewById(R.id.textViewItem2)


        fun bind(item: ItemModel) {
            textView.text = item.name
            textView2.text = item.desc

            textView.setOnClickListener {
                Toast.makeText(itemView.context, item.desc, Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newItems: List<ItemModel>) {
        items = newItems
        notifyDataSetChanged()
    }
}