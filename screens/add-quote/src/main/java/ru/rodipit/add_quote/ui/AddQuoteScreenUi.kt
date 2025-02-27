package ru.rodipit.add_quote.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import ru.rodipit.design.theme.AppTheme
import ru.rodipit.design.R
import ru.rodipit.design.components.BackButton
import ru.rodipit.design.components.TopAppBar

@Composable
internal fun AddQuoteScreenUi(
    presenter: AddQuoteScreenPresenter,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        TopAppBar(
            title = stringResource(R.string.add_quote_screen_top_bar_title),
            leadingButton = {
                BackButton(
                    onBackButtonClicked = { },
                )
            }
        )
        AddQuoteScreenContent(
            uiData = presenter.state.collectAsState().value,
        )
    }
}


@Composable
private fun AddQuoteScreenContent(
    uiData: AddQuoteScreenUiState,
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
            value = uiData.film,
            onValueChange = {

            },
            supportingText = {
                Text(
                    text = stringResource(R.string.enter_movie_title)
                )
            },
        )
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .heightIn(max = 140.dp),
            value = uiData.quoteText,
            onValueChange = {

            },
            supportingText = {
                Text(
                    text = stringResource(R.string.enter_movie_quote)
                )
            },
        )
        Button(
            onClick = { }
        ) {
            Text(
                text = stringResource(R.string.add_quote_button_text)
            )
        }
    }
}


@Preview
@Composable
private fun AddQuoteScreenContentPreview() {
    AppTheme {
        AddQuoteScreenUi(
            presenter = AddQuoteScreenPresenter.Preview(
                uiState = AddQuoteScreenUiState(
                    film = "FAFSA",
                    quoteText = "Adasdsaadasda"
                )
            )
        )
    }
}