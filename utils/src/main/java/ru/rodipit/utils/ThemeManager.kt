package ru.rodipit.utils

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.themeDataStore: DataStore<Preferences> by preferencesDataStore(name = "theme_preferences")

class ThemeManager(private val context: Context) {

    private val dataStore = context.themeDataStore

    private object PreferencesKeys {
        val SELECTED_THEME = stringPreferencesKey("selected_theme")
    }

    val themeFlow: Flow<String> = dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.SELECTED_THEME] ?: "system"
        }

    suspend fun setTheme(theme: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.SELECTED_THEME] = theme
        }
    }

    suspend fun applyTheme() {
        themeFlow.collect { theme ->
            Log.d("tag", "${theme}!")
            when (theme) {
                "light" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                "dark" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }
    }
}