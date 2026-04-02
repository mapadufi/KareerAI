package com.kiko.kareerai.data.repository

import com.kiko.kareerai.data.local.dao.CurriculoDao
import com.kiko.kareerai.data.local.entity.Curriculo
import kotlinx.coroutines.flow.Flow

class CurriculoRepository(private val curriculoDao: CurriculoDao) {

    fun getCurriculoByUsuario(usuarioId: Long): Flow<Curriculo?> =
        curriculoDao.getCurriculoByUsuario(usuarioId)

    suspend fun insertCurriculo(curriculo: Curriculo) =
        curriculoDao.insert(curriculo)

    suspend fun deleteCurriculoByUsuario(usuarioId: Long) =
        curriculoDao.deleteCurriculoByUsuario(usuarioId)
}
