package com.example.search.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigation.api.AppNavigator
import com.example.navigation.api.Route
import com.example.search.ui.SearchScreenUiState
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import org.koin.java.KoinJavaComponent.inject
import ru.rodipit.design.components.model.QuoteItemUiData
import ru.rodipit.models.QuoteModel
import ru.rodipit.quotes_api.api.ConvertedResult
import ru.rodipit.quotes_api.api.MyError
import ru.rodipit.quotes_api.api.QuotesRepository
import ru.rodipit.utils.DataStoreManager
import kotlin.random.Random


internal class SearchScreenViewModel(
    private val repository: QuotesRepository,
    private val dataStoreManager: DataStoreManager,
    private val navigator: AppNavigator,
) : ViewModel() {

    private val _state: MutableStateFlow<SearchScreenUiState> = MutableStateFlow(SearchScreenUiState.Loading)
    val state = _state.asStateFlow()

    private val searchHistory = toHistoryItems(dataStoreManager.getData(SEARCH_HISTORY_KEY))
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = emptyList(),
        )

    private val _query: MutableStateFlow<String> = MutableStateFlow("")
    val query = _query.asStateFlow()

    private var loadingJob: Job? = null


    init {
        viewModelScope.launch {
            query.collect {
                if (it.isNotEmpty()) {
                    searchQuotes(query = query.value)
                } else {
                    _state.update {
                        SearchScreenUiState.History(items = searchHistory.value)
                    }
                }
            }
        }

        viewModelScope.launch {
            searchHistory.collect {
                if (state.value is SearchScreenUiState.History) {
                    _state.update {
                        SearchScreenUiState.History(items = searchHistory.value)
                    }
                }
            }
        }
    }


    fun onQueryChanged(query: String) {
        _query.update { query }
    }

    fun clearSearchField() {
        _query.update { "" }
    }

    fun clearRefresh() {
        searchQuotes(query = query.value)
    }

    private fun searchQuotes(query: String) {
        loadingJob?.cancel()
        loadingJob = viewModelScope.launch {
            if ((_state.value as? SearchScreenUiState.Content)?.searchResult?.isNotEmpty() != true) {
                _state.update {
                    SearchScreenUiState.Loading
                }
            }
            delay(500)
            when(val result = repository.search(query)) {
                is ConvertedResult.Success -> {
                    _state.update {
                        SearchScreenUiState.Content(
                            searchResult = result.data.map { it.toUi() },
                        )
                    }
                }

                is ConvertedResult.Error -> {
                    _state.update {
                        SearchScreenUiState.Error(
                            message = result.error.toMessage(),
                        )
                    }
                }
            }

        }
    }

    fun onSearchItemClick(index: Int) {
        (state.value as? SearchScreenUiState.Content)?.let { state ->
            val clickedItem = state.searchResult.getOrNull(index) ?: return
            addItemToSearchHistory(clickedItem)
            navigateToQuoteDetails(clickedItem.id)
        }
    }

    fun onSearchHistoryItemClick(index: Int) {
        (state.value as? SearchScreenUiState.History)?.let {
            val clickedItem = searchHistory.value.getOrNull(index) ?: return
            navigateToQuoteDetails(clickedItem.id)
        }
    }


    fun clearSearchHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreManager.clearData(SEARCH_HISTORY_KEY)
        }
    }

    fun onNavigateToProfileScreen() {
        navigator.navigateTo(Route.Profile)
    }

    private fun navigateToQuoteDetails(id: String) {
        navigator.navigateTo(Route.QuoteDetails(id))
    }

    private fun addItemToSearchHistory(item: QuoteItemUiData) {

        viewModelScope.launch(Dispatchers.IO) {
            val newSearchHistory = searchHistory.value.toMutableList().apply {
                if (size >= 10) removeAt(lastIndex)
                add(0, item)
            }

            dataStoreManager.saveData(
                key = SEARCH_HISTORY_KEY,
                data = Gson().toJson(newSearchHistory)
            )
        }
    }


    private fun toHistoryItems(historyItemsJson: Flow<String?>): Flow<List<QuoteItemUiData>> {
        return historyItemsJson.mapNotNull { items ->
            items?.let {
                val type = object : TypeToken<List<QuoteItemUiData>>() {}.type
                Gson().fromJson(items, type)
            } ?: emptyList()
        }

    }

    companion object {
        private const val SEARCH_HISTORY_KEY = "SEARCH_HISTORY_KEY"
    }

}

internal fun QuoteModel.toUi(): QuoteItemUiData {
    return QuoteItemUiData(
        id = this.id,
        content = this.content,
        film = this.film,
    )
}

internal fun MyError.toMessage(): String {
    return when(this) {
        is MyError.NetworkError -> "Network Error"
        is MyError.ServerError -> "Server Error"
        is MyError.UnknownError -> "Unknown Error"
        is MyError.InternetError -> "Check your internet connection"
        is MyError.NotFoundError -> "Not Found"
    }
}
