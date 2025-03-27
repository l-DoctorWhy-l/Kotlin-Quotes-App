package com.example.search.ui

import androidx.compose.runtime.Stable
import ru.rodipit.design.components.model.QuoteItemUiData

@Stable
internal interface SearchScreenUiState {

    data object Loading: SearchScreenUiState

    data class Error(
        val message: String? = null,
    ): SearchScreenUiState

    data class Content(
        val searchResult: List<QuoteItemUiData>,
    ): SearchScreenUiState

    data class History(
        val items: List<QuoteItemUiData>,
    ): SearchScreenUiState

}