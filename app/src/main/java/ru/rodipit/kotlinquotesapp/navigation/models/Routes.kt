package ru.rodipit.kotlinquotesapp.navigation.models

internal sealed class Routes(val route: String) {
    data object Home : Routes("home")
    data object Favourites : Routes("favourites")
    data object Image : Routes("image")
}