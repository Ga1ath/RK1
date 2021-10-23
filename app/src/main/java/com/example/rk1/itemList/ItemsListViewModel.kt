package com.example.rk1.itemList

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rk1.data.DataSource
import com.example.rk1.data.Item


class ItemsListViewModel(val dataSource: DataSource) : ViewModel() {

    val itemsLiveData = dataSource.getItemList()

    /* If the name and description are present, create new Item and add it to the datasource */
    fun insertItem(itemId: Long, itemName: String?, itemDescription: String?) {
        if (itemName == null || itemDescription == null) {
            return
        }

        val newItem = Item(
            itemId,
            itemName,
            itemDescription
        )

        dataSource.addItem(newItem)
    }
}

class ItemsListViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemsListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ItemsListViewModel(
                dataSource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}