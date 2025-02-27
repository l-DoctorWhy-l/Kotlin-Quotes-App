package ru.rodipit.add_quote.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.rodipit.add_quote.viewmodel.AddQuoteScreenViewModel

val addQuoteScreenModule = module {
    viewModel {
        AddQuoteScreenViewModel()
    }
}