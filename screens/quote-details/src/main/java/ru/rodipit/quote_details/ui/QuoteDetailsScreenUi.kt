package ru.rodipit.quote_details.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.rodipit.design.components.TopAppBar
import ru.rodipit.design.theme.AppTheme
import ru.rodipit.design.R
import ru.rodipit.design.components.BackButton

@Composable
internal fun QuoteDetailsScreenUi(
    presenter: QuoteDetailsScreenPresenter,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        TopAppBar(
            title = "",
            leadingButton = {
                BackButton(
                    onBackButtonClicked = presenter::onBackButtonClick,
                )
            }
        )
        when(val uiState = presenter.state.collectAsState().value) {
            is QuoteDetailsScreenUiState.Loading -> {
                QuoteDetailsScreenLoading()
            }
            is QuoteDetailsScreenUiState.Content -> {
                QuoteDetailsScreenContent(
                    uiData = uiState,
                    onLikeClick = presenter::onLikeClick,
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
private fun QuoteDetailsScreenContent(
    uiData: QuoteDetailsScreenUiState.Content,
    onLikeClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(
                top = 32.dp,
                bottom = 16.dp,
                start = 16.dp,
                end = 16.dp,
            ),
    ) {
        Row(
            Modifier.fillMaxWidth(),
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleLarge,
                text = uiData.film,
            )
            IconButton(
                onClick = onLikeClick,
            ) {
                if (uiData.isLiked) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(R.drawable.baseline_favorite_24),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                } else {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(R.drawable.baseline_favorite_border_24),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

            }
        }
        Spacer(Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(14.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(8.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyLarge,
                text = uiData.content,
            )
        }
    }
}

@Composable
private fun QuoteDetailsScreenLoading(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(60.dp),
            color = MaterialTheme.colorScheme.primary,
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun QuoteDetailsScreenContentPreview() {
    AppTheme {
        QuoteDetailsScreenUi(
            modifier = Modifier.fillMaxSize(),
            presenter = QuoteDetailsScreenPresenter.Preview(
                QuoteDetailsScreenUiState.Content(
                    film = "Pirates of Carribian Sea \nADASDADA",
                    content = "Im a captain Jack Sparrow\nIm a captain Jack Sparrow\nIm a captain Jack Sparrow\nIm a captain Jack Sparrow\nIm a captain Jack Sparrow\nIm a captain Jack Sparrow\n",
                    isLiked = true,
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun QuoteDetailsScreenLoadingPreview() {
    AppTheme {
        QuoteDetailsScreenUi(
            modifier = Modifier.fillMaxSize(),
            presenter = QuoteDetailsScreenPresenter.Preview(
                QuoteDetailsScreenUiState.Loading(isLoading = true)
            )
        )
    }
}