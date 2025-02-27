package com.example.sign_up.viewmodel

import androidx.lifecycle.ViewModel
import com.example.navigation.api.AppNavigator

internal class SignUpScreenViewModel(
    private val appNavigator: AppNavigator,
): ViewModel() {


    fun backToSignIn() {
        appNavigator.popBackStack()
    }
}