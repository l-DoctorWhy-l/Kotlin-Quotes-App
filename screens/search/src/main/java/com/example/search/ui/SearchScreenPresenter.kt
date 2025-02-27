package com.example.search.ui

import com.example.search.viewmodel.SearchScreenViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal interface SearchScreenPresenter {

    val state: StateFlow<SearchScreenUiState>

    class Impl(
        viewModel: SearchScreenViewModel,
    ): SearchScreenPresenter {

        override val state = viewModel.state

    }

    class Preview(
        uiState: SearchScreenUiState,
    ): SearchScreenPresenter {
        override val state = MutableStateFlow(uiState)
    }


}