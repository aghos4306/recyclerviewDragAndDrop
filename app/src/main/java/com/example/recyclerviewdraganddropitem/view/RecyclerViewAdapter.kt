package com.example.recyclerviewdraganddropitem.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewdraganddropitem.databinding.AdapterItemBinding
import com.example.recyclerviewdraganddropitem.model.User

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: AdapterItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: User) {
            binding.apply {
                textTitle.text = item.name
                textDesc.text = item.location
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int, ): RecyclerViewAdapter.ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterItemBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ItemViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    private val differCallback = object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    fun moveItem(fromPosition: Int, toPosition: Int) {
        val list = differ.currentList.toMutableList()
        val fromItem = list[fromPosition]
        list.removeAt(fromPosition)
        if(toPosition < fromPosition) {
            list.add(toPosition + 1, fromItem)
        } else {
            list.add(toPosition - 1, fromItem)
        }
        differ.submitList(list)
    }
}