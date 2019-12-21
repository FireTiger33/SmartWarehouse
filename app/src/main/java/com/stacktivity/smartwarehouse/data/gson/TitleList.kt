package com.stacktivity.smartwarehouse.data.gson

import com.stacktivity.smartwarehouse.utils.BaseJson

data class TitleList(private val name: String, private val titleList: List<String>): BaseJson<String> {
    override fun getItemList(): List<String> = titleList
}