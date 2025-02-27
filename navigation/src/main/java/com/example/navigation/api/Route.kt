package com.example.navigation.api


sealed class Route(val value: String) {
    data object Home : Route("home")
    data object Favourites : Route("favourites")
    data object Search : Route("search")
    data object Splash : Route("splash")
    data object Main : Route("main")
    data object Auth : Route("auth")
    data object SignIn : Route("sign_in")
    data object SignUp : Route("sign_up")
    data object Profile : Route("profile")
}