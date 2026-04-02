package com.kiko.kareerai.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kiko.kareerai.data.database.KareerAIBD
import com.kiko.kareerai.data.entity.Usuario
import com.kiko.kareerai.data.repository.UsuarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class
UsuarioViewModel(application: Application) : AndroidViewModel(application) {

    private val usuarioRepository: UsuarioRepository

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    private val _currentUser = MutableStateFlow<Usuario?>(null)
    val currentUser: StateFlow<Usuario?> = _currentUser

    init {
        val database = KareerAIBD.getDatabase(application)
        usuarioRepository = UsuarioRepository(database.usuarioDao())

        // Verifica se usuário já existe
        viewModelScope.launch {
            usuarioRepository.getUsuario().collect { usuario ->
                _currentUser.value = usuario
                _isLoggedIn.value = usuario != null
            }
        }
    }

    fun insertUsuario(usuario: Usuario) {
        viewModelScope.launch {
            usuarioRepository.insertUsuario(usuario)
            _currentUser.value = usuario
            _isLoggedIn.value = true
        }
    }

    suspend fun login(nomeUsuario: String, senha: String): Usuario? {
        val usuario = usuarioRepository.login(nomeUsuario, senha)
        if (usuario != null) {
            _currentUser.value = usuario
            _isLoggedIn.value = true
        }
        return usuario
    }

    fun logout() {
        viewModelScope.launch {
            _currentUser.value = null
            _isLoggedIn.value = false
        }
    }

    fun updateUsuario(usuario: Usuario) {
        viewModelScope.launch {
            usuarioRepository.updateUsuario(usuario)
            _currentUser.value = usuario
        }
    }

    suspend fun getUsuarioTelefone(): String? {
        val usuario = usuarioRepository.getUsuario().first()
        return usuario?.telefone
    }
}
