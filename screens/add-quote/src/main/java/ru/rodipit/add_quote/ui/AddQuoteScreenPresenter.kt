package ru.rodipit.add_quote.ui

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.rodipit.add_quote.viewmodel.AddQuoteScreenViewModel

internal interface AddQuoteScreenPresenter {

    val state: StateFlow<AddQuoteScreenUiState>

    class Impl(
        viewModel: AddQuoteScreenViewModel,
    ): AddQuoteScreenPresenter {

        override val state = viewModel.uiState

    }

    class Preview(
        uiState: AddQuoteScreenUiState,
    ): AddQuoteScreenPresenter {
        override val state = MutableStateFlow(uiState)
    }


}