package com.example.recyclerviewdraganddropitem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewdraganddropitem.databinding.ActivityMainBinding
import com.example.recyclerviewdraganddropitem.model.User
import com.example.recyclerviewdraganddropitem.view.RecyclerViewAdapter

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        itemTouchHelper.attachToRecyclerView(binding.recyclerview)
        recyclerViewAdapter = RecyclerViewAdapter()
        recyclerViewAdapter.differ.submitList(getUsers())
        binding.recyclerview.adapter = recyclerViewAdapter
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

            override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
                super.onSelectedChanged(viewHolder, actionState)
                if(actionState == ACTION_STATE_DRAG) {
                    viewHolder?.itemView?.scaleY = 1.3f
                    viewHolder?.itemView?.alpha = 0.7f
                }
            }

            override fun clearView(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ) {
                super.clearView(recyclerView, viewHolder)
                viewHolder.itemView.scaleY = 1.0f
                viewHolder.itemView.alpha = 1.0f
            }
        }
        ItemTouchHelper(itemTouchCallback)
    }

    private fun getUsers() : List<User> {
        val user = mutableListOf<User>()
        user.add(User("John", "Doe", 25))
        user.add(User("Oreva", "Iroro", 23))
        user.add(User("Michael", "Peace", 32))
        user.add(User("Kelvin", "Onos", 35))
        user.add(User("Keno", "Nkem", 25))
        user.add(User("Runo", "Joshua", 28))
        user.add(User("Feji", "StillSearching", 17))
        user.add(User("Evi", "Onos", 32))
        return user
    }
}