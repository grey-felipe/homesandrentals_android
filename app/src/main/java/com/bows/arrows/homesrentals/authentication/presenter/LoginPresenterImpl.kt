package com.bows.arrows.homesrentals.authentication.presenter

import com.bows.arrows.homesrentals.authentication.model.User
import com.bows.arrows.homesrentals.authentication.view.ILoginView

class LoginPresenterImpl(private val loginView: ILoginView) : ILoginPresenter {

    override fun validateData(email: String, password: String) {
        when {
            email.isBlank() || email.isEmpty() -> {
                loginView.onValidationError("Email")
            }
            password.isBlank() || password.isEmpty() -> {
                loginView.onValidationError("Password")
            }
            else -> {
                val user = User("", email, "", "", password, true, "")
                passDataToModel(user)
            }
        }
    }

    private fun passDataToModel(user: User) {
        user.getLoginPresenterObj(this)
        user.loginUser(user)
    }

    override fun onLoginError() {
        loginView.onSubmitError()
    }

    override fun onLoginSuccess(token: String) {
        loginView.onSubmitSuccess(token)
    }
}