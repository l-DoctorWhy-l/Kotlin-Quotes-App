package ru.rodipit.favourites.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import ru.rodipit.database.api.QuotesDbManager
import ru.rodipit.design.components.model.QuoteItemUiData
import ru.rodipit.favourites.ui.UiState
import ru.rodipit.models.QuoteModel

internal class FavouritesScreenViewModel : ViewModel() {

    private val _state: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)

    private val databaseManager: QuotesDbManager by inject(QuotesDbManager::class.java)

    private var likedQuotes = databaseManager.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = emptyList()
        )

    val state
        get() =
            _state.asStateFlow()

    init {
        viewModelScope.launch {
            likedQuotes.collect { quotes ->
                _state.value = UiState.Success(quotes = quotes.map {
                    it.toQuoteItemUiData(true)
                }
                )
            }
        }
    }

    fun unlikeQuote(pos: Int) {
        viewModelScope.launch {
            likedQuotes.value.getOrNull(pos)?.let {
                if (likedQuotes.value.contains(it)) databaseManager.deleteQuote(it)
            }
        }
    }

}

private fun QuoteModel.toQuoteItemUiData(isLiked: Boolean): QuoteItemUiData {
    return QuoteItemUiData(
        content = content,
        author = author,
        isLiked = isLiked,
    )
}