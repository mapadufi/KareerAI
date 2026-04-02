package com.kiko.kareerai.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kiko.kareerai.data.local.entity.Job

@Dao
interface JobDao {

    // 🔹 Inserir vaga (ou substituir se já existir)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(job: Job)

    // 🔹 Inserir lista de vagas
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(jobs: List<Job>)

    // 🔹 Buscar todas as vagas salvas
    @Query("SELECT * FROM jobs ORDER BY id DESC")
    suspend fun getAllJobs(): List<Job>

    // 🔹 Buscar vaga por ID
    @Query("SELECT * FROM jobs WHERE id = :id")
    suspend fun getJobById(id: Int): Job?

    // 🔹 Deletar vaga
    @Query("DELETE FROM jobs WHERE id = :id")
    suspend fun deleteById(id: Int)

    // 🔹 Deletar todas as vagas
    @Query("DELETE FROM jobs")
    suspend fun deleteAll()
}