package com.kiko.kareerai.data.remote.jobs

import com.kiko.kareerai.data.local.entity.Job
import com.kiko.kareerai.data.model.Perfil
import com.kiko.kareerai.data.remote.ai.AIRepository

class JobRepository(
    private val service: JobService,
    private val aiRepository: AIRepository
) {

    suspend fun buscarVagas(apiKey: String, perfil: Perfil): List<Job> {
        return try {
            val query = montarQuery(perfil)
            val response = service.buscarVagas(query)

            val jobsLimitados = response.jobs.take(10)

            jobsLimitados.map { item ->
                val descricao = item.description ?: ""
                
                val score = aiRepository.calcularMatch(
                    apiKey = apiKey,
                    curriculo = formatarPerfilParaIA(perfil),
                    vagaDescricao = descricao
                )

                Job(
                    title = item.title,
                    company = item.company,
                    location = item.location,
                    description = descricao,
                    url = item.url,
                    isRemote = item.isRemote ?: false,
                    datePosted = item.datePosted ?: "",
                    matchScore = score
                )
            }.sortedByDescending { it.matchScore }

        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    private fun montarQuery(perfil: Perfil): String {
        val local = perfil.localizacao
        val buscaBase = (listOf(perfil.cargo) + perfil.tecnologias).joinToString(" ")
        return if (local.isNotBlank()) "$buscaBase $local" else buscaBase
    }

    private fun formatarPerfilParaIA(perfil: Perfil): String {
        return """
            Cargo: ${perfil.cargo}
            Nível: ${perfil.nivel}
            Tecnologias: ${perfil.tecnologias.joinToString(", ")}
            Localização: ${perfil.localizacao}
        """.trimIndent()
    }
}
