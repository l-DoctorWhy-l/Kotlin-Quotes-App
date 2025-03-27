package ru.rodipit.profile

import androidx.lifecycle.ViewModel
import com.example.navigation.api.AppNavigator
import com.example.navigation.api.Route
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.rodipit.profile.ui.ProfileScreenUiState

internal class ProfileScreenViewModel(
    private val navigator: AppNavigator,
): ViewModel() {

    private val _uiState: MutableStateFlow<ProfileScreenUiState> = MutableStateFlow(
        ProfileScreenUiState.Loading(true)
    )
    val uiState = _uiState.asStateFlow()


    fun onNavigateToSettingsScreen() {
        navigator.navigateTo(Route.Settings)
    }

    fun onBackButtonClick() {
        navigator.popBackStack()
    }

}