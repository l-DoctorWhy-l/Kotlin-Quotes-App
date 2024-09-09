package ru.rodipit.favourites.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import ru.rodipit.database.api.QuotesDbManager
import ru.rodipit.design.components.model.QuoteItemUiData
import ru.rodipit.favourites.ui.UiState
import ru.rodipit.models.QuoteModel

internal class FavouritesScreenViewModel: ViewModel() {

    private val _state: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)

    private val databaseManager: QuotesDbManager by inject(QuotesDbManager::class.java)

    val state get() =
        _state.asStateFlow()

    init {
        viewModelScope.launch {
            databaseManager.getAll().collect { quotes ->
                _state.value = UiState.Success(quotes = quotes.map { it.toQuoteItemUiData() })
            }
        }

        viewModelScope.launch {
            databaseManager.insertQuote(quoteModel = QuoteModel("AVCDEFG", "12345"))
        }
    }

}

private fun QuoteModel.toQuoteItemUiData(): QuoteItemUiData {
    return QuoteItemUiData(
        content = content,
        author = author,
    )
}