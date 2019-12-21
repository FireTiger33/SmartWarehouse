package com.stacktivity.smartwarehouse.data

import android.content.SharedPreferences
import com.stacktivity.smartwarehouse.contracts.CatalogContract
import com.stacktivity.smartwarehouse.data.gson.TitleList
import com.stacktivity.smartwarehouse.utils.PreferencesUtils

class CatalogRepository(private val preferences: SharedPreferences) : CatalogContract.Repository {
    private lateinit var titleList: ArrayList<String>

    init {
        loadSavedItems()
    }

    fun getCatalog(): ArrayList<String> {
        return titleList
    }

    override fun addTitle(name: String) {
        titleList.add(name)
        saveTitleListToPreferences()
    }

    override fun deleteTitle(pos: Int) {
        titleList.removeAt(pos)
    }

    override fun getTitle(pos: Int): String {
        return titleList[pos]
    }

    override fun getTitleCount(): Int {
        return titleList.size
    }


    private fun loadSavedItems() {
        titleList = PreferencesUtils.loadItemListFromJSON(TitleList::class.java, preferences, TITLE_LIST)
    }

    private fun saveTitleListToPreferences() {
        PreferencesUtils.saveItemListToJSON(TitleList(TITLE_LIST, titleList), preferences, TITLE_LIST)
    }

    companion object {
        private val tag = CatalogRepository::class.java.simpleName

        private val TITLE_LIST: String = "titleList"
    }
}