package io.github.daeun1012.withhotels.ui.main.like

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.daeun1012.withhotels.data.local.Hotel
import io.github.daeun1012.withhotels.data.local.Like
import io.github.daeun1012.withhotels.data.local.LikeHotel
import io.github.daeun1012.withhotels.databinding.ItemHotelBinding
import java.text.SimpleDateFormat
import java.util.*

class LikeListAdapter(private val onLikeListener: Callback) : PagedListAdapter<LikeHotel, RecyclerView.ViewHolder>(HotelDiffCallback()) {

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

        fun bind(item: LikeHotel, onLikeListener: Callback) {
            binding.apply {
                Glide.with(binding.ivThumb.context).load(item.hotel.thumbnail).into(binding.ivThumb)
                name = item.hotel.name
                rate = item.hotel.rate.toString()
                isLiked = item.isLiked
                clickListener = View.OnClickListener {
                    onLikeListener.onItemClick(item.hotel)
                }
                likeListener = View.OnClickListener {
                    item.isLiked = !item.isLiked
                    isLiked = item.isLiked
                    onLikeListener.toggleLike(item.hotel, item.isLiked)
                }
                executePendingBindings()
            }
            if(item.createdAt != null) {
                val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:dd", Locale.getDefault())
                binding.tvDate.visibility = View.VISIBLE
                binding.tvDate.text = dateFormat.format(item.createdAt?.time)
            }
        }
    }

    interface Callback {
        fun toggleLike(hotel: Hotel, isLiked: Boolean)
        fun onItemClick(hotel: Hotel)
    }
}

private class HotelDiffCallback : DiffUtil.ItemCallback<LikeHotel>() {

    override fun areItemsTheSame(oldItem: LikeHotel, newItem: LikeHotel): Boolean {
        return oldItem.hotel.id == newItem.hotel.id
    }

    override fun areContentsTheSame(oldItem: LikeHotel, newItem: LikeHotel): Boolean {
        return oldItem == newItem
    }

}