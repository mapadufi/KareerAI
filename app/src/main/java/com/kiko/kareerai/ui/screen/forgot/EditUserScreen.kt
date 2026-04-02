package com.kiko.kareerai.ui.screen.forgot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kiko.kareerai.components.buttons.KikoExtraButton
import com.kiko.kareerai.components.outlined.KikoOutlinedTextField
import com.kiko.kareerai.components.toast.KikoErrorToast
import com.kiko.kareerai.components.toast.KikoSuccessToast
import com.kiko.kareerai.data.database.KareerAIDatabase
import com.kiko.kareerai.data.entity.Usuario
import com.kiko.kareerai.navigation.Screen
import com.kiko.kareerai.ui.theme.KareerAITheme
import com.kiko.kareerai.viewmodel.UsuarioViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun EditUserScreen(
    navController: NavController? = null,
    viewModel: UsuarioViewModel? = null
) {
    val scope = rememberCoroutineScope()

    var username by remember { mutableStateOf(if (viewModel == null) "Usuário Exemplo" else "") }
    var email by remember { mutableStateOf(if (viewModel == null) "usuario@email.com" else "") }
    var phone by remember { mutableStateOf(if (viewModel == null) "+55 11 99999-9999" else "") }
    var newPassword by remember { mutableStateOf("") }
    var confirmNewPassword by remember { mutableStateOf("") }

    var showSuccess by remember { mutableStateOf(false) }
    var showErrorMessage by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(viewModel) {
        if (viewModel != null) {
            viewModel.currentUser.collect { usuario ->
                usuario?.let {
                    username = it.nomeUsuario
                    email = it.email
                    phone = it.telefone
                }
            }
        }
    }

    LaunchedEffect(showErrorMessage) {
        if (showErrorMessage) {
            delay(2000)
            showErrorMessage = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF003399), Color(0xFF0048FF))
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Editar Usuário",
                color = Color.White,
                fontSize = 20.sp
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(top = 96.dp, bottom = 24.dp, start = 24.dp, end = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (showErrorMessage) {
                KikoErrorToast(message = errorMessage, modifier = Modifier.fillMaxWidth())
            }

            if (showSuccess) {
                KikoSuccessToast(message = "Dados atualizados!", modifier = Modifier.fillMaxWidth())
            }

            KikoOutlinedTextField("Nome de Usuário", username, { username = it })
            KikoOutlinedTextField("Email", email, { email = it })
            KikoOutlinedTextField("Telefone", phone, { phone = it })
            KikoOutlinedTextField("Nova Senha", newPassword, { newPassword = it })
            KikoOutlinedTextField("Confirmar Nova Senha", confirmNewPassword, { confirmNewPassword = it })

            Spacer(modifier = Modifier.height(24.dp))

            KikoExtraButton(
                text = "SALVAR ALTERAÇÕES",
                onClick = {
                    scope.launch {
                        if (username.isBlank() || email.isBlank() || phone.isBlank()) {
                            errorMessage = "Preencha todos os campos"
                            showErrorMessage = true
                            return@launch
                        }

                        if (newPassword.isNotBlank() && newPassword != confirmNewPassword) {
                            errorMessage = "As senhas não conferem"
                            showErrorMessage = true
                            return@launch
                        }

                        if (viewModel == null) {
                            showSuccess = true
                            return@launch
                        }

                        val current = viewModel.currentUser.value
                        if (current == null) {
                            errorMessage = "Nenhum usuário encontrado"
                            showErrorMessage = true
                            return@launch
                        }

                        val senhaFinal = if (newPassword.isNotBlank()) newPassword else current.senha

                        val updated = Usuario(
                            id = current.id,
                            nomeUsuario = username,
                            senha = senhaFinal,
                            email = email,
                            telefone = phone
                        )

                        viewModel.updateUsuario(updated)

                        showSuccess = true
                        delay(1500)
                        navController?.navigate(Screen.Login.route) {
                            popUpTo(Screen.EditUser.route) { inclusive = true }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            KikoExtraButton(
                text = "CANCELAR",
                onClick = { navController?.popBackStack() },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun EditUserScreenLightPreview() {
    KareerAITheme (darkTheme = false) {
        EditUserScreen()
    }
}
