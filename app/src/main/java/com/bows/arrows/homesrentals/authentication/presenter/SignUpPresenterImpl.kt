package com.bows.arrows.homesrentals.authentication.presenter

import com.bows.arrows.homesrentals.authentication.model.User
import com.bows.arrows.homesrentals.authentication.view.ISignUpView

class SignUpPresenterImpl(private val signUpView: ISignUpView) : ISignUpPresenter {

    override fun validateData(username: String, email: String, phone: String, password: String) {
        when {
            username.isBlank() || username.isEmpty() -> {
                signUpView.onValidationError("Username")
            }
            email.isBlank() || email.isEmpty() -> {
                signUpView.onValidationError("Email")
            }
            phone.isBlank() || phone.isEmpty() -> {
                signUpView.onValidationError("Phone")
            }
            password.isBlank() || password.isEmpty() -> {
                signUpView.onValidationError("Password")
            }
            else -> {
                val user = User(username, email, phone, "", password, true, "")
                passUserDataToModel(user)
            }
        }
    }

    private fun passUserDataToModel(user: User) {
        user.getSignUpPresenterObj(this)
        user.pushUserDataToBackend(user)
    }

    override fun onPushError(message:String) {
        signUpView.onSubmitError(message)
    }

    override fun onPushSuccess() {
        signUpView.onSubmitSuccess()
    }
}