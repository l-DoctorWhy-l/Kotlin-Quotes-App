package com.example.navigation.api

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class AppNavigatorImpl : AppNavigator {

    private val _navigationEvent = MutableStateFlow<NavigationEvent?>(null)

    override val navigationEvent: StateFlow<NavigationEvent?> = _navigationEvent.asStateFlow()

    override fun popUpTo(route: Route) {
        _navigationEvent.value = NavigationEvent.PopUpTo(route)
    }

    override fun popBackStack() {
        _navigationEvent.value = NavigationEvent.PopBackStack

    }

    override fun navigateTo(route: Route) {
        _navigationEvent.value = NavigationEvent.NavigateTo(route)
    }

    override fun resetNavigation() {
        _navigationEvent.value = null
    }
}