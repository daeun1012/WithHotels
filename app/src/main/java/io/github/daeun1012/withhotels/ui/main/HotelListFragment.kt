package io.github.daeun1012.withhotels.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.daeun1012.withhotels.databinding.FragmentHotelListBinding

class HotelListFragment : Fragment() {

//    private val viewModel: HotelListViewModel by viewModels {
//        InjectorUtils.provideHotelListViewModelFactory(requireContext())
//    }

    private lateinit var binding: FragmentHotelListBinding

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
        val adapter = HotelListAdapter()
        binding.plantList.adapter = adapter
        subscribeUi(adapter)
    }

    private fun subscribeUi(adapter: HotelListAdapter) {

    }

}
