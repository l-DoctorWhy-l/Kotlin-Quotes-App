package com.example.search.di

import com.example.search.viewmodel.SearchScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val searchScreenModule = module {

    viewModel {
        SearchScreenViewModel(
            repository = get(),
            dataStoreManager = get(),
            navigator = get(),
        )
    }
}