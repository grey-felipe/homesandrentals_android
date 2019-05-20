package com.bows.arrows.homesrentals.authentication.view


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bows.arrows.homesrentals.MainActivity
import com.bows.arrows.homesrentals.R
import com.bows.arrows.homesrentals.authentication.presenter.LoginPresenterImpl
import com.bows.arrows.homesrentals.utilities.Constants


/**
 * A simple [Fragment] subclass.
 *
 */
class LoginFragment : Fragment(), View.OnClickListener, ILoginView {

    private lateinit var fragView: View
    private lateinit var emailEdt: EditText
    private lateinit var passwordEdt: EditText
    private lateinit var signUpBtn: Button
    private lateinit var loginBtn: Button

    private lateinit var loginPresenterImpl: LoginPresenterImpl

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        fragView = inflater.inflate(R.layout.fragment_login, container, false)
        loginPresenterImpl = LoginPresenterImpl(this)
        initializeViews(fragView)
        return fragView
    }

    private fun initializeViews(view: View?) {
        if (view != null) {
            emailEdt = view.findViewById(R.id.loginEmailEdt)
            passwordEdt = view.findViewById(R.id.loginPasswordEdt)
            signUpBtn = view.findViewById(R.id.loginSignUpButton)
            loginBtn = view.findViewById(R.id.loginButton)
        }

        signUpBtn.setOnClickListener(this)
        loginBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            signUpBtn.id -> {
                loadSignUpFragment()
            }
            loginBtn.id -> {
                submitUserData()
            }
        }
    }

    override fun loadSignUpFragment() {
        fragmentManager!!.beginTransaction()
            .replace(R.id.authFragmentsContainer, SignUpFragment.newInstance(), "SIGN_UP_FRAGMENT")
            .addToBackStack("LOGIN_FRAGMENT")
            .commit()
    }

    override fun submitUserData() {
        loginPresenterImpl.validateData(emailEdt.text.toString(), passwordEdt.text.toString())
    }

    override fun onValidationError(value: String) {
        Toast.makeText(context, "$value was not provided.", Toast.LENGTH_SHORT).show()
    }

    override fun onSubmitError() {
        Toast.makeText(context, "Login failed, please check your network or credentials.", Toast.LENGTH_SHORT)
            .show()
    }

    override fun onSubmitSuccess(token: String) {
        saveTokenToSharedPreferences(token)
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
        activity!!.finish()
    }

    private fun saveTokenToSharedPreferences(token: String) {
        val sharedPreferences = context!!.getSharedPreferences(Constants.SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(Constants.SHARED_PREFERENCES_TOKEN_KEY, token)
        editor.apply()
    }

    companion object {
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }
}
