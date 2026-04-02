package com.kiko.kareerai.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kiko.kareerai.data.local.datastore.ThemePreferences
import com.kiko.kareerai.ui.theme.AppThemeType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ThemeViewModel(application: Application) : AndroidViewModel(application) {

    private val preferences = ThemePreferences(application)

    private val _themeType = MutableStateFlow(AppThemeType.PADRAO)
    val themeType: StateFlow<AppThemeType> = _themeType

    private val _isDarkMode = MutableStateFlow(false)
    val isDarkMode: StateFlow<Boolean> = _isDarkMode

    private val _geminiApiKey = MutableStateFlow<String?>(null)
    val geminiApiKey: StateFlow<String?> = _geminiApiKey

    init {
        viewModelScope.launch {
            preferences.themeFlow.collectLatest { theme ->
                _themeType.value = when (theme) {
                    AppThemeType.VERDE.name -> AppThemeType.VERDE
                    AppThemeType.VERMELHO.name -> AppThemeType.VERMELHO
                    AppThemeType.ROXO.name -> AppThemeType.ROXO
                    else -> AppThemeType.PADRAO
                }
            }
        }

        viewModelScope.launch {
            preferences.darkModeFlow.collectLatest { isDark ->
                _isDarkMode.value = isDark
            }
        }

        viewModelScope.launch {
            preferences.geminiApiKeyFlow.collectLatest { key ->
                _geminiApiKey.value = key
            }
        }
    }

    fun setTheme(theme: AppThemeType) {
        viewModelScope.launch {
            _themeType.value = theme
            preferences.saveTheme(theme.name)
        }
    }

    fun setDarkMode(isDark: Boolean) {
        viewModelScope.launch {
            _isDarkMode.value = isDark
            preferences.saveDarkMode(isDark)
        }
    }

    fun setGeminiApiKey(key: String) {
        viewModelScope.launch {
            _geminiApiKey.value = key
            preferences.saveGeminiApiKey(key)
        }
    }
}
