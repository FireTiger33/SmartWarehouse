package com.stacktivity.smartwarehouse.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stacktivity.smartwarehouse.R
import com.stacktivity.smartwarehouse.contracts.InventoryContract
import com.stacktivity.smartwarehouse.data.entities.Item
import com.stacktivity.smartwarehouse.utils.BaseItemTouchHelper
import com.stacktivity.smartwarehouse.utils.BaseListView
import com.stacktivity.smartwarehouse.utils.ItemUtils


class InventoryView: BaseListView(), InventoryContract.View {
//    private lateinit var presenter: InventoryContract.Presenter
    private lateinit var itemUtils: ItemUtils


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, R.layout.recyclerview_fragment)

        itemUtils = ItemUtils(inflater, checkNotNull(context))

        return view
    }

    override fun showEditItemDialog(item: Item, callback: ItemUtils.ItemCallback) {
        itemUtils.showEditItemDialog(item, callback)
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