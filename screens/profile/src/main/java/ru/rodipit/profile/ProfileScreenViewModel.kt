package ru.rodipit.profile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.rodipit.profile.ui.ProfileScreenUiState

internal class ProfileScreenViewModel: ViewModel() {

    private val _uiState: MutableStateFlow<ProfileScreenUiState> = MutableStateFlow(
        ProfileScreenUiState.Loading(true)
    )
    val uiState = _uiState.asStateFlow()

}