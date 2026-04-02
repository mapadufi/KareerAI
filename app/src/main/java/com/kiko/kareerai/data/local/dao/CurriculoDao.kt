package com.kiko.kareerai.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kiko.kareerai.data.local.entity.Curriculo
import kotlinx.coroutines.flow.Flow

@Dao
interface CurriculoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(curriculo: Curriculo)

    @Query("SELECT * FROM curriculo WHERE usuario_id = :usuarioId LIMIT 1")
    fun getCurriculoByUsuario(usuarioId: Long): Flow<Curriculo?>

    @Query("DELETE FROM curriculo WHERE usuario_id = :usuarioId")
    suspend fun deleteCurriculoByUsuario(usuarioId: Long)
}
