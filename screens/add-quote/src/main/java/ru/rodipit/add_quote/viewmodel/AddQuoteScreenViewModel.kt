package ru.rodipit.add_quote.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.rodipit.add_quote.ui.AddQuoteScreenUiState

internal class AddQuoteScreenViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(AddQuoteScreenUiState())
    val uiState = _uiState.asStateFlow()


}