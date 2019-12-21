package com.stacktivity.smartwarehouse.utils

interface BaseJson<T> {
    fun getItemList(): List<T>
}