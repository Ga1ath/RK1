package com.example.rk1.data

import android.content.res.Resources
import com.example.rk1.R


/* Returns initial list of items. */
fun itemList(resources: Resources): List<Item> {
    return listOf(
        Item(
            0, "Chronological list of crypto", "Contains lowest and highest values per day"
        )
    )
}