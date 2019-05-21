package com.bows.arrows.homesrentals.home_module.view


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
class HomeFragment : Fragment() {
    private lateinit var fragView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragView = inflater.inflate(R.layout.fragment_home, container, false)
        return fragView
    }

    fun initializeView(view: View) {
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}
