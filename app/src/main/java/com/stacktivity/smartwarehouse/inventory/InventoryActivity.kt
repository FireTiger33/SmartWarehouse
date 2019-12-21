package com.stacktivity.smartwarehouse.inventory

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.speech.RecognizerIntent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.stacktivity.smartwarehouse.R
import com.stacktivity.smartwarehouse.contracts.InventoryContract
import com.stacktivity.smartwarehouse.data.InventoryRepository
import com.stacktivity.smartwarehouse.data.RoomItemDataBase
import com.stacktivity.smartwarehouse.ui.InventoryView
import com.stacktivity.smartwarehouse.utils.ActivityUtils
import com.stacktivity.smartwarehouse.utils.BaseItemTouchHelper
import kotlinx.android.synthetic.main.activity_inventory.*


class InventoryActivity: AppCompatActivity(), BaseItemTouchHelper.ItemTouchHelperListener {
    private lateinit var presenter: InventoryContract.Presenter
    private lateinit var repository: InventoryRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)

        createInventoryScreen()

        val fab: FloatingActionButton = findViewById(R.id.btn_add_item)
        fab.setOnClickListener {
            // createVoiceInput()
            presenter.processVoiceInputResult(arrayListOf("Огурцы 5 штук", "помидоры 6 штук"))
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            checkNotNull(data)
            val commandList = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)

            presenter.processVoiceInputResult(commandList)
        }
    }

    private fun createVoiceInput() {
        // намерение для вызова формы обработки речи
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)

//        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "What can you tell me?")

        startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE)

    }

    private fun createInventoryScreen() {
        // Create view
        var view = supportFragmentManager.findFragmentById(R.id.fragment_container) as InventoryView?
        if (view == null) {
            view = InventoryView()
            ActivityUtils.replaceFragmentInActivity(supportFragmentManager, view, R.id.fragment_container)
        }

        // Create repository
        repository = InventoryRepository(
            getSharedPreferences(KEY_PREFERENCES, Context.MODE_PRIVATE),
            RoomItemDataBase.getInstance(applicationContext)?.itemsDao()
        )

        // Create presenter
        presenter = InventoryPresenter(/*view,*/ repository)
        presenter.attachView(view)


        // Configure view
        @Suppress("UNCHECKED_CAST")
        view.setListAdapter(presenter.getListAdapter() as RecyclerView.Adapter<RecyclerView.ViewHolder>)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int, position: Int) {
        checkNotNull(viewHolder)

        // backup of removed item for undo purpose
        val deletedItem = repository.getItem(viewHolder.adapterPosition)
        val deletedIndex = viewHolder.adapterPosition

        // remove the item from recycler view
        repository.removeItem(deletedIndex)
        presenter.getListAdapter().notifyItemRemoved(deletedIndex)

        Snackbar
            .make(fragment_container, "${deletedItem.itemName} удалены из отчёта!", Snackbar.LENGTH_LONG)
            .setActionTextColor(Color.YELLOW)
            // undo is selected, restore the deleted item
            .setAction(R.string.undo) {
                repository.restoreItem(deletedItem, deletedIndex)
                presenter.getListAdapter().notifyItemInserted(deletedIndex)
            }
            .show()
    }
    override fun onBackPressed() {
        finish()
    }

    companion object {
        private val KEY_PREFERENCES = "appPreferences"
        private val VOICE_RECOGNITION_REQUEST_CODE = 761
    }
}