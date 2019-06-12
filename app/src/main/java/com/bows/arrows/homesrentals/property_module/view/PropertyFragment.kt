package com.bows.arrows.homesrentals.property_module.view


import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.bows.arrows.homesrentals.R

/**
 * A simple [Fragment] subclass.
 *
 */
class PropertyFragment : Fragment() {
    private lateinit var fragView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        fragView = inflater.inflate(R.layout.fragment_property, container, false)
        initializeViews(fragView)
        return fragView
    }

    private fun initializeViews(view: View) {
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.property_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_property -> {
                val intent = Intent(context, AddPropertyActivity::class.java)
                startActivity(intent)
            }
        }
        return false
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun startActivityForResult(intent: Intent?, requestCode: Int) {
        super.startActivityForResult(intent, requestCode)
    }

    companion object {
        fun newInstance(): PropertyFragment {
            return PropertyFragment()
        }
    }
}
