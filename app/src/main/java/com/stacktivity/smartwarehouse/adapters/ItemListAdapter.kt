package com.stacktivity.smartwarehouse.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stacktivity.smartwarehouse.R
import com.stacktivity.smartwarehouse.data.entities.Item

class ItemListAdapter(
    private val provider: ContentProvider)
    : RecyclerView.Adapter<ItemViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)

        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return provider.getItemsCount()
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(provider.getItem(position))
        holder.itemView.setOnClickListener {
            provider.onItemClick(position)
        }
    }


    interface ContentProvider {
        fun getItemsCount(): Int
        fun getItem(pos: Int): Item
        fun onItemClick(pos: Int)
    }
}