package ru.rodipit.add_quote.ui

import androidx.compose.runtime.Stable
import ru.rodipit.design.components.model.QuoteItemUiData

@Stable
internal data class AddQuoteScreenUiState(
    val film: String = "",
    val quoteText: String = "",
)