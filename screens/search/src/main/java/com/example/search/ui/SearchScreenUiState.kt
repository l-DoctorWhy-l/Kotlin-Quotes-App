package com.example.search.ui

import androidx.compose.runtime.Stable
import ru.rodipit.design.components.model.QuoteItemUiData

@Stable
internal interface SearchScreenUiState {

    val query: String

    data class Loading(
        override val query: String,
        val isLoading: Boolean,
    ): SearchScreenUiState

    data class Content(
        override val query: String,
        val searchResult: List<QuoteItemUiData>,
    ): SearchScreenUiState

}