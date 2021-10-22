package com.example.rk1.data

import android.content.res.Resources
import com.example.rk1.R


/* Returns initial list of items. */
fun itemList(resources: Resources): List<Item> {
    return listOf(
        Item(
            id = 1,
            name = resources.getString(R.string.item1_name),
            description = resources.getString(R.string.item1_description)
        )
    )
}