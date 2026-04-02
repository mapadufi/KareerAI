package com.kiko.kareerai.data.repository

import com.kiko.kareerai.data.local.dao.JobDao
import com.kiko.kareerai.data.local.entity.Job

class JobLocalRepository(
    private val dao: JobDao
) {

    suspend fun obterVagasSalvas(): List<Job> {
        return dao.getAllJobs()
    }

    suspend fun salvarVaga(job: Job) {
        dao.insert(job)
    }
}