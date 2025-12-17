package com.appdev.feedback.ui.screens.login.event

sealed class UIEventLogin {
    data class ShowToast(val message: String) : UIEventLogin()
    //object LoginSuccess : UIEventLogin()
    data class Navigate(val screen: String) : UIEventLogin()
}