package com.example.image.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.rodipit.design.components.QuoteItem
import ru.rodipit.design.theme.AppTheme

@Composable
internal fun ImageScreenContent(
    presenter: ImageScreenPresenter,
    modifier: Modifier = Modifier,
) {
    val imageUrl by presenter.state.collectAsState()

    Column(modifier = modifier.fillMaxSize()) {

    }
}


@Preview
@Composable
private fun MainScreenContentPreview() {

    AppTheme {
        ImageScreenContent(
            presenter = ImageScreenPresenter.Preview()
        )
    }
}