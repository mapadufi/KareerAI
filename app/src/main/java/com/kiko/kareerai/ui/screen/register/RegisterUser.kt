package com.kiko.kareerai.ui.screen.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kiko.kareerai.components.buttons.KikoExtraButton
import com.kiko.kareerai.components.layout.LayoutKiko
import com.kiko.kareerai.components.outlined.KikoOutlinedTextField
import com.kiko.kareerai.components.toast.KikoErrorToast
import com.kiko.kareerai.components.toast.KikoSuccessToast
import com.kiko.kareerai.data.local.entity.Usuario
import com.kiko.kareerai.navigation.Screen
import com.kiko.kareerai.viewmodel.UsuarioViewModel
import kotlinx.coroutines.delay

@Composable
fun RegisterScreen(navController: NavController, viewModel: UsuarioViewModel) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var showErrorToast by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var showSuccessToast by remember { mutableStateOf(false) }

    LayoutKiko(
        navController = navController,
        usuarioViewModel = viewModel,
        title = "Cadastro",
        onBack = { navController.popBackStack() }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Crie sua conta", style = MaterialTheme.typography.headlineMedium)

                Spacer(modifier = Modifier.height(24.dp))

                KikoOutlinedTextField(label = "Usuário", value = username, onValueChange = { username = it })
                Spacer(modifier = Modifier.height(8.dp))
                KikoOutlinedTextField(
                    label = "Email",
                    value = email,
                    onValueChange = { email = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )
                Spacer(modifier = Modifier.height(8.dp))
                KikoOutlinedTextField(label = "Telefone", value = phone, onValueChange = { phone = it })
                Spacer(modifier = Modifier.height(8.dp))
                KikoOutlinedTextField(
                    label = "Senha",
                    value = password,
                    onValueChange = { password = it },
                    visualTransformation = PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.height(8.dp))
                KikoOutlinedTextField(
                    label = "Confirmar Senha",
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    visualTransformation = PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.height(16.dp))

                KikoExtraButton(
                    text = "CADASTRAR",
                    onClick = {
                        if (username.isBlank() || email.isBlank() || phone.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
                            errorMessage = "Preencha todos os campos"
                            showErrorToast = true
                            return@KikoExtraButton
                        }
                        if (password != confirmPassword) {
                            errorMessage = "Senhas não coincidem"
                            showErrorToast = true
                            return@KikoExtraButton
                        }

                        // Salvar senha em texto puro
                        val usuario = Usuario(
                            nomeUsuario = username,
                            senha = password,
                            email = email,
                            telefone = phone,
                        )
                        viewModel.insertUsuario(usuario)
                        showSuccessToast = true
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                KikoExtraButton(
                    text = "JÁ TEM CONTA? FAÇA LOGIN",
                    onClick = {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Register.route) { inclusive = true }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            if (showErrorToast) {
                KikoErrorToast(
                    message = errorMessage,
                    modifier = Modifier.align(Alignment.TopCenter).padding(top = 16.dp)
                )
                LaunchedEffect(showErrorToast) { delay(2000); showErrorToast = false }
            }

            if (showSuccessToast) {
                KikoSuccessToast(
                    message = "Usuário cadastrado!",
                    modifier = Modifier.align(Alignment.TopCenter).padding(top = 16.dp)
                )
                LaunchedEffect(showSuccessToast) {
                    delay(1500)
                    showSuccessToast = false
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Register.route) { inclusive = true }
                    }
                }
            }
        }
    }
}
