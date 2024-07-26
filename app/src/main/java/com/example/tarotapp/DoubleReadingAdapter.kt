package com.example.tarotapp

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class DoubleReadingAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment =
            when (position) {
                0 -> DailyTextFragment()
                1 -> WeeklyTextFragment()
                else -> {
                    Fragment()
                }
            }
        return fragment
    }
}