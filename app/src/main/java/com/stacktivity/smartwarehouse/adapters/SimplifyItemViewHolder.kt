package com.stacktivity.smartwarehouse.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.stacktivity.smartwarehouse.R

class SimplifyItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(name: String) {
        itemView.findViewById<TextView>(R.id.title).text = name
    }
}