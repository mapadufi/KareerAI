package com.kiko.kareerai.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kiko.kareerai.data.local.entity.Job
import com.kiko.kareerai.data.model.Perfil
import com.kiko.kareerai.domain.usecase.BuscarVagasUseCase
import com.kiko.kareerai.domain.usecase.DeletarVagaUseCase
import com.kiko.kareerai.domain.usecase.ObterVagasSalvasUseCase
import com.kiko.kareerai.domain.usecase.SalvarVagaUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class JobViewModel(
    private val buscarVagasUseCase: BuscarVagasUseCase,
    private val salvarVagaUseCase: SalvarVagaUseCase,
    private val obterVagasSalvasUseCase: ObterVagasSalvasUseCase,
    private val deletarVagaUseCase: DeletarVagaUseCase
) : ViewModel() {

    private val _jobs = MutableStateFlow<List<Job>>(emptyList())
    val jobs: StateFlow<List<Job>> = _jobs

    private val _savedJobs = MutableStateFlow<List<Job>>(emptyList())
    val savedJobs: StateFlow<List<Job>> = _savedJobs

    private val _selectedJob = MutableStateFlow<Job?>(null)
    val selectedJob: StateFlow<Job?> = _selectedJob

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _erro = MutableStateFlow<String?>(null)
    val erro: StateFlow<String?> = _erro

    fun buscarVagas(apiKey: String, perfil: Perfil) {
        viewModelScope.launch {
            _loading.value = true
            _erro.value = null
            try {
                val resultado = buscarVagasUseCase(apiKey, perfil)
                _jobs.value = resultado
            } catch (e: Exception) {
                _erro.value = e.message ?: "Erro ao buscar vagas"
            } finally {
                _loading.value = false
            }
        }
    }

    fun selectJob(job: Job) {
        _selectedJob.value = job
    }

    fun salvarVaga(job: Job) {
        viewModelScope.launch {
            try {
                salvarVagaUseCase(job)
                carregarVagasSalvas()
            } catch (e: Exception) {
                _erro.value = "Erro ao salvar vaga"
            }
        }
    }

    fun deletarVaga(id: Int) {
        viewModelScope.launch {
            try {
                deletarVagaUseCase(id)
                carregarVagasSalvas()
            } catch (e: Exception) {
                _erro.value = "Erro ao deletar vaga"
            }
        }
    }

    fun carregarVagasSalvas() {
        viewModelScope.launch {
            try {
                _savedJobs.value = obterVagasSalvasUseCase()
            } catch (e: Exception) {
                _erro.value = "Erro ao carregar vagas salvas"
            }
        }
    }
}
