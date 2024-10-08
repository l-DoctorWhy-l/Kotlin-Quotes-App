﻿package ru.rodipit.main_screen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.rodipit.design.components.QuoteItem
import ru.rodipit.design.components.TopAppBar
import ru.rodipit.design.theme.AppTheme

@Composable
internal fun MainScreenContent(
    presenter: MainScreenPresenter,
    modifier: Modifier = Modifier,
) {
    when(val uiState = presenter.state.collectAsState().value) {
        is UiState.Loading -> MainScreenLoading(uiData = uiState, modifier = modifier)
        is UiState.Success -> MainScreenSuccess(uiData = uiState, presenter = presenter, modifier = modifier)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainScreenSuccess(
    uiData: UiState.Success,
    presenter: MainScreenPresenter,
    modifier: Modifier
) {
    val isRefreshing by presenter.refreshingState.collectAsState()
    val pullRefreshState = rememberPullToRefreshState()

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = presenter::refresh,
        state = pullRefreshState,
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp),
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(uiData.quotes) { index, item ->
                    QuoteItem(
                        uiData = item,
                        onClick = { presenter.likeQuote(index) }
                    )
                }
            }
        }
    }
}


@Composable
private fun MainScreenLoading(
    uiData: UiState.Loading,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(40.dp)
        )
    }
}

@Preview
@Composable
private fun MainScreenContentPreview() {
    AppTheme {
        MainScreenContent(
            presenter = MainScreenPresenter.Preview(UiState.Success.forPreview())
        )
    }
}
