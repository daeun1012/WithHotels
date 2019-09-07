package io.github.daeun1012.withhotels.ui.hotel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.daeun1012.withhotels.databinding.FragmentHotelBinding

class HotelFragment : Fragment() {

    private lateinit var binding: FragmentHotelBinding

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

        binding.onBackClickListener = View.OnClickListener {
            fragmentManager?.popBackStack()
        }
    }

}