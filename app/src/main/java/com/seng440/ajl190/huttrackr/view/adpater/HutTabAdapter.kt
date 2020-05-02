package com.seng440.ajl190.huttrackr.view.adpater

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.seng440.ajl190.huttrackr.view.HutsListFragment
import com.seng440.ajl190.huttrackr.view.TracksFragment

class HutTabAdapter(private val myContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                HutsListFragment()
            }
            1 -> {
                TracksFragment() //todo change to map fragment
            }
            else -> {
                HutsListFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "List View"
            1 -> "Map View"
            else -> "List View"
        }
    }

}