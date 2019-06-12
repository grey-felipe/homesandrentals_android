package com.bows.arrows.homesrentals.property_module.view


import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bows.arrows.homesrentals.R
import com.bows.arrows.homesrentals.property_module.model.Property
import com.bows.arrows.homesrentals.property_module.presenter.PropertyInfoPresenterImpl
import com.google.android.material.chip.ChipGroup
import com.jaredrummler.materialspinner.MaterialSpinner

/**
 * A simple [Fragment] subclass.
 *
 */
class PropertyInfoFragment : Fragment(), IPropertyInfoView {

    private lateinit var fragView: View
    private lateinit var presenter: PropertyInfoPresenterImpl

    private lateinit var titleEdt: EditText
    private lateinit var priceEdt: EditText
    private lateinit var descriptionEdt: EditText
    private lateinit var chipGroup: ChipGroup
    private lateinit var spinner: MaterialSpinner

    private var type = ""
    private var currency = "USHS"

    private lateinit var viewModel: AddPropertyViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        fragView = inflater.inflate(R.layout.fragment_property_info, container, false)
        presenter = PropertyInfoPresenterImpl(this)
        initializeViews(fragView)
        return fragView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(AddPropertyViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.property_info_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.property_info_next -> {
                presenter.validatePropertyData(
                    titleEdt.text.toString(),
                    parsePrice(priceEdt.text.toString()),
                    currency,
                    type,
                    true,
                    descriptionEdt.text.toString()
                )
            }
            android.R.id.home -> {
                activity!!.onBackPressed()
            }
        }
        return false
    }

    private fun initializeViews(fragView: View) {
        titleEdt = fragView.findViewById(R.id.propertyTitleEdt)
        priceEdt = fragView.findViewById(R.id.propertyPriceEdt)
        descriptionEdt = fragView.findViewById(R.id.propertyDescriptionEdt)
        initializeChips(fragView)
        initializeSpinner(fragView)
    }

    private fun initializeChips(fragView: View) {
        chipGroup = fragView.findViewById(R.id.add_property_chip_group)
        chipGroup.setOnCheckedChangeListener { group, checkedId ->
            when (group.checkedChipId) {
                R.id.add_property_for_sale_chip -> {
                    type = "FOR SALE"
                }
                R.id.add_property_for_rent_chip -> {
                    type = "FOR RENT"
                }
            }
        }
    }

    private fun initializeSpinner(fragView: View) {
        spinner = fragView.findViewById(R.id.propertyCurrencySpinner)
        spinner.setItems("USHS", "USD")
        spinner.setOnItemSelectedListener { view, position, id, item ->
            currency = item.toString()
        }

    }

    private fun parsePrice(value: String): Int {
        var price = 0
        return if (value.isEmpty() || value.isBlank()) {
            price
        } else {
            price = value.toInt()
            price
        }
    }

    override fun onValidationError(message: String) {
        Toast.makeText(context, "$message was not provided", Toast.LENGTH_SHORT).show()
    }

    override fun startLocationFragment(property: Property) {
        viewModel.setPropertyObject(property)
        fragmentManager!!.beginTransaction()
            .replace(
                R.id.add_property_activity_fragment_container,
                PropertyLocationFragment.newInstance(),
                "PROPERTY_LOCATION_FRAGMENT"
            )
            .addToBackStack("PROPERTY_INFO_FRAGMENT")
            .commit()
    }

    companion object {
        fun newInstance(): PropertyInfoFragment {
            return PropertyInfoFragment()
        }
    }
}
