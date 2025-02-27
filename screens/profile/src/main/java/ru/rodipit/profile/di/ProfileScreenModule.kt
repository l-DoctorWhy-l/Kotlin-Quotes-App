package ru.rodipit.profile.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.rodipit.profile.ProfileScreenViewModel

val profileScreenModule = module {
    viewModel {
        ProfileScreenViewModel()
    }
}