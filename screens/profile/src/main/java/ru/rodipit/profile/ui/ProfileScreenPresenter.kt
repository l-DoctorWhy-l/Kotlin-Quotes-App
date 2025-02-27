package ru.rodipit.profile.ui

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.rodipit.profile.ProfileScreenViewModel

internal interface ProfileScreenPresenter {

    val state: StateFlow<ProfileScreenUiState>

    class Impl(
        viewModel: ProfileScreenViewModel,
    ): ProfileScreenPresenter {

        override val state = viewModel.uiState

    }

    class Preview(
        uiState: ProfileScreenUiState,
    ): ProfileScreenPresenter {
        override val state = MutableStateFlow(uiState)
    }


}