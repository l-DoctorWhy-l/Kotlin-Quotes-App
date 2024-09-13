package com.example.image.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import ru.rodipit.design.theme.AppTheme

@Composable
internal fun ImageScreenContent(
    presenter: ImageScreenPresenter,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        SubcomposeAsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://zenquotes.io/api/image")
                .build(),
            contentDescription = null,
        )

    }
}


@Preview
@Composable
private fun ImageScreenContentPreview() {

    AppTheme {
        ImageScreenContent(
            presenter = ImageScreenPresenter.Preview()
        )
    }
}