package ru.rodipit.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class DataStoreManager(
    private val context: Context,
) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_datastore")


    suspend fun saveData(key: String, data: String) {
        context.dataStore.edit { preferences ->
            preferences[stringPreferencesKey(key)] = data
        }
    }

    suspend fun clearData(key: String) {
        context.dataStore.edit { preferences ->
            preferences.remove(stringPreferencesKey(key))
        }
    }

    fun getData(key: String): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[stringPreferencesKey(key)]
        }
    }
}