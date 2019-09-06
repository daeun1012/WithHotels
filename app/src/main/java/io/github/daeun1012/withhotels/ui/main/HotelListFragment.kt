package io.github.daeun1012.withhotels.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import io.github.daeun1012.withhotels.data.local.Hotel
import io.github.daeun1012.withhotels.databinding.FragmentHotelListBinding
import io.github.daeun1012.withhotels.utils.InjectorUtils
import io.github.daeun1012.withhotels.viewmodel.HotelListViewModel
import timber.log.Timber

class HotelListFragment : Fragment() {

    private lateinit var viewModel: HotelListViewModel
    private lateinit var binding: FragmentHotelListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHotelListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this, InjectorUtils.provideViewModelFactory(requireContext())).get(HotelListViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = HotelListAdapter()
        binding.list.adapter = adapter
        subscribeUi(adapter)

        viewModel.getHotels()
    }

    private fun subscribeUi(adapter: HotelListAdapter) {
        viewModel.hotels.observe(this, Observer<List<Hotel>> {
            Timber.d("list: ${it?.size}")
            showEmptyList(it?.size == 0)
            adapter.submitList(it)
        })
        viewModel.networkErrors.observe(this, Observer<String> {
            Toast.makeText(requireContext(), "\uD83D\uDE28 Wooops $it", Toast.LENGTH_LONG).show()
        })
    }

    private fun showEmptyList(show: Boolean) {
        if (show) {
            binding.emptyList.visibility = View.VISIBLE
            binding.list.visibility = View.GONE
        } else {
            binding.emptyList.visibility = View.GONE
            binding.list.visibility = View.VISIBLE
        }
    }

}
