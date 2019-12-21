package com.stacktivity.smartwarehouse.data

import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.stacktivity.smartwarehouse.contracts.InventoryContract
import com.stacktivity.smartwarehouse.data.dao.ItemsDao
import com.stacktivity.smartwarehouse.data.entities.Item
import com.stacktivity.smartwarehouse.data.gson.ItemList
import com.stacktivity.smartwarehouse.utils.PreferencesUtils

class InventoryRepository(
    private val local: ItemsDao?
): InventoryContract.Repository {
    private lateinit var preferences: SharedPreferences  // TODO barrier if preferences is not init
    private lateinit var items: ArrayList<Item>
    private lateinit var catalog: ArrayList<String>

    constructor( preferences: SharedPreferences, local: ItemsDao?): this(local) {
        this.preferences = preferences
        loadSavedItems()
        loadCatalog()
    }


    override fun addItem(item: Item) {
        items.add(item)
        saveItemsToPreferences()
    }

    override fun removeItem(pos: Int) {
        items.removeAt(pos)
        saveItemsToPreferences()
    }

    override fun restoreItem(item: Item, pos: Int) {
        items.add(pos, item)
        saveItemsToPreferences()
    }

    override fun getItemsCount(): Int {
        return items.size
    }

    override fun getItem(pos: Int): Item {
        return items[pos]
    }

    override fun getCatalog(): ArrayList<String> {
        return catalog
    }

    override fun clearData() {
        items.clear()
        saveItemsToPreferences()
    }

    private fun loadSavedItems() {
        items = PreferencesUtils.loadItemListFromJSON(ItemList::class.java, preferences, INVENTORY_ITEMS)
    }

    private fun loadCatalog() {
        catalog = CatalogRepository(preferences).getCatalog()
    }

    private fun saveItemsToPreferences() {
        PreferencesUtils.saveItemListToJSON(ItemList(INVENTORY_ITEMS, items), preferences, INVENTORY_ITEMS)
    }

    private suspend fun getLocalItems() {  // TODO coroutine
        local?.getItems()
    }

    companion object {
        private val tag = InventoryRepository::class.java.simpleName
        private val INVENTORY_ITEMS = "notSavedItems"
    }

}