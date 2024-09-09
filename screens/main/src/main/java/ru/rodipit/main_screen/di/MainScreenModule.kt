package ru.rodipit.main_screen.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.rodipit.main_screen.viewmodel.MainScreenViewModel

val mainScreenModule = module {

    viewModel {
        MainScreenViewModel()
    }

}