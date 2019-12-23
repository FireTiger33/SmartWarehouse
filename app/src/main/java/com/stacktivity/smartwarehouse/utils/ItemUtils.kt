package com.stacktivity.smartwarehouse.utils

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.stacktivity.smartwarehouse.R
import com.stacktivity.smartwarehouse.data.entities.Item

class ItemUtils(inflater: LayoutInflater, private val context: Context) {
    private val dialogView: View


    init {
        @SuppressLint("InflateParams")
        dialogView = inflater.inflate(R.layout.dialog_edit_item, null, false)
    }

    fun showEditItemDialog(item: Item, callback: ItemCallback) {

        val editItemDialog =
            AlertDialog.Builder(context)
        val newCount: EditText =
            dialogView.findViewById(R.id.item_count)
        newCount.hint = item.itemCount.toString()

        if (dialogView.parent != null) {
            (dialogView.parent as ViewGroup).removeView(
                dialogView
            )
        }
        editItemDialog.setView(dialogView)
        editItemDialog.setPositiveButton("OK"
        ) { dialog, which ->
            if (newCount.text != null) {
                item.itemCount = newCount.text.toString().toFloat()
                callback.onSuccess(item)
            }
            dialog.cancel()
        }
        editItemDialog.setNegativeButton(
            "cancel"
        ) { dialog, which ->
            dialog.cancel()
            newCount.setText("")
        }
        editItemDialog.show()
    }

    interface ItemCallback {
        fun onSuccess(item: Item)
    }
}