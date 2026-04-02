package com.kiko.kareerai.data.dao

import androidx.room.*
import com.kiko.kareerai.data.entity.Usuario
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao {

    @Query("SELECT * FROM usuario LIMIT 1")
    fun getUsuario(): Flow<Usuario?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsuario(usuario: Usuario)

    @Update
    suspend fun updateUsuario(usuario: Usuario)

    @Query("SELECT COUNT(*) FROM usuario")
    suspend fun getUsuarioCount(): Int

    @Query("SELECT * FROM usuario WHERE nome_usuario = :nomeUsuario AND senha = :senha LIMIT 1")
    suspend fun login(nomeUsuario: String, senha: String): Usuario?
}
