package com.example.rk1.itemList

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.rk1.R
import com.example.rk1.data.WebApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception


/* A list always displaying one element: the number of items. */
class HeaderAdapter(private val itemsListViewModel: ItemsListViewModel) : RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>() {
    private var itemCount: Int = 0

    /* ViewHolder for displaying header. */
    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind() {
        }
    }

    /* Inflates view and returns HeaderViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.header_item, parent, false)

        view.findViewById<Button>(R.id.button_submit).setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {
                /*
                 * For @Query: You need to replace the following line with val response = service.getEmployees(2)
                 * For @Path: You need to replace the following line with val response = service.getEmployee(53)
                 */

                try {
                    val response = WebApi.retrofitService.getData()
                    val data = response.data.data

                    Log.d("LENGTH: ", data.size.toString())

                    for (element in data) {
                        itemsListViewModel.insertItem(
                            element.time.toLong(),
                            "BTC: " + java.time.format.DateTimeFormatter.ISO_INSTANT.format(java.time.Instant.ofEpochSecond(
                                element.time.toLong()
                            )),
                            "average cost: " + (element.low + element.high) / 2
                        )
                        Log.d("ITERATION: ", itemsListViewModel.itemsLiveData.value?.size.toString())
                    }
                } catch (e: Exception) {
                    e.message?.let { it1 -> Log.e("RETROFIT_ERROR: ", it1) }
                }
            }
        }

        return HeaderViewHolder(view)
    }

    /* Binds number of items to the header. */
    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind()
    }

    /* Returns number of items, since there is only one item in the header return one  */
    override fun getItemCount(): Int {
        return 1
    }

    /* Updates header to display number of items when a item is added or subtracted. */
    fun updateItemCount(updatedItemCount: Int) {
        itemCount = updatedItemCount
        notifyDataSetChanged()
    }
}