package com.bows.arrows.homesrentals.property_module.view.review_fragment


import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bows.arrows.homesrentals.R
import com.bows.arrows.homesrentals.property_module.model.Property
import com.bows.arrows.homesrentals.property_module.presenter.AddPropertyPresenterImpl
import com.bows.arrows.homesrentals.property_module.view.AddPropertyViewModel
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView

/**
 * A simple [Fragment] subclass.
 *
 */
class PropertyReviewFragment : Fragment(), IPropertyReviewFragment {
    private lateinit var fragView: View
    private lateinit var slider: SliderView
    private lateinit var viewModel: AddPropertyViewModel
    private lateinit var list: List<Any>
    private lateinit var titleTv: TextView
    private lateinit var priceTv: TextView
    private lateinit var locationTv: TextView
    private lateinit var descriptionTv: TextView
    private var property: Property? = null
    private var presenter: AddPropertyPresenterImpl? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        fragView = inflater.inflate(R.layout.fragment_review_property, container, false)
        return fragView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(AddPropertyViewModel::class.java)
        presenter = AddPropertyPresenterImpl(this, context!!)
        property = viewModel.getPropertyObject()
        presenter!!.setPropertyObj(property!!)
        list = property!!.media
        initializeViews(fragView)
    }

    private fun initializeViews(fragView: View) {
        slider = fragView.findViewById(R.id.add_property_image_slider)
        slider.sliderAdapter = SliderAdapter(list)
        slider.setIndicatorAnimation(IndicatorAnimations.WORM)
        slider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        slider.isAutoScrolling = false

        titleTv = fragView.findViewById(R.id.reviewTitleTv)
        titleTv.text = property!!.title
        priceTv = fragView.findViewById(R.id.reviewPriceTv)
        val price = "${property!!.price} ${property!!.currency}"
        priceTv.text = price
        locationTv = fragView.findViewById(R.id.reviewLocationTv)
        locationTv.text = property!!.location
        descriptionTv = fragView.findViewById(R.id.reviewDescriptionTv)
        descriptionTv.text = property!!.description
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.property_review_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                activity!!.onBackPressed()
            }
            R.id.upload_property -> {
                presenter!!.pushPropertyToRoomDB()
            }
        }
        return false
    }

    companion object {
        fun newInstance(): PropertyReviewFragment {
            return PropertyReviewFragment()
        }
    }
}
