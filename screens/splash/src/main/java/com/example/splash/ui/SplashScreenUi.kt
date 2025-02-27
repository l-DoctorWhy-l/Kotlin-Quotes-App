package com.example.splash.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.rodipit.design.R
import ru.rodipit.design.theme.AppTheme


@Composable
internal fun SplashScreenUi(
    onLoad: () -> Unit,
    modifier: Modifier = Modifier,
) {

    LaunchedEffect(Unit) {
        onLoad()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(R.string.splash_screen_title_text),
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.primary,

        )
    }
}


@Preview
@Composable
private fun SplashScreenUiPreview(
) {
    AppTheme {
        SplashScreenUi(
            onLoad = { },
        )
    }
}