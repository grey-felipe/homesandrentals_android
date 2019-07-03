package com.bows.arrows.homesrentals.authentication.view


import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bows.arrows.homesrentals.R
import com.bows.arrows.homesrentals.authentication.presenter.SignUpPresenterImpl


class SignUpFragment : Fragment(), ISignUpView {

    lateinit var usernameEdt: EditText
    lateinit var emailEdt: EditText
    lateinit var phoneEdt: EditText
    lateinit var passwordEdt: EditText


    private lateinit var fragView: View
    private lateinit var signUpPresenterImplObj: SignUpPresenterImpl

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        fragView = inflater.inflate(R.layout.fragment_sign_up, container, false)
        signUpPresenterImplObj = SignUpPresenterImpl(this)
        initializeViews(fragView)
        return fragView
    }

    private fun initializeViews(view: View) {
        usernameEdt = view.findViewById(R.id.registerUsernameEdt)
        emailEdt = view.findViewById(R.id.registerEmailEdt)
        phoneEdt = view.findViewById(R.id.registerPhoneEdt)
        passwordEdt = view.findViewById(R.id.registerPasswordEdt)
    }

    override fun submitUserData() {
        signUpPresenterImplObj.validateData(
            usernameEdt.text.toString().trim(),
            emailEdt.text.toString().trim(),
            phoneEdt.text.toString().trim(),
            passwordEdt.text.toString().trim()
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.registeration_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.reg_done -> {
                submitUserData()
            }
            android.R.id.home -> {
                activity!!.onBackPressed()
            }
        }
        return false
    }

    override fun onValidationError(value: String) {
        Toast.makeText(context, "$value was not provided.", Toast.LENGTH_SHORT).show()
    }

    override fun getFragContext(): Context {
        return context!!
    }

    override fun onError(error: String) {
        Toast.makeText(context, "Registration failed, $error", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess(message: String) {
        fragmentManager!!.popBackStackImmediate()
    }

    companion object {
        fun newInstance(): SignUpFragment {
            return SignUpFragment()
        }
    }
}
