package com.stacktivity.smartwarehouse.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Item(@PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "item_name") var itemName: String,
    @ColumnInfo(name = "item_count") var itemCount: Int,
    @ColumnInfo(name = "quantity_name ") var qtyName: String
) {

    @Ignore
    constructor(itemName: String,
                itemCount: Int,
                qtyName: String
    ): this(null, itemName, itemCount, qtyName)
}