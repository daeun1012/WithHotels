package io.github.daeun1012.withhotels.ui.main.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import io.github.daeun1012.withhotels.R
import io.github.daeun1012.withhotels.data.local.Hotel
import io.github.daeun1012.withhotels.databinding.FragmentHotelBinding
import io.github.daeun1012.withhotels.ui.main.MainViewModel
import io.github.daeun1012.withhotels.utils.InjectorUtils

class HotelFragment : Fragment() {

    private lateinit var binding: FragmentHotelBinding
    private val viewModel: MainViewModel by viewModels {
        InjectorUtils.provideMainViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHotelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val item: Hotel = arguments?.get("hotel") as Hotel
        val isLiked = viewModel.isLiked(item.id)

        isLiked.observe(this, Observer<Boolean> {
            if(it){
                binding.fab.setImageResource(R.drawable.ic_favorite_black_24dp)
            } else {
                binding.fab.setImageResource(R.drawable.ic_favorite_border_black_24dp)
            }
        })
        binding.hotel = item

        binding.lifecycleOwner = this
        binding.likeListener = object : Callback {
            override fun toggleLike(hotel: Hotel) {
                if(isLiked == null || !isLiked.value!!) {
                    viewModel.addLikes(item.id)
                } else {
                    viewModel.deleteLikes(item.id)
                }

            }
        }

        Glide.with(binding.detailImage.context).load(item.description?.imagePath).into(binding.detailImage)
        binding.tvName.text = item.name
        binding.tvSubject.text = item.description?.subject
        binding.tvPrice.text = "가격 ${item.description?.price.toString()} 원"

        binding.toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }

        var isToolbarShown = false

        binding.hotelDetailScrollview.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->

                val shouldShowToolbar = scrollY > binding.toolbar.height

                if (isToolbarShown != shouldShowToolbar) {
                    isToolbarShown = shouldShowToolbar

                    // Use shadow animator to add elevation if toolbar is shown
                    binding.appbar.isActivated = shouldShowToolbar

                    binding.toolbarLayout.isTitleEnabled = shouldShowToolbar
                }
            }
        )
    }

    interface Callback {
        fun toggleLike(hotel: Hotel)
    }
}