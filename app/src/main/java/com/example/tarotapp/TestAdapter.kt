package com.example.tarotapp

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TestAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        val fragment =
            when (position) {
                0 -> DailyFragment()
                1 -> LoveFragment()
                2 -> WeeklyFragment()
                3 -> WorkFragment()
                4 -> AskFragment()
                else -> {
                    Fragment()
                }
            }
        return fragment
    }
}