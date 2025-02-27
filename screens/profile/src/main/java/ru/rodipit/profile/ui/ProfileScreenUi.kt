package ru.rodipit.profile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.rodipit.design.components.TopAppBar
import ru.rodipit.design.theme.AppTheme
import ru.rodipit.design.R
import ru.rodipit.design.components.BackButton

@Composable
internal fun ProfileScreenUi(
    presenter: ProfileScreenPresenter,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        TopAppBar(
            title = stringResource(R.string.profile_screen_top_bar_title),
            leadingButton = {
                BackButton(
                    onBackButtonClicked = { },
                )
            }
        )
        when(val uiState = presenter.state.collectAsState().value) {
            is ProfileScreenUiState.Loading -> {

            }
            is ProfileScreenUiState.Content -> {
                ProfileScreenContent(
                    uiData = uiState,
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
private fun ProfileScreenContent(
    uiData: ProfileScreenUiState.Content,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
    ) {

        IconButton(
            modifier = Modifier.align(Alignment.End),
            onClick = { },
        ) {
            Icon(
                modifier = Modifier.size(32.dp),
                painter = painterResource(R.drawable.baseline_edit_24),
                contentDescription = null,
            )
        }
        Image(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 12.dp),
            painter = painterResource(R.drawable.baseline_person_24),
            contentDescription = null
        )
        Spacer(Modifier.height(8.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.email, uiData.email),
        )
        Spacer(Modifier.height(8.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.name, uiData.name),
        )
        Spacer(Modifier.weight(1f))
        Button(
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.onErrorContainer,
                disabledContentColor = MaterialTheme.colorScheme.scrim,
                disabledContainerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = { },
        ) {
            Text(
                text = "LogOut"
            )
        }
    }
}


@Preview
@Composable
private fun ProfileScreenUiPreview() {
    AppTheme {
        ProfileScreenUi(
            presenter = ProfileScreenPresenter.Preview(
                ProfileScreenUiState.Content(
                    email = "kvartalovfade@gmail.com",
                    name = "Egor",
                )
            )
        )
    }
}