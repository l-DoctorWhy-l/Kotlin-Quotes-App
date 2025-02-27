package ru.rodipit.quote_details.ui

import androidx.compose.runtime.Stable

@Stable
sealed interface QuoteDetailsScreenUiState {

    data class Loading(val isLoading: Boolean): QuoteDetailsScreenUiState
    data class Content(
        val film: String,
        val content: String,
        val isLiked: Boolean,
    ): QuoteDetailsScreenUiState

}