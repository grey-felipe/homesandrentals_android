package com.bows.arrows.homesrentals.authentication.view

import com.bows.arrows.homesrentals.authentication.base_contracts.IBaseAuthView

interface ISignUpView : IBaseAuthView{
    fun submitUserData()
}