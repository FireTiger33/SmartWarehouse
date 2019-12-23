package com.stacktivity.smartwarehouse.contracts

import com.stacktivity.smartwarehouse.data.entities.Item
import com.stacktivity.smartwarehouse.adapters.ItemListAdapter
import com.stacktivity.smartwarehouse.utils.ItemUtils

interface InventoryContract {
    interface View: BaseListViewInterface {
//        fun setPresenter(presenter: Presenter)
        fun showEditItemDialog(item: Item, callback: ItemUtils.ItemCallback)
    }

    interface Presenter {
        fun attachView(view: View)
        fun getListAdapter(): ItemListAdapter
        fun processVoiceInputResult(results: ArrayList<String>)
    }

    interface Repository {
        fun addItem(item: Item)
        fun removeItem(pos: Int)
        fun restoreItem(item: Item, pos: Int)
        fun getItemsCount(): Int
        fun getItem(pos: Int): Item
        fun getCatalog(): ArrayList<String>
        fun clearData()
    }
}