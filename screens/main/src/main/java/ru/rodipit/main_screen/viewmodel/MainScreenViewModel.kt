package ru.rodipit.main_screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private var loadingJob: Job? = null

    val state get() =
        _state.asStateFlow()

    val isRefreshingState get() =
        _isRefreshingState.asStateFlow()

    init {
        loadQuotes()
    }

    fun likeQuote(pos: Int) {
        viewModelScope.launch {
            quotes.getOrNull(pos)?.let {
                quotesDb.insertQuote(it)
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
            _state.value = UiState.Success(quotes = result.map { it.toQuoteItemUiData() })
            _isRefreshingState.value = false
        }
    }

}

private fun QuoteModel.toQuoteItemUiData(): QuoteItemUiData {
    return QuoteItemUiData(
        content = content,
        author = author,
    )
}