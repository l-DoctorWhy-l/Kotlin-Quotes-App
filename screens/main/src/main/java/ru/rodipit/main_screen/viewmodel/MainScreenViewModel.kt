package ru.rodipit.main_screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigation.api.AppNavigator
import com.example.navigation.api.Route
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
import ru.rodipit.quotes_api.api.ConvertedResult
import ru.rodipit.quotes_api.api.QuotesRepository

internal class MainScreenViewModel : ViewModel() {

    private val _state: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading(true))

    private val _isRefreshingState: MutableStateFlow<Boolean> = MutableStateFlow(false)

    private var quotes: List<QuoteModel> = emptyList()

    private val quotesRepo: QuotesRepository by inject(QuotesRepository::class.java)
    private val quotesDb: QuotesDbManager by inject(QuotesDbManager::class.java)
    private val navigator: AppNavigator by inject(AppNavigator::class.java)

    private var likedQuotes = quotesDb.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = emptyList()
        )


    private var loadingJob: Job? = null

    val state
        get() =
            _state.asStateFlow()

    val isRefreshingState
        get() =
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
                if (likedQuotes.value.contains(it)) quotesDb.deleteQuote(it) else quotesDb.insertQuote(
                    it
                )
            }
        }
    }

    fun onNavigateToQuote(pos: Int) {
        navigator.navigateTo(Route.QuoteDetails(id = quotes[pos].id))
    }


    fun loadQuotes() {
        loadingJob?.cancel()
        loadingJob = null
        loadingJob = viewModelScope.launch {
            _isRefreshingState.value = true
            quotes = emptyList()
            when (val result = quotesRepo.feed()) {
                is ConvertedResult.Success -> {
                    quotes = result.data.map { it }
                    _state.value = UiState.Success(quotes = quotes.map { it.toQuoteItemUiData() })
                }

                is ConvertedResult.Error -> {
                    _state.value = UiState.Loading(false)
                }
            }

            _isRefreshingState.value = false
        }
    }

}

private fun QuoteModel.toQuoteItemUiData(isLiked: Boolean = false): QuoteItemUiData {
    return QuoteItemUiData(
        id = id,
        content = content,
        film = film,
        isLiked = isLiked,
    )
}