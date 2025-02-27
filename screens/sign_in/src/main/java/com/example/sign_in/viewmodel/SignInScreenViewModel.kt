package com.example.sign_in.viewmodel

import androidx.lifecycle.ViewModel
import com.example.navigation.api.AppNavigator
import com.example.navigation.api.Route

internal class SignInScreenViewModel(
    private val navigator: AppNavigator,
): ViewModel() {



    fun navigateToSignUp() {
        navigator.navigateTo(Route.SignUp)
    }

}