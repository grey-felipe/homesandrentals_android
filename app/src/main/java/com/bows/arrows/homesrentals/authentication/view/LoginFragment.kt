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

    private lateinit var presenter: LoginPresenterImpl

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        fragView = inflater.inflate(R.layout.fragment_login, container, false)
        presenter = LoginPresenterImpl(this)
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
                signUp()
            }
            loginBtn.id -> {
                login()
            }
        }
    }

    override fun signUp() {
        fragmentManager!!.beginTransaction()
            .replace(R.id.authFragmentsContainer, SignUpFragment.newInstance(), "SIGN_UP_FRAGMENT")
            .addToBackStack("LOGIN_FRAGMENT")
            .commit()
    }

    override fun login() {
        presenter.validateData(emailEdt.text.toString(), passwordEdt.text.toString())
    }

    override fun getFragContext(): Context {
        return context!!
    }

    override fun onValidationError(error: String) {
        when (error) {
            "Email" -> presenter.validationError(emailEdt, error)
            "Password" -> presenter.validationError(passwordEdt, error)
        }
    }

    override fun onError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess(message: String) {
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
        activity!!.finish()
    }

    companion object {
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }
}
