package com.example.rk1.itemDetail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rk1.data.DataSource
import com.example.rk1.data.Item

class ItemDetailViewModel(private val datasource: DataSource) : ViewModel() {

    /* Queries datasource to returns a item that corresponds to an id. */
    fun getItemForId(id: Long) : Item? {
        return datasource.getItemForId(id)
    }

    /* Queries datasource to remove a item. */
    fun removeItem(item: Item) {
        datasource.removeItem(item)
    }
}

class ItemDetailViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ItemDetailViewModel(
                datasource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}