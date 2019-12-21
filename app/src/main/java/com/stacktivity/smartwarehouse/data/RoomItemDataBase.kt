package com.stacktivity.smartwarehouse.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.stacktivity.smartwarehouse.data.dao.ItemsDao
import com.stacktivity.smartwarehouse.data.entities.Item

@Database(
    entities = [Item::class],
    version = 1
)
abstract class RoomItemDataBase: RoomDatabase() {

    abstract fun itemsDao(): ItemsDao

    companion object {
        @Volatile // For Singleton instantiation
        private var INSTANCE: RoomItemDataBase? = null

        fun getInstance(context: Context): RoomItemDataBase? {
            return INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDataBase(context).also { INSTANCE = it }
            }
        }

        fun destroyInstance() {
            INSTANCE = null
        }

        private fun buildDataBase(context: Context): RoomItemDataBase {
            return Room.databaseBuilder(context.applicationContext,
                RoomItemDataBase::class.java, "items.db")
                .build()
        }

    }
}