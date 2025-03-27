package ru.rodipit.quote_details.ui

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.rodipit.quote_details.viewmodel.QuoteDetailsScreenViewModel

internal interface QuoteDetailsScreenPresenter {

    val state: StateFlow<QuoteDetailsScreenUiState>

    fun onBackButtonClick()

    class Impl(
        private val viewModel: QuoteDetailsScreenViewModel,
    ): QuoteDetailsScreenPresenter {

        override val state = viewModel.uiState

        override fun onBackButtonClick() {
            viewModel.onBackButtonClicked()
        }

    }

    class Preview(
        uiState: QuoteDetailsScreenUiState,
    ): QuoteDetailsScreenPresenter {
        override val state = MutableStateFlow(uiState)
        override fun onBackButtonClick() = Unit

    }

}