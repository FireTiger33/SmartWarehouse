package com.stacktivity.smartwarehouse.contracts

import androidx.recyclerview.widget.RecyclerView

interface BaseListViewInterface {
    fun setListAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>)

    fun showMessage(msg: String)
    fun showMessage(msgId: Int)
    fun showDebugMessage(msg: String)
}