package com.stacktivity.smartwarehouse.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.stacktivity.smartwarehouse.R
import com.stacktivity.smartwarehouse.contracts.BaseListViewInterface

abstract class BaseListView: Fragment(), BaseListViewInterface {
    private lateinit var itemsView: RecyclerView
    private lateinit var itemListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>

    fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, layoutId: Int
    ): View {
        val view: View = inflater.inflate(layoutId, container, false)

        createItemsView(view)

        return view
    }


    override fun setListAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        itemListAdapter = adapter
        try {
            itemsView.adapter = itemListAdapter
        } catch (e: UninitializedPropertyAccessException) {}
    }

    override fun showMessage(msg: String) {
        Snackbar.make(itemsView, msg,
            Snackbar.LENGTH_LONG).show()
    }

    override fun showMessage(msgId: Int) {
        Snackbar.make(itemsView, msgId,
            Snackbar.LENGTH_LONG).show()
    }

    override fun showDebugMessage(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    private fun createItemsView(view: View) {
        itemsView = view.findViewById(R.id.items_container)
        itemsView.layoutManager = LinearLayoutManager(context)
        itemsView.adapter = itemListAdapter
    }
}