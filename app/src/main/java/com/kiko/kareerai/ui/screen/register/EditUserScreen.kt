package com.kiko.kareerai.ui.screen.register

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.kiko.kareerai.components.buttons.KikoExtraButton
import com.kiko.kareerai.components.layout.LayoutKiko
import com.kiko.kareerai.components.outlined.KikoOutlinedTextField
import com.kiko.kareerai.components.toast.KikoErrorToast
import com.kiko.kareerai.components.toast.KikoSuccessToast
import com.kiko.kareerai.data.entity.Usuario
import com.kiko.kareerai.viewmodel.UsuarioViewModel
import kotlinx.coroutines.launch

@Composable
fun EditarUserScreen(
    navController: NavController,
    usuarioViewModel: UsuarioViewModel = viewModel()
) {
    val scope = rememberCoroutineScope()

    // Observa o usuário atual do ViewModel
    val currentUser by usuarioViewModel.currentUser.collectAsState()

    // Campos editáveis
    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var novaSenha by remember { mutableStateOf("") }
    var confirmarSenha by remember { mutableStateOf("") }

    var showSuccess by remember { mutableStateOf(false) }
    var showErrorMessage by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    // Preenche os campos quando o usuário for carregado/atualizado
    LaunchedEffect(currentUser) {
        currentUser?.let {
            nome = it.nomeUsuario ?: ""
            email = it.email ?: ""
            telefone = it.telefone ?: ""
        }
    }

    // Oculta mensagem de erro após 2s
    LaunchedEffect(showErrorMessage) {
        if (showErrorMessage) {
            kotlinx.coroutines.delay(2000)
            showErrorMessage = false
        }
    }

    LayoutKiko (
        navController = navController,
        usuarioViewModel = usuarioViewModel,
        title = "Editar Usuário"
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Editar usuário", style = MaterialTheme.typography.headlineMedium)

            if (showErrorMessage) {
                KikoErrorToast(message = errorMessage, modifier = Modifier.fillMaxWidth())
            }

            if (showSuccess) {
                KikoSuccessToast(message = "Dados atualizados com sucesso!", modifier = Modifier.fillMaxWidth())
            }

            // Campos
            KikoOutlinedTextField("Nome de Usuário", nome, { nome = it }, Modifier.fillMaxWidth())
            KikoOutlinedTextField("Email", email, { email = it }, Modifier.fillMaxWidth())
            KikoOutlinedTextField("Telefone", telefone, { telefone = it }, Modifier.fillMaxWidth())
            KikoOutlinedTextField("Nova Senha", novaSenha, { novaSenha = it }, Modifier.fillMaxWidth())
            KikoOutlinedTextField("Confirmar Nova Senha", confirmarSenha, { confirmarSenha = it }, Modifier.fillMaxWidth())

            Spacer(Modifier.height(16.dp))

            // Botão Salvar
            KikoExtraButton(
                text = "Salvar",
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    scope.launch {
                        if (nome.isBlank() || email.isBlank() || telefone.isBlank()) {
                            errorMessage = "Preencha todos os campos"
                            showErrorMessage = true
                            return@launch
                        }

                        if (novaSenha.isNotBlank() && novaSenha != confirmarSenha) {
                            errorMessage = "As senhas não conferem"
                            showErrorMessage = true
                            return@launch
                        }

                        val current = currentUser
                        if (current == null) {
                            errorMessage = "Nenhum usuário encontrado"
                            showErrorMessage = true
                            return@launch
                        }

                        val senhaFinal = if (novaSenha.isNotBlank()) novaSenha else current.senha

                        val updated = Usuario(
                            id = current.id,
                            nomeUsuario = nome,
                            senha = senhaFinal,
                            email = email,
                            telefone = telefone
                        )

                        usuarioViewModel.updateUsuario(updated)

                        showSuccess = true
                        kotlinx.coroutines.delay(1500)
                        navController.popBackStack() // ou navegar para tela desejada
                    }
                }
            )

            // Botão Cancelar
            TextButton(onClick = { navController.popBackStack() }) {
                Text("Cancelar")
            }
        }
    }
}
