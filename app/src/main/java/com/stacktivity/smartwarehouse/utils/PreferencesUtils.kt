package com.stacktivity.smartwarehouse.utils

import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import java.lang.reflect.Constructor
import java.util.function.Predicate

class PreferencesUtils {

    companion object {
        private val tag = PreferencesUtils::class.java.simpleName

        fun <ClassJSON: BaseJson<ItemClass>, ItemClass> loadItemListFromJSON(
            jsonClass: Class<ClassJSON>,
            preferences: SharedPreferences,
            key: String
        ): ArrayList<ItemClass> {
            return if (preferences.contains(key)) {
                val titlesJSON = preferences.getString(key, null)
                val jsonData: ClassJSON = Gson().fromJson(titlesJSON, jsonClass)
                ArrayList(jsonData.getItemList())
            } else {
                ArrayList()
            }
        }

        fun <ClassJSON> saveItemListToJSON(
            itemsClass: ClassJSON,
            preferences: SharedPreferences,
            key: String
        ) {
            Log.d(tag, itemsClass.toString())
            val itemsJSON = Gson().toJson(itemsClass)
            Log.d(tag, itemsJSON)

            var success = false
            while (!success) {
                success = preferences.edit()
                    .putString(key, itemsJSON)
                    .commit()
            }

        }


    }
}