package com.stacktivity.smartwarehouse.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stacktivity.smartwarehouse.R
import com.stacktivity.smartwarehouse.contracts.CatalogContract

class SimplifyItemListAdapter(
    private val presenter: CatalogContract.Presenter)
    : RecyclerView.Adapter<SimplifyItemViewHolder>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimplifyItemViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.simple_item, parent, false)

        return SimplifyItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return presenter.getTitleCount()
    }

    override fun onBindViewHolder(holder: SimplifyItemViewHolder, position: Int) {
        holder.bind(presenter.getTitle(position))
    }

}