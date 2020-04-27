package io.github.daeun1012.withhotels.ui.main.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.daeun1012.withhotels.R
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
                this.item = item
                clickListener = View.OnClickListener {
                    onLikeListener.onItemClick(item)
                }
                likeListener = View.OnClickListener {
                    item.isLiked = !item.isLiked

                    binding.ivLike.setImageResource(if(item.isLiked) {
                        R.drawable.ic_favorite_black_24dp
                    } else {
                        R.drawable.ic_favorite_border_black_24dp
                    })
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