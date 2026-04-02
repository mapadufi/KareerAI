package com.kiko.kareerai.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.themeDataStore: DataStore<Preferences> by preferencesDataStore(name = "theme_preferences")

class ThemePreferences(private val context: Context) {

    companion object {
        private val THEME_KEY = stringPreferencesKey("app_theme")
        private val DARK_MODE_KEY = booleanPreferencesKey("dark_mode") // ✅ usar booleanPreferencesKey
    }

    val themeFlow: Flow<String?> = context.themeDataStore.data.map { it[THEME_KEY] }

    val darkModeFlow: Flow<Boolean> = context.themeDataStore.data.map { it[DARK_MODE_KEY] ?: false }

    suspend fun saveTheme(theme: String) {
        context.themeDataStore.edit { it[THEME_KEY] = theme }
    }

    suspend fun saveDarkMode(isDark: Boolean) {
        context.themeDataStore.edit { it[DARK_MODE_KEY] = isDark }
    }
}
