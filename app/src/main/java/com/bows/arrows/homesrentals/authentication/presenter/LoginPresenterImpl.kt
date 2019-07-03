package com.bows.arrows.homesrentals.authentication.presenter

import android.content.Context
import android.widget.EditText
import com.bows.arrows.homesrentals.authentication.model.User
import com.bows.arrows.homesrentals.authentication.view.ILoginView
import com.bows.arrows.homesrentals.utilities.Constants

class LoginPresenterImpl(private val view: ILoginView) : ILoginPresenter {

    private var context: Context? = null

    init {
        context = view.getFragContext()
    }

    override fun validateData(email: String, password: String) {
        when {
            email.isBlank() || email.isEmpty() -> {
                view.onValidationError("Email")
            }
            password.isBlank() || password.isEmpty() -> {
                view.onValidationError("Password")
            }
            else -> {
                val user = User("", email, "", "", password, true, "")
                passDataToModel(user)
            }
        }
    }

    fun validationError(editText: EditText, error: String) {
        editText.error = "$error was not provided"
    }

    private fun passDataToModel(user: User) {
        user.getLoginPresenterObj(this)
        user.loginUser(user)
    }

    override fun onLoginError() {
        view.onError("Login Failed, Unknown User")
    }

    override fun onLoginSuccess(token: String) {
        view.onSuccess("Login Successful")
        saveAuthToken(token)
    }

    private fun saveAuthToken(token: String) {
        val sharedPreferences = context!!.getSharedPreferences(Constants.SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(Constants.SHARED_PREFERENCES_TOKEN_KEY, token)
        editor.apply()
    }
}