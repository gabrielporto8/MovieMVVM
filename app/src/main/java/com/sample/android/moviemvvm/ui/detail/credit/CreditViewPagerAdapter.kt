package com.sample.android.moviemvvm.ui.detail.credit

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


class CreditViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fragmentManager, lifecycle) {

    val creditFragments: ArrayList<PagerItem> = ArrayList()

    override fun createFragment(position: Int) = creditFragments[position].fragment

    fun addFragment(fragment: PagerItem) {
        creditFragments.add(fragment)
    }

    override fun getItemCount(): Int = creditFragments.size

}

class PagerItem(val fragment: Fragment,
                val title: Int)