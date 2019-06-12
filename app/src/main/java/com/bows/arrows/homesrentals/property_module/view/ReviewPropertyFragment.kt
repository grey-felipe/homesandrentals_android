package com.bows.arrows.homesrentals.property_module.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bows.arrows.homesrentals.R

/**
 * A simple [Fragment] subclass.
 *
 */
class ReviewPropertyFragment : Fragment() {
    private lateinit var fragView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragView = inflater.inflate(R.layout.fragment_review_property, container, false)
        return fragView
    }

}
