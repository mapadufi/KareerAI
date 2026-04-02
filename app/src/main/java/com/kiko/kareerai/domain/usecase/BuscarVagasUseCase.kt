package com.kiko.kareerai.domain.usecase

import com.kiko.kareerai.data.model.Perfil
import com.kiko.kareerai.data.remote.jobs.JobRepository
import com.kiko.kareerai.data.local.entity.Job

class BuscarVagasUseCase(
    private val repository: JobRepository
) {
    suspend operator fun invoke(apiKey: String, perfil: Perfil): List<Job> {
        return repository.buscarVagas(apiKey, perfil)
    }
}
