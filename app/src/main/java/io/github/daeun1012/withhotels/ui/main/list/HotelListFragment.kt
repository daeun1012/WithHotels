package io.github.daeun1012.withhotels.ui.main.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import io.github.daeun1012.withhotels.data.local.Hotel
import io.github.daeun1012.withhotels.databinding.FragmentHotelListBinding
import io.github.daeun1012.withhotels.ui.main.MainViewModel
import io.github.daeun1012.withhotels.ui.main.MainFragmentDirections
import io.github.daeun1012.withhotels.utils.InjectorUtils
import timber.log.Timber

class HotelListFragment : Fragment() {

    private lateinit var binding: FragmentHotelListBinding
    private val viewModel: MainViewModel by activityViewModels {
        InjectorUtils.provideMainViewModelFactory(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHotelListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        subscribeUi()

        viewModel.getHotels()
    }

    private fun initRecycler() {
        binding.list.apply {
            this.adapter = HotelListAdapter(object : HotelListAdapter.Callback {

                override fun onItemClick(hotel: Hotel) {
                    val direction = MainFragmentDirections.actionMainToHotel(hotel, hotel.isLiked)
                    this@HotelListFragment.findNavController().navigate(direction)
                }

                override fun toggleLike(hotel: Hotel) {
                    if (hotel.isLiked) {
                        viewModel.addLikes(hotel)
                    } else {
                        viewModel.deleteLikes(hotel)
                    }
                }
            })
        }
    }

    private fun subscribeUi() {
        viewModel.pagedListHotel.observe(this, Observer<PagedList<Hotel>> {
            Timber.d("Hotels: ${it?.size}")
            (binding.list.adapter as? HotelListAdapter)?.submitList(it)
        })

        viewModel.updatePosition.observe(this, Observer {
            if(it == null) return@Observer

            binding.list.adapter?.notifyDataSetChanged()
            viewModel.updatePosition.postValue(null)
        })
    }
}
