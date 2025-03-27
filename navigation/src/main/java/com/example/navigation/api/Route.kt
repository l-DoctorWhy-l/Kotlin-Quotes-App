package com.example.navigation.api

import kotlinx.serialization.Serializable


@Serializable
sealed class Route(val value: String) {
    @Serializable
    data object Home : Route("home")
    @Serializable
    data object Favourites : Route("favourites")
    @Serializable
    data object Search : Route("search")
    @Serializable
    data object Splash : Route("splash")
    @Serializable
    data object Main : Route("main")
    @Serializable
    data object Auth : Route("auth")
    @Serializable
    data object SignIn : Route("sign_in")
    @Serializable
    data object SignUp : Route("sign_up")
    @Serializable
    data object Profile : Route("profile")
    @Serializable
    data object Settings : Route("settings")
    @Serializable
    data class QuoteDetails(
        val id: String,
    ) : Route("quote_details")
}