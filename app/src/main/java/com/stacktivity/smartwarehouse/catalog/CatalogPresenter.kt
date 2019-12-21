package com.stacktivity.smartwarehouse.catalog

import com.stacktivity.smartwarehouse.adapters.SimplifyItemListAdapter
import com.stacktivity.smartwarehouse.contracts.CatalogContract

class CatalogPresenter(val repository: CatalogContract.Repository)
    : CatalogContract.Presenter
{
    private lateinit var view: CatalogContract.View

    private val itemListAdapter: SimplifyItemListAdapter = SimplifyItemListAdapter(this)
    override fun getListAdapter(): SimplifyItemListAdapter {
        return itemListAdapter
    }

    override fun attachView(view: CatalogContract.View) {
        this.view = view
    }

    override fun onAddTitle() {
        view.showAddDialog()
    }

    override fun addTitle(title: String) {
        repository.addTitle(title)
        itemListAdapter.notifyItemInserted(repository.getTitleCount() - 1)
    }

    // Uses adapter
    override fun getTitle(pos: Int): String {
        return repository.getTitle(pos)
    }

    override fun getTitleCount(): Int {
        return repository.getTitleCount()
    }
}