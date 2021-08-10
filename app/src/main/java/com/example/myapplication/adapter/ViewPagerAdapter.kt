package com.example.myapplication.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.activities.main_fragments.DialogPopupFragment
import com.example.myapplication.activities.main_fragments.NewsListFragment
import com.example.myapplication.activities.main_fragments.ShapeExampleFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    val FRAG_COUNT = 3
    override fun getItemCount(): Int {
        return FRAG_COUNT
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> NewsListFragment()
            1 -> ShapeExampleFragment()
            else -> DialogPopupFragment()
        }
    }

}

