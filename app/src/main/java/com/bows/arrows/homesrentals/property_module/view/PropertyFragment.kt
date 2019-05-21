package com.bows.arrows.homesrentals.property_module.view


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.bows.arrows.homesrentals.R
import com.bows.arrows.homesrentals.property_module.adapters.PropertyFragmentsAdapter
import com.google.android.material.tabs.TabLayout

/**
 * A simple [Fragment] subclass.
 *
 */
class PropertyFragment : Fragment() {
    private lateinit var fragView: View
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        fragView = inflater.inflate(R.layout.fragment_property, container, false)
        initializeViews(fragView)
        initializeFragmentAdapter()
        return fragView
    }

    private fun initializeViews(view: View) {
        viewPager = view.findViewById(R.id.main_fragments_viewpager)
        tabLayout = view.findViewById(R.id.main_fragments_tab_layout)
    }

    private fun initializeFragmentAdapter() {
        val fragmentAdapter = PropertyFragmentsAdapter(childFragmentManager)
        viewPager.adapter = fragmentAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

    companion object {
        fun newInstance(): PropertyFragment {
            return PropertyFragment()
        }
    }

}
