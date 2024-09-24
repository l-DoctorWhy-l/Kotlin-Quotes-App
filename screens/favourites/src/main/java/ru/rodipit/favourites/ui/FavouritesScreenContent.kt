package ru.rodipit.favourites.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.rodipit.design.components.QuoteItem
import ru.rodipit.design.theme.AppTheme

@Composable
internal fun FavouritesScreenContent(
    presenter: FavouritesScreenPresenter,
    modifier: Modifier = Modifier,
) {
    when(val uiState = presenter.state.collectAsState().value) {
        is UiState.Loading -> FavouritesScreenLoading(uiData = uiState, modifier = modifier)
        is UiState.Success -> FavouritesScreenSuccess(uiData = uiState, presenter = presenter, modifier = modifier)
    }
}

@Composable
private fun FavouritesScreenSuccess(
    uiData: UiState.Success,
    presenter: FavouritesScreenPresenter,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = presenter::doWork,
        ) {
            Text("Do work!")
        }

        Spacer(Modifier.height(10.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(uiData.quotes) { index, item ->
                QuoteItem(
                    uiData = item,
                    onClick = { presenter.unlikeQuote(pos = index) }
                )
            }
        }
    }
}


@Composable
private fun FavouritesScreenLoading(
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
        FavouritesScreenContent(
            presenter = FavouritesScreenPresenter.Preview(UiState.Success.forPreview())
        )
    }
}
