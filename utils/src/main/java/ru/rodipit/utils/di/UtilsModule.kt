package ru.rodipit.utils.di

import org.koin.dsl.module
import ru.rodipit.utils.DataStoreManager
import ru.rodipit.utils.StringResourceProvider
import ru.rodipit.utils.ThemeManager

val utilsModule = module {

    single {
        DataStoreManager(
            context = get(),
        )
    }

    single {
        ThemeManager(
            context = get(),
        )
    }

    single {
        StringResourceProvider(
            context = get(),
        )
    }

}