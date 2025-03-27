package ru.rodipit.profile.ui

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.rodipit.profile.ProfileScreenViewModel

internal interface ProfileScreenPresenter {

    val state: StateFlow<ProfileScreenUiState>

    fun onNavigateToSettings()

    fun onBackButtonClick()

    class Impl(
        private val viewModel: ProfileScreenViewModel,
    ): ProfileScreenPresenter {

        override val state = viewModel.uiState

        override fun onNavigateToSettings() {
            viewModel.onNavigateToSettingsScreen()
        }

        override fun onBackButtonClick() {
            viewModel.onBackButtonClick()
        }

    }

    class Preview(
        uiState: ProfileScreenUiState,
    ): ProfileScreenPresenter {
        override val state = MutableStateFlow(uiState)
        override fun onNavigateToSettings()  = Unit
        override fun onBackButtonClick() = Unit
    }


}