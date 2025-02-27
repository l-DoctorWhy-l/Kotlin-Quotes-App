package ru.rodipit.profile.ui

import androidx.compose.runtime.Stable

@Stable
sealed interface ProfileScreenUiState {

    data class Loading(val isLoading: Boolean): ProfileScreenUiState
    data class Content(
        val email: String,
        val name: String,
    ): ProfileScreenUiState

}