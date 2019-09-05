package io.github.daeun1012.withhotels.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.daeun1012.withhotels.data.local.Hotel
import io.github.daeun1012.withhotels.databinding.ItemHotelBinding

class HotelListAdapter : ListAdapter<Hotel, RecyclerView.ViewHolder>(HotelDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HotelViewHolder(ItemHotelBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)

        // cast generic RecyclerView.ViewHolder to Plant view holder
        (holder as HotelViewHolder).apply {
            bind(createOnClickListener(item.id), item)
        }
    }

    private fun createOnClickListener(id: String): View.OnClickListener {
        return View.OnClickListener {
            val direction = MainFragmentDirections.actionMainToHotel(id)
            it.findNavController().navigate(direction)
        }
    }

    class HotelViewHolder (
        private val binding: ItemHotelBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: Hotel) {
            binding.apply {
                clickListener = listener
                hotel = item
                executePendingBindings()
            }
        }
    }

}

private class HotelDiffCallback : DiffUtil.ItemCallback<Hotel>() {

    override fun areItemsTheSame(oldItem: Hotel, newItem: Hotel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Hotel, newItem: Hotel): Boolean {
        return oldItem == newItem
    }

}