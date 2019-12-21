package com.stacktivity.smartwarehouse.data.dao

import androidx.room.*
import com.stacktivity.smartwarehouse.data.entities.Item

@Dao
interface ItemsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun incert(items: Item)

    @Query("SELECT * from Item")
    suspend fun getItems(): List<Item>

    @Delete
    suspend fun deleteItem(item: Item)

    @Query("UPDATE Item set item_count = item_count + :count where id = :id")
    suspend fun increaseCount(id: Int, count: Int)
}