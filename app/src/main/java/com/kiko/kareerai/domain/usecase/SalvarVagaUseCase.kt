package com.kiko.kareerai.domain.usecase

import com.kiko.kareerai.data.local.dao.JobDao
import com.kiko.kareerai.data.local.entity.Job

class SalvarVagaUseCase(
    private val repository: JobDao
) {

    suspend operator fun invoke(job: Job) {
        repository.insert(job)
    }
}