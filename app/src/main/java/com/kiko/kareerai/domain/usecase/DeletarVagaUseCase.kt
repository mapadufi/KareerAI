package com.kiko.kareerai.domain.usecase

import com.kiko.kareerai.data.local.dao.JobDao

class DeletarVagaUseCase(
    private val repository: JobDao
) {
    suspend operator fun invoke(id: Int) {
        repository.deleteById(id)
    }
}
