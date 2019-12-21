package com.stacktivity.smartwarehouse.catalog

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.stacktivity.smartwarehouse.R
import com.stacktivity.smartwarehouse.adapters.SimplifyItemListAdapter
import com.stacktivity.smartwarehouse.contracts.CatalogContract
import com.stacktivity.smartwarehouse.data.CatalogRepository
import com.stacktivity.smartwarehouse.ui.CatalogEditView
import com.stacktivity.smartwarehouse.utils.ActivityUtils

class CatalogActivity: AppCompatActivity() {
    private lateinit var presenter: CatalogContract.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)

        createEditCatalogScreen()

        val fab: FloatingActionButton = findViewById(R.id.btn_add_item)
        fab.setOnClickListener {
            presenter.onAddTitle()
        }

    }

    private fun createEditCatalogScreen() {
        // Create view
        var view = supportFragmentManager.findFragmentById(R.id.fragment_container) as CatalogEditView?
        if (view == null) {
            view = CatalogEditView()
            ActivityUtils.replaceFragmentInActivity(supportFragmentManager, view, R.id.fragment_container)
        }

        // Create repository
        val repository = CatalogRepository(getSharedPreferences(KEY_PREFERENCES, Context.MODE_PRIVATE))

        // Create presenter
        presenter = CatalogPresenter(repository)
        presenter.attachView(view)

        // Configure view
        @Suppress("UNCHECKED_CAST")
        view.setListAdapter(presenter.getListAdapter() as RecyclerView.Adapter<RecyclerView.ViewHolder>)
        view.setPresenter(presenter)
    }

    override fun onBackPressed() {
        finish()
    }

    companion object {
        private val KEY_PREFERENCES = "appPreferences"
    }

}