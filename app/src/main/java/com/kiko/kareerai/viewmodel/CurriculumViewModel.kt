package com.kiko.kareerai.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kiko.kareerai.data.local.entity.Curriculo
import com.kiko.kareerai.data.model.Perfil
import com.kiko.kareerai.data.repository.CurriculoRepository
import com.kiko.kareerai.domain.usecase.AnalisarCurriculoUseCase
import com.kiko.kareerai.data.remote.ai.AIRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class CurriculumViewModel(
    private val analisarCurriculoUseCase: AnalisarCurriculoUseCase,
    private val curriculoRepository: CurriculoRepository,
    private val aiRepository: AIRepository
) : ViewModel() {

    private val _perfil = MutableStateFlow<Perfil?>(null)
    val perfil: StateFlow<Perfil?> = _perfil

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _erro = MutableStateFlow<String?>(null)
    val erro: StateFlow<String?> = _erro

    private val _pitch = MutableStateFlow<String?>(null)
    val pitch: StateFlow<String?> = _pitch

    private val _isGeneratingPitch = MutableStateFlow(false)
    val isGeneratingPitch: StateFlow<Boolean> = _isGeneratingPitch

    fun analisarCurriculo(apiKey: String, texto: String, usuarioId: Long) {
        viewModelScope.launch {
            _loading.value = true
            _erro.value = null

            try {
                val resultado = analisarCurriculoUseCase(apiKey, texto)
                
                val curriculoEntity = Curriculo(
                    usuarioId = usuarioId,
                    textoBruto = texto,
                    cargo = resultado.cargo,
                    nivel = resultado.nivel,
                    tecnologias = resultado.tecnologias.joinToString(", ")
                )
                curriculoRepository.insertCurriculo(curriculoEntity)

                _perfil.value = resultado

            } catch (e: Exception) {
                _erro.value = e.message ?: "Erro ao analisar currículo"
            } finally {
                _loading.value = false
            }
        }
    }

    fun carregarCurriculoSalvo(usuarioId: Long) {
        viewModelScope.launch {
            curriculoRepository.getCurriculoByUsuario(usuarioId).collect { saved ->
                saved?.let {
                    _perfil.value = Perfil(
                        cargo = it.cargo,
                        nivel = it.nivel,
                        tecnologias = it.tecnologias.split(", ").filter { t -> t.isNotBlank() }
                    )
                }
            }
        }
    }

    fun gerarPitchIA(apiKey: String, usuarioId: Long, vagaTitulo: String, vagaDescricao: String) {
        viewModelScope.launch {
            _isGeneratingPitch.value = true
            try {
                val curriculo = curriculoRepository.getCurriculoByUsuario(usuarioId).first()
                if (curriculo != null) {
                    val pitchGerado = aiRepository.gerarPitch(
                        apiKey = apiKey,
                        curriculo = curriculo.textoBruto,
                        vagaTitulo = vagaTitulo,
                        vagaDescricao = vagaDescricao
                    )
                    _pitch.value = pitchGerado
                } else {
                    _erro.value = "Nenhum currículo encontrado."
                }
            } catch (e: Exception) {
                _erro.value = "Erro ao conectar com a IA"
            } finally {
                _isGeneratingPitch.value = false
            }
        }
    }

    fun limparPitch() {
        _pitch.value = null
    }

    fun limparPerfil() {
        _perfil.value = null
    }
}
