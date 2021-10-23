package com.example.rk1.itemDetail

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.rk1.R
import com.example.rk1.itemList.ITEM_ID


class ItemDetailActivity : AppCompatActivity() {

    private val itemDetailViewModel by viewModels<ItemDetailViewModel> {
        ItemDetailViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_detail_activity)

        var currentItemId: Long? = null

        /* Connect variables to UI elements. */
        val itemName: TextView = findViewById(R.id.item_detail_name)
        val itemDescription: TextView = findViewById(R.id.item_detail_description)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            currentItemId = bundle.getLong(ITEM_ID)
        }

        /* If currentItemId is not null, get corresponding item and set name, image and
        description */
        currentItemId?.let {
            val currentItem = itemDetailViewModel.getItemForId(it)
            itemName.text = currentItem?.name
            itemDescription.text = currentItem?.description
        }

    }
}