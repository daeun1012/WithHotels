package io.github.daeun1012.withhotels.ui.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import io.github.daeun1012.withhotels.ui.main.list.HotelListFragment
import io.github.daeun1012.withhotels.ui.main.like.LikeFragment

const val HOTEL_LIST_PAGE_INDEX = 0
const val LIKE_LIST_PAGE_INDEX = 1

class MainPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    /**
     * view pager fragment 초기화
     */
    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        HOTEL_LIST_PAGE_INDEX to { HotelListFragment() },
        LIKE_LIST_PAGE_INDEX to { LikeFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}