package com.stacktivity.smartwarehouse.contracts

import com.stacktivity.smartwarehouse.adapters.SimplifyItemListAdapter

interface CatalogContract {
    interface View: BaseListViewInterface {
        fun setPresenter(presenter: Presenter)

        fun showAddDialog()
    }

    interface Presenter {
        fun attachView(view: View)
        fun getListAdapter(): SimplifyItemListAdapter
        fun onAddTitle()
        fun addTitle(title: String)
        fun getTitle(pos: Int): String
        fun getTitleCount():Int
    }

    interface Repository {
        fun addTitle(name: String)
        fun removeTitle(pos: Int)
        fun restoreTitle(title: String, pos: Int)
        fun deleteTitle(pos: Int)
        fun getTitle(pos: Int): String
        fun getTitleCount():Int
    }
}