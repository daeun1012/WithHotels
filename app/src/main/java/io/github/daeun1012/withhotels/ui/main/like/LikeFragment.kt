package io.github.daeun1012.withhotels.ui.main.like

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import io.github.daeun1012.withhotels.R
import io.github.daeun1012.withhotels.data.local.Hotel
import io.github.daeun1012.withhotels.data.local.SortType
import io.github.daeun1012.withhotels.databinding.FragmentLikeBinding
import io.github.daeun1012.withhotels.ui.main.MainFragmentDirections
import io.github.daeun1012.withhotels.ui.main.MainViewModel
import io.github.daeun1012.withhotels.utils.InjectorUtils
import timber.log.Timber

class LikeFragment : Fragment() {

    private lateinit var binding: FragmentLikeBinding
    private val viewModel: MainViewModel by activityViewModels {
        InjectorUtils.provideMainViewModelFactory(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLikeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        initRecycler()
        subscribeUi()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_product_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.filter_created_at_desc -> {
                viewModel.setFilter(SortType.CREATED_AT_DESC)
                true
            }
            R.id.filter_created_at_asc -> {
                viewModel.setFilter(SortType.CREATED_AT_ASC)
                true
            }
            R.id.filter_rate_asc -> {
                viewModel.setFilter(SortType.RATE_ASC)
                true
            }
            R.id.filter_rate_desc -> {
                viewModel.setFilter(SortType.RATE_DESC)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun subscribeUi() {
        viewModel.pagedListLike.observe(this, Observer<PagedList<Hotel>> {
            Timber.d("Likes: ${it?.size}")
            (binding.list.adapter as? LikeListAdapter)?.submitList(it)
        })
    }

    private fun initRecycler() {
        binding.list.apply {
            this.adapter = LikeListAdapter(object : LikeListAdapter.Callback {
                override fun onItemClick(hotel: Hotel) {
                    val direction = MainFragmentDirections.actionMainToHotel(hotel, hotel.isLiked)
                    this@LikeFragment.findNavController().navigate(direction)
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

}