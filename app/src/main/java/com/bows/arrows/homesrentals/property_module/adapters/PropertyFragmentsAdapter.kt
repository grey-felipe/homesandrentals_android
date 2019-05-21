package com.bows.arrows.homesrentals.property_module.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.bows.arrows.homesrentals.property_module.view.AddPropertyFragment
import com.bows.arrows.homesrentals.property_module.view.MyPropertyFragment

class PropertyFragmentsAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                AddPropertyFragment.newInstance()
            }
            1 -> {
                MyPropertyFragment.newInstance()
            }
            else -> {
                return AddPropertyFragment.newInstance()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Add Property"
            1 -> "My Property"
            else -> {
                return "Third Tab"
            }
        }
    }
}