package com.stacktivity.smartwarehouse.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stacktivity.smartwarehouse.R
import com.stacktivity.smartwarehouse.contracts.InventoryContract

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
}