package ru.rodipit.settings.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.rodipit.settings.SettingsScreenViewModel

val settingsScreenModule = module {
    viewModel {
        SettingsScreenViewModel(
            navigator = get(),
            themeManager = get(),
        )
    }
}