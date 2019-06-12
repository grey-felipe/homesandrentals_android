package com.bows.arrows.homesrentals.property_module.view

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bows.arrows.homesrentals.R
import com.bows.arrows.homesrentals.property_module.adapters.PropertyMediaRecyclerAdapter
import com.bows.arrows.homesrentals.property_module.presenter.AddPropertyPresenterImpl
import com.sangcomz.fishbun.FishBun
import com.sangcomz.fishbun.adapter.image.impl.GlideAdapter
import com.sangcomz.fishbun.define.Define


/**
 * A simple [Fragment] subclass.
 *
 */
class PropertyMediaFragment : Fragment(), IPropertyMediaView {

    private lateinit var fragView: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: PropertyMediaRecyclerAdapter
    private lateinit var addImagesLayout: LinearLayout

    private lateinit var viewModel: AddPropertyViewModel
    private lateinit var presenter: AddPropertyPresenterImpl
    private lateinit var images: List<Uri>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        fragView = inflater.inflate(R.layout.fragment_property_media, container, false)
        presenter = AddPropertyPresenterImpl(this)
        images = emptyList()
        initializeViews(fragView)
        return fragView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(AddPropertyViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.property_media_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.submit_new_property -> {
                presenter.validatePropertyMedia(images, viewModel)
            }
            R.id.add_media -> {
                FishBun.with(this).setImageAdapter(GlideAdapter())
                    .setMinCount(1)
                    .setMaxCount(12)
                    .setActionBarColor(R.color.colorPrimary)
                    .setActionBarTitleColor(android.R.color.white)
                    .textOnNothingSelected("No Images Selected")
                    .textOnImagesSelectionLimitReached("Max Images Selected")
                    .setSelectCircleStrokeColor(R.color.colorPrimary)
                    .startAlbum()
            }
            android.R.id.home -> {
                activity!!.onBackPressed()
            }
        }
        return false
    }

    private fun initializeViews(fragView: View) {
        recyclerView = fragView.findViewById(R.id.media_recyclerview)
        addImagesLayout = fragView.findViewById(R.id.add_images_layout)
    }

    private fun showRecyclerView() {
        addImagesLayout.visibility = View.GONE
        viewModel.addMedia(images)
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        recyclerAdapter = PropertyMediaRecyclerAdapter(images, context!!)
        recyclerView.adapter = recyclerAdapter

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            Define.ALBUM_REQUEST_CODE -> {
                if (resultCode == RESULT_OK) {
                    images = data!!.getParcelableArrayListExtra(Define.INTENT_PATH)
                    showRecyclerView()
                }
            }
        }
    }

    override fun onValidationError(message: String) {
        Toast.makeText(context, "$message were not provided", Toast.LENGTH_SHORT).show()
    }


    companion object {
        fun newInstance(): PropertyMediaFragment {
            return PropertyMediaFragment()
        }
    }
}
