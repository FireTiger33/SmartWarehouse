package com.stacktivity.smartwarehouse.inventory

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.stacktivity.smartwarehouse.R
import com.stacktivity.smartwarehouse.contracts.InventoryContract
import com.stacktivity.smartwarehouse.data.InventoryRepository
import com.stacktivity.smartwarehouse.data.RoomItemDataBase
import com.stacktivity.smartwarehouse.ui.InventoryView
import com.stacktivity.smartwarehouse.utils.ActivityUtils


class InventoryActivity: AppCompatActivity() {
    private lateinit var presenter: InventoryContract.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)

        createInventoryScreen()

        val fab: FloatingActionButton = findViewById(R.id.btn_add_item)
        fab.setOnClickListener {
            createVoiceInput()
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
        val repository = InventoryRepository(
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

    override fun onBackPressed() {
        finish()
    }

    companion object {
        private val KEY_PREFERENCES = "appPreferences"
        private val VOICE_RECOGNITION_REQUEST_CODE = 761
    }
}