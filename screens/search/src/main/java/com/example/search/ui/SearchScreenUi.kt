package com.example.search.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import ru.rodipit.design.theme.AppTheme
import ru.rodipit.design.R
import ru.rodipit.design.components.QuoteItem
import ru.rodipit.design.components.TopAppBar

@Composable
internal fun SearchScreenUi(
    presenter: SearchScreenPresenter,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        TopAppBar(
            title = stringResource(com.example.image.R.string.search_screen_top_bar_title),
            trailingButton = {
                IconButton(
                    onClick = { }
                ) {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        painter = painterResource(R.drawable.baseline_person_24),
                        contentDescription = null,
                    )
                }
            }
        )
        when(val uiState = presenter.state.collectAsState().value) {
            is SearchScreenUiState.Content -> {
                SearchScreenContent(
                    uiData = uiState,
                )
            }
        }
    }
}


@Composable
private fun SearchScreenContent(
    uiData: SearchScreenUiState.Content,
    modifier: Modifier = Modifier,
) {
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
                Icon(
                    painter = painterResource(R.drawable.baseline_clear_24),
                    tint = MaterialTheme.colorScheme.onPrimary,
                    contentDescription = null,
                )
            },
            value = "",
            onValueChange = {},
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(uiData.searchResult.size) { index ->
                QuoteItem(uiData = uiData.searchResult[index])
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
                    query = "",
                    searchResult = emptyList(),
                )
            )
        )
    }
}