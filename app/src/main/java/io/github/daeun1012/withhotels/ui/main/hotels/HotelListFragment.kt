package io.github.daeun1012.withhotels.ui.main.hotels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import io.github.daeun1012.withhotels.data.local.Hotel
import io.github.daeun1012.withhotels.databinding.FragmentHotelListBinding
import io.github.daeun1012.withhotels.ui.main.MainViewModel
import io.github.daeun1012.withhotels.ui.main.MainFragmentDirections
import io.github.daeun1012.withhotels.utils.InjectorUtils
import timber.log.Timber

class HotelListFragment : Fragment() {

    private lateinit var binding: FragmentHotelListBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHotelListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(
            activity!!,
            InjectorUtils.provideMainViewModelFactory(requireContext())
        ).get(MainViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = HotelListAdapter(object : HotelListAdapter.Callback {
            override fun onItemClick(hotel: Hotel) {
                val direction = MainFragmentDirections.actionMainToHotel(hotel)
                this@HotelListFragment.findNavController().navigate(direction)
            }

            override fun toggleLike(hotel: Hotel) {
                if (hotel == null) return

                val isLikeToggle = hotel.isLiked
                if (isLikeToggle) {
                    viewModel.addLikes(hotel.id)
                } else {
                    viewModel.deleteLikes(hotel.id)
                }
            }
        })
        initRecycler(adapter)
        subscribeUi(adapter)

        viewModel.getHotels()
    }

    private fun initRecycler(hotelAdapter: HotelListAdapter) {
        binding.list.apply {
            this.adapter = hotelAdapter
        }
        binding.refreshLayout.setOnRefreshListener {
            viewModel.getHotels()
        }
    }

    private fun subscribeUi(hotelAdapter: HotelListAdapter) {
        viewModel.pagedListHotel.observe(this, Observer<PagedList<Hotel>> {
            Timber.d("Hotels: ${it?.size}")
            hotelAdapter.submitList(it)
        })
    }
}
