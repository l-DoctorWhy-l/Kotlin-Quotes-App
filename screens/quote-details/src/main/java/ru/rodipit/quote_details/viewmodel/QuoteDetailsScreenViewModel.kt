package ru.rodipit.quote_details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.navigation.api.AppNavigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import ru.rodipit.database.api.QuotesDbManager
import ru.rodipit.models.QuoteModel
import ru.rodipit.quote_details.ui.QuoteDetailsScreenUiState
import ru.rodipit.quotes_api.api.ConvertedResult
import ru.rodipit.quotes_api.api.QuotesRepository

internal class QuoteDetailsScreenViewModel(
    private val quoteId: String,
    private val repository: QuotesRepository,
    private val navigator: AppNavigator,
): ViewModel() {

    private val _uiState: MutableStateFlow<QuoteDetailsScreenUiState> =
        MutableStateFlow(QuoteDetailsScreenUiState.Loading(isLoading = true))
    val uiState = _uiState.asStateFlow()

    private val quotesDbManager: QuotesDbManager by inject(QuotesDbManager::class.java)

    private var likedQuotes = quotesDbManager.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = emptyList()
        )


    init {
        viewModelScope.launch {
            when(val result = repository.quote(quoteId)) {
                is ConvertedResult.Success -> {
                    _uiState.update {
                        QuoteDetailsScreenUiState.Content(
                            film = result.data.film ?: "",
                            content = result.data.content,
                            isLiked = likedQuotes.value.map { it.id }.contains(quoteId),
                        )
                    }
                }
                is ConvertedResult.Error -> {
                    _uiState.update {
                        QuoteDetailsScreenUiState.Content(
                            film = "",
                            content = "",
                            isLiked = false,
                        )
                    }
                }
            }
        }
    }

    fun onLikeClick() {
        viewModelScope.launch {
            (_uiState.value as? QuoteDetailsScreenUiState.Content)?.let { state ->
                if (state.isLiked) {
                    quotesDbManager.deleteById(quoteId)
                    _uiState.update { state.copy(isLiked = false) }
                } else {
                    quotesDbManager.insertQuote(
                        QuoteModel(
                            id = quoteId,
                            content = state.content,
                            film = state.film,
                        )
                    )
                    _uiState.update { state.copy(isLiked = true) }
                }
            }
        }

    }

    fun onBackButtonClicked() {
        navigator.popBackStack()
    }


}

internal class QuoteDetailsScreenViewModelFactory(
    private val quoteId: String,
) : ViewModelProvider.Factory {

    private val repository: QuotesRepository by inject(QuotesRepository::class.java)
    private val navigator: AppNavigator by inject(AppNavigator::class.java)

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuoteDetailsScreenViewModel::class.java)) {
            return QuoteDetailsScreenViewModel(
                quoteId = quoteId,
                repository = repository,
                navigator = navigator,
            ) as T
        }
        return super.create(modelClass)
    }
}