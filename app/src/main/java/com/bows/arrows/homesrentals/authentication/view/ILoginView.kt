package com.bows.arrows.homesrentals.authentication.view

import com.bows.arrows.homesrentals.authentication.base_contracts.IBaseAuthView

interface ILoginView : IBaseAuthView {
    fun signUp()
    fun login()
}