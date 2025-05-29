package ru.rodipit.profile.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel
import ru.rodipit.profile.ProfileScreenViewModel
import ru.rodipit.profile.ui.ProfileScreenPresenter
import ru.rodipit.profile.ui.ProfileScreenUi
import ru.rodipit.profile.ui.ProfileScreenUiState

@Composable
fun ProfileScreenWrapper(
    modifier: Modifier = Modifier,
) {
    val viewModel: ProfileScreenViewModel = koinViewModel()
    val presenter = remember { ProfileScreenPresenter.Impl(viewModel = viewModel) }
    ProfileScreenUi(
        presenter = ProfileScreenPresenter.Preview(
            ProfileScreenUiState.Content(
                email = "kvartalovfade@gmail.com",
                name = "Egor",
            )
        ),
        modifier = modifier,
    )
}
