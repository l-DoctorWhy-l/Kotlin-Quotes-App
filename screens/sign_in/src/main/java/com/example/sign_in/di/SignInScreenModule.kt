package com.example.sign_in.di

import com.example.sign_in.viewmodel.SignInScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val signInScreenModule = module {

    viewModel<SignInScreenViewModel> {
        SignInScreenViewModel(
            navigator = get()
        )
    }

}