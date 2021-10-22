package com.example.rk1.itemList

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rk1.R
import com.example.rk1.data.Item
import com.example.rk1.itemDetail.ItemDetailActivity

const val ITEM_ID = "item id"

class ItemsListActivity : AppCompatActivity() {

    private val itemsListViewModel by viewModels<ItemsListViewModel> {
        ItemsListViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_frag_main)

        /* Instantiates headerAdapter and itemsAdapter. Both adapters are added to concatAdapter.
        which displays the contents sequentially */
        val headerAdapter = HeaderAdapter()
        val itemsAdapter = ItemsAdapter { item -> adapterOnClick(item) }
        val concatAdapter = ConcatAdapter(headerAdapter, itemsAdapter)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = concatAdapter

        itemsListViewModel.itemsLiveData.observe(this, {
            it?.let {
                itemsAdapter.submitList(it as MutableList<Item>)
                headerAdapter.updateItemCount(it.size)
            }
        })
    }

    /* Opens ItemDetailActivity when RecyclerView item is clicked. */
    private fun adapterOnClick(item: Item) {
        val intent = Intent(this, ItemDetailActivity()::class.java)
        intent.putExtra(ITEM_ID, item.id)
        startActivity(intent)
    }
}