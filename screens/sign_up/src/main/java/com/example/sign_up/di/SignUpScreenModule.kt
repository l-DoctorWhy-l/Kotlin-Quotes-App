package com.example.sign_up.di

import com.example.sign_up.viewmodel.SignUpScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val signUpScreenModule = module {

    viewModel<SignUpScreenViewModel> {
        SignUpScreenViewModel(
            appNavigator = get(),
            repository = get(),
            stringProvider = get(),
        )
    }

}