package com.example.recyclerviewdraganddropitem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewdraganddropitem.view.RecyclerViewAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private val itemTouchHelper by lazy {
        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(UP or DOWN or START or END, 0) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                val recyclerViewAdapter = recyclerView.adapter as RecyclerViewAdapter
                val fromPosition = viewHolder.adapterPosition
                val toPosition = target.adapterPosition
                recyclerViewAdapter.moveItem(fromPosition, toPosition)
                recyclerViewAdapter.notifyItemMoved(fromPosition, toPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            }
        }
        ItemTouchHelper(itemTouchCallback)
    }
}