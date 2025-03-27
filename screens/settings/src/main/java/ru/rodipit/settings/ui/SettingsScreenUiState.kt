package ru.rodipit.settings.ui

import androidx.compose.runtime.Stable

@Stable
internal data class SettingsScreenUiState(
    val theme: ThemeType,
)

internal enum class ThemeType {
    Day, Night, Auto, None
}

internal fun themeToString(theme: ThemeType) = when (theme) {
    ThemeType.Day -> "light"
    ThemeType.Night -> "dark"
    else -> "system"
}