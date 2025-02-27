package ru.rodipit.quote_details.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.rodipit.quote_details.viewmodel.QuoteDetailsScreenViewModel

val quoteDetailsScreenModule = module {

    viewModel {
        QuoteDetailsScreenViewModel()
    }

}