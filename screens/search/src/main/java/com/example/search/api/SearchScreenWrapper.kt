package com.example.search.api

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.search.ui.SearchScreenUi
import com.example.search.ui.SearchScreenPresenter
import com.example.search.viewmodel.SearchScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreenWrapper(
    modifier: Modifier = Modifier,
) {
    val viewModel: SearchScreenViewModel = koinViewModel()
    val presenter = remember { SearchScreenPresenter.Impl(viewModel = viewModel) }

    Column(
        modifier = modifier,
    ) {
        SearchScreenUi(
            presenter = presenter,
        )
    }
}