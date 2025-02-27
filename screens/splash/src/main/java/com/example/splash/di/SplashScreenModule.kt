package com.example.splash.di

import com.example.splash.viewmodel.SplashScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val splashScreenModule = module {
    viewModel<SplashScreenViewModel> {
        SplashScreenViewModel(
            navigator = get(),
        )
    }
}