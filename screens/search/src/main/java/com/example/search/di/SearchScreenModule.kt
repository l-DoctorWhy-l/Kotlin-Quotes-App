package com.example.search.di

import com.example.search.viewmodel.SearchScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchScreenModule = module {

    viewModel {
        SearchScreenViewModel()
    }
}