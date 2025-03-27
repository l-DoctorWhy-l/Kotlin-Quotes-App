package com.example.search.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastCbrt
import ru.rodipit.design.theme.AppTheme
import ru.rodipit.design.R
import ru.rodipit.design.components.QuoteItem
import ru.rodipit.design.components.QuoteItemShimmer
import ru.rodipit.design.components.TopAppBar
import ru.rodipit.design.components.model.QuoteItemUiData

@Composable
internal fun SearchScreenUi(
    presenter: SearchScreenPresenter,
    modifier: Modifier = Modifier,
) {

    val uiState = presenter.state.collectAsState().value
    val query = presenter.query.collectAsState().value

    Column(
        modifier = modifier
    ) {
        TopAppBar(
            title = stringResource(com.example.image.R.string.search_screen_top_bar_title),
            trailingButton = {
                IconButton(
                    onClick = presenter::onNavigateToProfileScreen,
                ) {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        painter = painterResource(R.drawable.baseline_person_24),
                        contentDescription = null,
                    )
                }
            }
        )
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                label = {
                    Text(
                        text = stringResource(R.string.search_field_label_text)
                    )
                },
                shape = RoundedCornerShape(24.dp),
                trailingIcon = {
                    if (query.isNotEmpty()) {
                        IconButton(
                            onClick = presenter::onClearSearchField,
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.baseline_clear_24),
                                tint = MaterialTheme.colorScheme.primary,
                                contentDescription = null,
                            )
                        }
                    }
                },
                value = query,
                onValueChange = presenter::onQueryChanged,
            )

            Spacer(Modifier.height(8.dp))

            when(uiState) {
                is SearchScreenUiState.Content -> {
                    SearchScreenContent(
                        uiData = uiState,
                        presenter = presenter,
                    )
                }
                is SearchScreenUiState.Loading -> {
                    SearchScreenLoading()
                }
                is SearchScreenUiState.Error -> {
                    SearchScreenError(
                        presenter = presenter,
                        uiData = uiState,
                    )
                }
                is SearchScreenUiState.History -> {
                    SearchScreenHistory(
                        uiData = uiState,
                        presenter = presenter,
                    )
                }
            }
        }
    }
}


@Composable
private fun SearchScreenContent(
    uiData: SearchScreenUiState.Content,
    presenter: SearchScreenPresenter,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        when(uiData.searchResult.isEmpty()) {
            true -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(vertical = 16.dp)
                ) {
                    Text(
                        text = stringResource(R.string.search_result_empty_text)
                    )
                    Text(
                        text = stringResource(R.string.search_result_empty_description)
                    )
                }
            }

            false -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiData.searchResult.size) { index ->
                        QuoteItem(
                            uiData = uiData.searchResult[index],
                            onClick = { presenter.onSearchItemClick(index) },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchScreenHistory(
    uiData: SearchScreenUiState.History,
    presenter: SearchScreenPresenter,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        when(uiData.items.isEmpty()) {
            true -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(vertical = 16.dp)
                ) {
                    Text(
                        text = stringResource(R.string.empty_search_history_text)
                    )
                    Text(
                        text = stringResource(R.string.empty_search_history_description)
                    )
                }
            }

            false -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 8.dp),
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.headlineLarge,
                        text = stringResource(R.string.search_history_header)
                    )
                    Spacer(Modifier.height(8.dp))
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        items(uiData.items.size) { index ->
                            QuoteItem(
                                uiData = uiData.items[index],
                                onClick = { presenter.onSearchHistoryItemClick(index) }
                            )
                        }

                        item {
                            Button(
                                onClick = presenter::clearSearchHistory,
                            ) {
                                Text(
                                    text = stringResource(R.string.clear_search_history_button_text),
                                    color = MaterialTheme.colorScheme.onPrimary,
                                )
                            }
                        }
                    }
                }

            }
        }
    }
}

@Composable
private fun SearchScreenLoading(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        LazyColumn(
            userScrollEnabled = false,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(3) { _ ->
                QuoteItemShimmer(isLoading = true)
            }
        }
    }
}

@Composable
private fun SearchScreenError(
    presenter: SearchScreenPresenter,
    uiData: SearchScreenUiState.Error,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.surfaceContainer,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(4.dp),
                text = uiData.message ?: stringResource(R.string.search_result_text_error),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.error,
            )
            Spacer(Modifier.height(8.dp))
            IconButton(
                onClick = presenter::onRefresh,
                modifier = Modifier.background(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(12.dp),
                )
            ) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    tint = MaterialTheme.colorScheme.primary,
                    painter = painterResource(R.drawable.baseline_refresh_24),
                    contentDescription = null,
                )
            }
        }
    }
}


@Preview
@Composable
private fun SearchScreenContentPreview() {
    AppTheme {
        SearchScreenUi(
            presenter = SearchScreenPresenter.Preview(
                uiState = SearchScreenUiState.Content(
                    searchResult = emptyList(),
                )
            )
        )
    }
}

@Preview
@Composable
private fun SearchScreenLoadingPreview() {
    AppTheme {
        SearchScreenUi(
            presenter = SearchScreenPresenter.Preview(
                uiState = SearchScreenUiState.Loading
            )
        )
    }
}

@Preview
@Composable
private fun SearchScreenErrorPreview() {
    AppTheme {
        SearchScreenUi(
            presenter = SearchScreenPresenter.Preview(
                uiState = SearchScreenUiState.Error()
            )
        )
    }
}

@Preview
@Composable
private fun SearchScreenHistoryPreview() {
    AppTheme {
        SearchScreenUi(
            presenter = SearchScreenPresenter.Preview(
                uiState = SearchScreenUiState.History(
                    listOf(
                        QuoteItemUiData.forPreview(),
                        QuoteItemUiData.forPreview(),
                        QuoteItemUiData.forPreview(),
                    )
                )
            )
        )
    }
}