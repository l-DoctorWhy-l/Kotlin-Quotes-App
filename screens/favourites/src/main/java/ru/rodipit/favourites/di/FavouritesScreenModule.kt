package ru.rodipit.favourites.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.rodipit.favourites.viewmodel.FavouritesScreenViewModel

val favouritesScreenModule = module {

    viewModel { FavouritesScreenViewModel() }

}