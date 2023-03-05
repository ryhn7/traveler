package com.example.traveler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.traveler.databinding.CategoryItemBinding

class ListCategoryAdapter(private val listCategory: ArrayList<Category>) :
    RecyclerView.Adapter<ListCategoryAdapter.ListViewHolder>() {

    class ListViewHolder(var binding: CategoryItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listCategory.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val(name) = listCategory[position]
        holder.binding.tvCategoryName.text = name
    }
}