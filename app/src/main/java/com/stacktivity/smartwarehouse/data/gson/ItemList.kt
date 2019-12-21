package com.stacktivity.smartwarehouse.data.gson

import com.stacktivity.smartwarehouse.data.entities.Item
import com.stacktivity.smartwarehouse.utils.BaseJson

data class ItemList(private val name: String, private val items: List<Item>): BaseJson<Item> {
    override fun getItemList(): List<Item> = items
}