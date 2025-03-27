package ru.rodipit.settings.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel
import ru.rodipit.settings.SettingsScreenViewModel
import ru.rodipit.settings.ui.SettingsScreenPresenter
import ru.rodipit.settings.ui.SettingsScreenUi

@Composable
fun SettingsScreenWrapper(
    modifier: Modifier = Modifier,
) {
    val viewModel: SettingsScreenViewModel = koinViewModel()
    val presenter = remember { SettingsScreenPresenter.Impl(viewModel = viewModel) }
    SettingsScreenUi(
        presenter = presenter,
        modifier = modifier,
    )
}
