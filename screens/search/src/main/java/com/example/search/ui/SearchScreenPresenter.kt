package com.example.search.ui

import androidx.compose.runtime.Stable
import com.example.search.viewmodel.SearchScreenViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Stable
internal interface SearchScreenPresenter {

    val state: StateFlow<SearchScreenUiState>
    val query: StateFlow<String>

    fun onQueryChanged(query: String)
    fun onClearSearchField()
    fun onRefresh()

    fun onSearchItemClick(index: Int)
    fun onSearchHistoryItemClick(index: Int)
    fun clearSearchHistory()
    fun onNavigateToProfileScreen()

    class Impl(
        private val viewModel: SearchScreenViewModel,
    ): SearchScreenPresenter {

        override val state = viewModel.state
        override val query: StateFlow<String> = viewModel.query

        override fun onQueryChanged(query: String) {
            viewModel.onQueryChanged(query)
        }

        override fun onClearSearchField() {
            viewModel.clearSearchField()
        }

        override fun onRefresh() {
            viewModel.clearRefresh()
        }

        override fun onSearchItemClick(index: Int) {
            viewModel.onSearchItemClick(index)
        }

        override fun onSearchHistoryItemClick(index: Int) {
            viewModel.onSearchHistoryItemClick(index)
        }

        override fun clearSearchHistory() {
            viewModel.clearSearchHistory()
        }

        override fun onNavigateToProfileScreen() {
            viewModel.onNavigateToProfileScreen()
        }

    }

    class Preview(
        uiState: SearchScreenUiState,
    ): SearchScreenPresenter {
        override val state = MutableStateFlow(uiState)
        override val query: StateFlow<String> = MutableStateFlow("")
        override fun onQueryChanged(query: String) = Unit
        override fun onClearSearchField() = Unit
        override fun onRefresh() = Unit
        override fun onSearchItemClick(index: Int) = Unit

        override fun onSearchHistoryItemClick(index: Int) = Unit

        override fun clearSearchHistory() = Unit
        override fun onNavigateToProfileScreen() = Unit
    }


}