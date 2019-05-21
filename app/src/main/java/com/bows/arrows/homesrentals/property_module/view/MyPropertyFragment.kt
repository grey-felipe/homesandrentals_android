package com.bows.arrows.homesrentals.property_module.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bows.arrows.homesrentals.R

/**
 * A simple [Fragment] subclass.
 *
 */
class MyPropertyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_property, container, false)
    }

    companion object {
        fun newInstance(): MyPropertyFragment {
            return MyPropertyFragment()
        }
    }
}
