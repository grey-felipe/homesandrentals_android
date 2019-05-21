package com.bows.arrows.homesrentals.property_module.view


import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bows.arrows.homesrentals.R
import com.bows.arrows.homesrentals.property_module.presenter.AddPropertyPresenterImpl
import com.google.android.material.chip.ChipGroup
import com.jaredrummler.materialspinner.MaterialSpinner

/**
 * A simple [Fragment] subclass.
 *
 */
class AddPropertyFragment : Fragment(), IAddPropertyView {

    private lateinit var fragView: View
    private lateinit var materialSpinner: MaterialSpinner
    private lateinit var titleEdt: EditText
    private lateinit var priceEdt: EditText
    private lateinit var descriptionEdt: EditText
    private lateinit var chipGroup: ChipGroup
    private lateinit var addPropertyPresenter: AddPropertyPresenterImpl
    private var currency: String = "USD"
    private var type: String = ""
//    private var mediaList:List<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        fragView = inflater.inflate(R.layout.fragment_add_property, container, false)
        addPropertyPresenter = AddPropertyPresenterImpl(this)
        initializeViews(fragView)
        return fragView
    }

    private fun initializeViews(fragView: View) {
        initializeSpinner(fragView)
        titleEdt = fragView.findViewById(R.id.propertyNameEdt)
        priceEdt = fragView.findViewById(R.id.propertyPriceEdt)
        descriptionEdt = fragView.findViewById(R.id.propertyDescriptionEdt)
        initializeChipGroup(fragView)
    }

    private fun initializeSpinner(fragView: View) {
        materialSpinner = fragView.findViewById(R.id.propertyCurrencySpinner)
        materialSpinner.setItems("USD", "USHS")
        materialSpinner.setOnItemSelectedListener { view, position, id, item ->
            currency = item.toString()
        }
    }

    private fun initializeChipGroup(fragView: View) {
        chipGroup = fragView.findViewById(R.id.add_property_chip_group)
        chipGroup.setOnCheckedChangeListener { group, checkedId ->
            when (group.checkedChipId) {
                R.id.add_property_for_sale_chip -> {
                    type = "for sale"
                }
                R.id.add_property_for_rent_chip -> {
                    type = "for rent"
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_property_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_property_location -> {
            }
            R.id.add_property_media -> {
            }
            R.id.add_property -> {
                submitPropertyData()
            }
        }
        return false
    }

    override fun submitPropertyData() {
        addPropertyPresenter.validatePropertyData(
            titleEdt.text.toString(),
            catchPriceBlank(priceEdt.text.toString().trim()),
            currency,
            type,
            true,
            descriptionEdt.text.toString(),
            "n/a",
            "n/a",
            listOf("https://url2.com", "https://url1.com")
        )
    }

    private fun catchPriceBlank(value: String): Int {
        var price = 0
        return if (priceEdt.text.toString().trim() == "") {
            price
        } else {
            price = priceEdt.text.toString().trim().toInt()
            price
        }
    }

    override fun onValidationError(message: String) {
        Toast.makeText(context, "$message was not provided", Toast.LENGTH_SHORT).show()
    }

    override fun onSubmitError() {
        Toast.makeText(
            context, "Adding property failed, check your network or try again later.", Toast.LENGTH_SHORT
        ).show()
    }

    override fun onSubmitSuccess() {
        Toast.makeText(context, "Adding property was successful.", Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance(): AddPropertyFragment {
            return AddPropertyFragment()
        }
    }
}
