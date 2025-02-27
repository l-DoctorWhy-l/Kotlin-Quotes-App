package ru.rodipit.main_screen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.rodipit.design.components.QuoteItem
import ru.rodipit.design.components.QuoteItemShimmer
import ru.rodipit.design.components.TopAppBar
import ru.rodipit.design.theme.AppTheme
import ru.rodipit.main_screen.R

@Composable
internal fun MainScreenUi(
    presenter: MainScreenPresenter,
    modifier: Modifier = Modifier,
) {
    Column {
        TopAppBar(
            title = stringResource(R.string.main_screen_top_bar_title),
            trailingButton = {
                IconButton(
                    onClick = { }
                ) {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        painter = painterResource(ru.rodipit.design.R.drawable.baseline_person_24),
                        contentDescription = null,
                    )
                }
            }
        )
        when(val uiState = presenter.state.collectAsState().value) {
            is UiState.Loading -> MainScreenLoading(isLoading = uiState.isLoading, modifier = modifier)
            is UiState.Success -> MainScreenSuccess(uiData = uiState, presenter = presenter, modifier = modifier)
        }
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
                .background(MaterialTheme.colorScheme.background)
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
    isLoading: Boolean,
    modifier: Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        userScrollEnabled = false,
    ) {
        items(3) {
            QuoteItemShimmer(isLoading = isLoading)
        }
    }
}

@Preview
@Composable
private fun MainScreenContentPreview() {
    AppTheme {
        MainScreenUi(
            presenter = MainScreenPresenter.Preview(UiState.Success.forPreview())
        )
    }
}

@Preview
@Composable
private fun MainScreenLoadingPreview() {
    AppTheme {
        MainScreenUi(
            presenter = MainScreenPresenter.Preview(UiState.Loading(true))
        )
    }
}
