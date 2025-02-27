package com.example.navigation.api

import kotlinx.coroutines.flow.StateFlow

interface AppNavigator {

    val navigationEvent: StateFlow<NavigationEvent?>

    fun navigateTo(route: Route)

    fun popUpTo(route: Route)

    fun popBackStack()

    fun resetNavigation()
}

sealed interface NavigationEvent {
    data class PopUpTo(val route: Route): NavigationEvent
    data class NavigateTo(val route: Route): NavigationEvent
    data object PopBackStack: NavigationEvent
}