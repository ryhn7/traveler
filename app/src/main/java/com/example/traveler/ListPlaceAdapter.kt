package com.example.traveler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.traveler.databinding.PlaceItemBinding


class ListPlaceAdapter(private val listPlace: ArrayList<Place>) :
    RecyclerView.Adapter<ListPlaceAdapter.ListViewHolder>() {

    class ListViewHolder(var binding: PlaceItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = PlaceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listPlace.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val(photo) = listPlace[position]
        Glide.with(holder.itemView.context).load(photo).into(holder.binding.ivPlaceImage)
    }

}