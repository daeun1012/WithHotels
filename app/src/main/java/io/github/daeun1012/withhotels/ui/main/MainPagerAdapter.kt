package io.github.daeun1012.withhotels.ui.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

const val HOTEL_LIST_PAGE_INDEX = 0
const val LIKE_LIST_PAGE_INDEX = 1

class MainPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    /**
     * view pager fragment 초기화
     */
    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        HOTEL_LIST_PAGE_INDEX to { HotelListFragment() },
        LIKE_LIST_PAGE_INDEX to { LikeListFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}