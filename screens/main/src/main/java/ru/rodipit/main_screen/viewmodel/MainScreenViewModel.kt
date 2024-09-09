package ru.rodipit.main_screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import ru.rodipit.design.components.model.QuoteItemUiData
import ru.rodipit.main_screen.ui.UiState
import ru.rodipit.models.QuoteModel
import ru.rodipit.quotes_api.api.QuotesRepository

internal class MainScreenViewModel: ViewModel() {

    private val _state: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)


    private val quotesRepo: QuotesRepository by inject(QuotesRepository::class.java)

    private var loadingJob: Job? = null

    val state get() =
        _state.asStateFlow()

    init {
        loadQuotes()
    }


    private fun loadQuotes() {
        loadingJob?.cancel()
        loadingJob = null

        loadingJob = viewModelScope.launch {
            val result = quotesRepo.loadQuotes()
            _state.value = UiState.Success(quotes = result.map { it.toQuoteItemUiData() })
        }
    }

}

private fun QuoteModel.toQuoteItemUiData(): QuoteItemUiData {
    return QuoteItemUiData(
        content = content,
        author = author,
    )
}