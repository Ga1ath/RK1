package com.example.rk1.data

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


/* Handles operations on itemsLiveData and holds details about it. */
class DataSource(resources: Resources) {
    private val initialItemList = itemList(resources)
    private val itemsLiveData = MutableLiveData(initialItemList)

    /* Returns item given an ID. */
    fun getItemForId(id: Long): Item? {
        itemsLiveData.value?.let { items ->
            return items.firstOrNull{ it.id == id}
        }
        return null
    }

    fun getItemList(): LiveData<List<Item>> {
        return itemsLiveData
    }

    /* Adds item to liveData and posts value. */
    fun addItem(item: Item) {
        val currentList = itemsLiveData.value
        if (currentList == null) {
            itemsLiveData.postValue(listOf(item))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(item)
            itemsLiveData.postValue(updatedList)
        }
    }

    /* Removes item from liveData and posts value. */
    fun removeItem(item: Item) {
        val currentList = itemsLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(item)
            itemsLiveData.postValue(updatedList)
        }
    }

    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(resources: Resources): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}