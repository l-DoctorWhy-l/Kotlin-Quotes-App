package ru.rodipit.settings.ui

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.rodipit.settings.SettingsScreenViewModel

internal interface SettingsScreenPresenter {

    val state: StateFlow<SettingsScreenUiState>

    fun onBackButtonClick()

    fun setTheme(themeType: ThemeType)

    class Impl(
        private val viewModel: SettingsScreenViewModel,
    ): SettingsScreenPresenter {

        override val state = viewModel.uiState
        override fun onBackButtonClick() {
            viewModel.onBackButtonClick()
        }

        override fun setTheme(themeType: ThemeType) {
            viewModel.setTheme(themeType)
        }

    }

    class Preview(
        uiState: SettingsScreenUiState,
    ): SettingsScreenPresenter {
        override val state = MutableStateFlow(uiState)
        override fun onBackButtonClick() = Unit
        override fun setTheme(themeType: ThemeType) = Unit
    }


}