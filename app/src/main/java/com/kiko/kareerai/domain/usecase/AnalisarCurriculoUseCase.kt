package com.kiko.kareerai.domain.usecase

import com.kiko.kareerai.data.model.Perfil
import com.kiko.kareerai.data.remote.ai.AIRepository

class AnalisarCurriculoUseCase(
    private val repository: AIRepository
) {
    suspend operator fun invoke(apiKey: String, curriculo: String): Perfil {
        return repository.analisarCurriculo(apiKey, curriculo)
    }
}
