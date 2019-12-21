package com.stacktivity.smartwarehouse.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.stacktivity.smartwarehouse.R
import com.stacktivity.smartwarehouse.contracts.CatalogContract
import com.stacktivity.smartwarehouse.utils.BaseListView

class CatalogEditView: BaseListView(), CatalogContract.View {

    private lateinit var presenter: CatalogContract.Presenter
    private lateinit var addTitleDialogView: View

    @SuppressLint("InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = super.onCreateView(inflater, container, R.layout.recyclerview_fragment)

        addTitleDialogView = inflater.inflate(R.layout.add_title_dialog, null, false)

        return view
    }
    override fun setPresenter(presenter: CatalogContract.Presenter) {
        this.presenter = presenter
    }

    override fun showAddDialog() {
        val createNewFolderDialog =
            AlertDialog.Builder(context)
        val title: EditText =
            addTitleDialogView.findViewById(R.id.title)
        if (addTitleDialogView.parent != null) {
            (addTitleDialogView.parent as ViewGroup).removeView(
                addTitleDialogView
            )
        }
        createNewFolderDialog.setView(addTitleDialogView)
        createNewFolderDialog.setPositiveButton("OK"
        ) { dialog, which ->
            if (title.text != null) {
                presenter.addTitle(title.text.toString())
            }
            dialog.cancel()
        }
        createNewFolderDialog.setNegativeButton(
            "cancel"
        ) { dialog, which ->
            dialog.cancel()
            title.setText("")
        }
        createNewFolderDialog.show()
    }

}