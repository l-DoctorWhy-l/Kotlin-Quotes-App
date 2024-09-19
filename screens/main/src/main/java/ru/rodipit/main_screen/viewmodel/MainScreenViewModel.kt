package ru.rodipit.main_screen.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import ru.rodipit.database.api.QuotesDbManager
import ru.rodipit.design.components.model.QuoteItemUiData
import ru.rodipit.main_screen.ui.UiState
import ru.rodipit.models.QuoteModel
import ru.rodipit.quotes_api.api.QuotesRepository

internal class MainScreenViewModel: ViewModel() {

    private val _state: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)

    private val _isRefreshingState: MutableStateFlow<Boolean> = MutableStateFlow(false)

    private var quotes: List<QuoteModel> = emptyList()

    private val quotesRepo: QuotesRepository by inject(QuotesRepository::class.java)
    private val quotesDb: QuotesDbManager by inject(QuotesDbManager::class.java)

    private var likedQuotes = quotesDb.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = emptyList()
        )


    private var loadingJob: Job? = null

    val state get() =
        _state.asStateFlow()

    val isRefreshingState get() =
        _isRefreshingState.asStateFlow()

    init {
        loadQuotes()

        viewModelScope.launch {
            likedQuotes.collect { likedQuotes ->
                _state.value = UiState.Success(quotes = quotes.map {
                    it.toQuoteItemUiData(likedQuotes.contains(it))
                })
            }
        }
    }

    fun likeQuote(pos: Int) {
        viewModelScope.launch {
            quotes.getOrNull(pos)?.let {
                if (likedQuotes.value.contains(it)) quotesDb.deleteQuote(it) else quotesDb.insertQuote(it)
            }
        }
    }


    internal fun loadQuotes() {
        loadingJob?.cancel()
        loadingJob = null

        loadingJob = viewModelScope.launch {
            _isRefreshingState.value = true
            val result = quotesRepo.loadQuotes()
            quotes = result
            _state.value = UiState.Success(quotes = result.map {
                it.toQuoteItemUiData()
            })
            _isRefreshingState.value = false
        }
    }

}

private fun QuoteModel.toQuoteItemUiData(isLiked: Boolean = false): QuoteItemUiData {
    return QuoteItemUiData(
        content = content,
        author = author,
        isLiked = isLiked,
    )
}