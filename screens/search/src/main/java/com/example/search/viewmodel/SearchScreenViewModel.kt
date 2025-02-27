package com.example.search.viewmodel

import androidx.lifecycle.ViewModel
import com.example.search.ui.SearchScreenUiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.java.KoinJavaComponent.inject
import ru.rodipit.quotes_api.api.QuotesRepository


internal class SearchScreenViewModel : ViewModel() {

    private val _state: MutableStateFlow<SearchScreenUiState> = MutableStateFlow(
        SearchScreenUiState.Content(
            query = "",
            searchResult = emptyList(),
        )
    )

    val state = _state.asStateFlow()

    private val quotesRepo: QuotesRepository by inject(QuotesRepository::class.java)

    private var loadingJob: Job? = null


}
