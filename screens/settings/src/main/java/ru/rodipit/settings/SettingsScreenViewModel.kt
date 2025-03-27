package ru.rodipit.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigation.api.AppNavigator
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.rodipit.settings.ui.SettingsScreenUiState
import ru.rodipit.settings.ui.ThemeType
import ru.rodipit.settings.ui.themeToString
import ru.rodipit.utils.ThemeManager

internal class SettingsScreenViewModel(
    private val navigator: AppNavigator,
    private val themeManager: ThemeManager,
): ViewModel() {

    private val _uiState: StateFlow<SettingsScreenUiState> = themeManager.themeFlow.map { theme ->
        SettingsScreenUiState(
            theme = when(theme) {
                "light" -> ThemeType.Day
                "dark" -> ThemeType.Night
                else -> ThemeType.Auto
            }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = SettingsScreenUiState(ThemeType.None)
    )
    val uiState = _uiState


    fun setTheme(themeType: ThemeType) {
        viewModelScope.launch {
            val themeName = themeToString(themeType)
            themeManager.setTheme(themeName)
        }
    }


    fun onBackButtonClick() {
        navigator.popBackStack()
    }

}