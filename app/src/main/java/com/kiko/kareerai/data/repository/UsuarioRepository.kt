package com.kiko.kareerai.data.repository

import com.kiko.kareerai.data.dao.UsuarioDao
import com.kiko.kareerai.data.entity.Usuario
import kotlinx.coroutines.flow.Flow

class UsuarioRepository(private val usuarioDao: UsuarioDao) {

    fun getUsuario(): Flow<Usuario?> = usuarioDao.getUsuario()

    suspend fun insertUsuario(usuario: Usuario) = usuarioDao.insertUsuario(usuario)

    suspend fun updateUsuario(usuario: Usuario) = usuarioDao.updateUsuario(usuario)

    suspend fun getUsuarioCount(): Int = usuarioDao.getUsuarioCount()

    suspend fun login(nomeUsuario: String, senha: String): Usuario? =
        usuarioDao.login(nomeUsuario, senha)
}
