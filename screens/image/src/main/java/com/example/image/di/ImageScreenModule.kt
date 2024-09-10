package com.example.image.di

import com.example.image.viewmodel.ImageScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val imageScreenModule = module {

    viewModel {
        ImageScreenViewModel()
    }
}