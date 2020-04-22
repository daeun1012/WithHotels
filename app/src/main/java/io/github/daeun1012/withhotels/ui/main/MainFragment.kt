package io.github.daeun1012.withhotels.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.tabs.TabLayoutMediator
import io.github.daeun1012.withhotels.R
import io.github.daeun1012.withhotels.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager.adapter = MainPagerAdapter(this)

        // Set the icon and text for each tab
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            // tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            HOTEL_LIST_PAGE_INDEX -> getString(R.string.hotel_list)
            LIKE_LIST_PAGE_INDEX -> getString(R.string.like_list)
            else -> null
        }
    }
}