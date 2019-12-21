package com.stacktivity.smartwarehouse.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.stacktivity.smartwarehouse.R
import com.stacktivity.smartwarehouse.contracts.InventoryContract
import com.stacktivity.smartwarehouse.utils.BaseItemTouchHelper
import com.stacktivity.smartwarehouse.utils.BaseListView

class InventoryView: BaseListView(), InventoryContract.View {
//    private lateinit var presenter: InventoryContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, R.layout.recyclerview_fragment)
    }

    /*override fun setPresenter(presenter: InventoryContract.Presenter) {
        this.presenter = presenter
    }*/

    override fun onSwiped(
        viewHolder: RecyclerView.ViewHolder?,
        direction: Int,
        position: Int
    ) {
        (activity as BaseItemTouchHelper.ItemTouchHelperListener).onSwiped(viewHolder, direction, position)
    }
}