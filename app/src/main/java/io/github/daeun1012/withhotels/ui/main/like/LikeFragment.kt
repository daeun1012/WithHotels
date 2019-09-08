package io.github.daeun1012.withhotels.ui.main.like

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import io.github.daeun1012.withhotels.R
import io.github.daeun1012.withhotels.data.local.Hotel
import io.github.daeun1012.withhotels.data.local.LikeHotel
import io.github.daeun1012.withhotels.databinding.FragmentLikeBinding
import io.github.daeun1012.withhotels.ui.main.MainFragmentDirections
import io.github.daeun1012.withhotels.ui.main.MainViewModel
import io.github.daeun1012.withhotels.utils.InjectorUtils
import timber.log.Timber

class LikeFragment : Fragment() {

    private lateinit var binding: FragmentLikeBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLikeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(
            activity!!,
            InjectorUtils.provideMainViewModelFactory(requireContext())
        ).get(MainViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        val adapter = LikeListAdapter(object : LikeListAdapter.Callback {
            override fun onItemClick(hotel: Hotel) {
                val direction = MainFragmentDirections.actionMainToHotel(hotel)
                this@LikeFragment.findNavController().navigate(direction)
            }

            override fun toggleLike(hotel: Hotel, isLiked: Boolean) {
                if (hotel == null) return

                if (isLiked) {
                    viewModel.addLikes(hotel.id)
                } else {
                    viewModel.deleteLikes(hotel.id)
                }
            }
        })
        initRecycler(adapter)
        subscribeUi(adapter)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_product_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.filter_created_at_desc -> {
                viewModel.setFilter(MainViewModel.CREATD_AT_DESC)
                true
            }
            R.id.filter_created_at_asc -> {
                viewModel.setFilter(MainViewModel.CREATD_AT_ASC)
                true
            }
            R.id.filter_rate_asc -> {
                viewModel.setFilter(MainViewModel.RATE_ASC)
                true
            }
            R.id.filter_rate_desc -> {
                viewModel.setFilter(MainViewModel.RATE_DESC)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun subscribeUi(hotelAdapter: LikeListAdapter) {
        viewModel.pagedListLike.observe(this, Observer<PagedList<LikeHotel>> {
            Timber.d("Likes: ${it?.size}")
            hotelAdapter.submitList(it)
        })
    }

    private fun initRecycler(hotelAdapter: LikeListAdapter) {
        binding.list.apply {
            this.adapter = hotelAdapter
        }
    }

}