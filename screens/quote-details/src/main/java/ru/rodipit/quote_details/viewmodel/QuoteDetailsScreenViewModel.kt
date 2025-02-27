package ru.rodipit.quote_details.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.rodipit.quote_details.ui.QuoteDetailsScreenUiState

internal class QuoteDetailsScreenViewModel: ViewModel() {

    private val _uiState: MutableStateFlow<QuoteDetailsScreenUiState> =
        MutableStateFlow(QuoteDetailsScreenUiState.Loading(isLoading = true))
    val uiState = _uiState.asStateFlow()

}