package io.github.daeun1012.withhotels.ui.main.hotels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.daeun1012.withhotels.data.local.Hotel
import io.github.daeun1012.withhotels.databinding.ItemHotelBinding

class HotelListAdapter(private val onLikeListener: Callback) : PagedListAdapter<Hotel, RecyclerView.ViewHolder>(HotelDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HotelViewHolder(ItemHotelBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if(item != null) {
            (holder as HotelViewHolder).apply {
                bind(item, onLikeListener)
            }
        }

    }

    class HotelViewHolder (private val binding: ItemHotelBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Hotel, onLikeListener: Callback) {
            binding.apply {
                Glide.with(binding.ivThumb.context).load(item.thumbnail).into(binding.ivThumb)
                name = item.name
                rate = item.rate.toString()
                isLiked = item.isLiked
                clickListener = View.OnClickListener {
                    onLikeListener.onItemClick(item)
                }
                likeListener = View.OnClickListener {
                    item.isLiked = MutableLiveData(!isLiked?.value!!)
                    isLiked = item.isLiked
                    onLikeListener.toggleLike(item)
                }
                executePendingBindings()
            }
        }
    }

    interface Callback {
        fun toggleLike(hotel: Hotel)
        fun onItemClick(hotel: Hotel)
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