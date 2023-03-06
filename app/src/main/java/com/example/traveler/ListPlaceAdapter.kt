package com.example.traveler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.traveler.databinding.PlaceItemBinding


class ListPlaceAdapter(private val listPlace: ArrayList<Place>) :
    RecyclerView.Adapter<ListPlaceAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(var binding: PlaceItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = PlaceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listPlace.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val(rating, photo, name, location) = listPlace[position]
        holder.binding.tvPlaceRating.text = rating
        holder.binding.ivPlaceImage.setImageResource(photo)
        holder.binding.tvPlaceName.text = name
        holder.binding.tvPlaceLocation.text = location

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listPlace[holder.adapterPosition])
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Place)
    }

}