package com.example.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigation.api.AppNavigator
import com.example.navigation.api.Route
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal class SplashScreenViewModel(
    private val navigator: AppNavigator,
): ViewModel() {

    fun loadAccount() {
        viewModelScope.launch {
            delay(2000)
            navigator.popUpTo(Route.Auth)
        }
    }

}