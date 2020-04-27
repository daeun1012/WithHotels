package io.github.daeun1012.withhotels.ui.main.like

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.daeun1012.withhotels.data.local.Hotel
import io.github.daeun1012.withhotels.databinding.ItemHotelBinding
import java.text.SimpleDateFormat
import java.util.*

class LikeListAdapter(private val onLikeListener: Callback) : PagedListAdapter<Hotel, RecyclerView.ViewHolder>(HotelDiffCallback()) {

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
                this.item = item
                clickListener = View.OnClickListener {
                    onLikeListener.onItemClick(item)
                }
                likeListener = View.OnClickListener {
                    item.isLiked = !item.isLiked
                    onLikeListener.toggleLike(item)
                }
                executePendingBindings()
            }
            if(item.createdAt != null) {
                val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                binding.tvDate.visibility = View.VISIBLE
                binding.tvDate.text = dateFormat.format(item.createdAt?.time)
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