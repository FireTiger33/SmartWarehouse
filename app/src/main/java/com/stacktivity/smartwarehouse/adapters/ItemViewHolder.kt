package com.stacktivity.smartwarehouse.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.stacktivity.smartwarehouse.R
import com.stacktivity.smartwarehouse.data.entities.Item

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: Item) {
        itemView.findViewById<TextView>(R.id.item_name).text = item.itemName
        itemView.findViewById<TextView>(R.id.item_count).text = item.itemCount.toString()
        itemView.findViewById<TextView>(R.id.item_qty_name).text = item.qtyName
    }
}