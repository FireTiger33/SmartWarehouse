package com.stacktivity.smartwarehouse.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stacktivity.smartwarehouse.R
import com.stacktivity.smartwarehouse.contracts.InventoryContract

class ItemListAdapter(
    private val presenter: InventoryContract.Presenter)
    : RecyclerView.Adapter<ItemViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)

        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return presenter.getItemsCount()
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(presenter.getItem(position))
    }
}