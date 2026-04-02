package com.kiko.kareerai.ui.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kiko.kareerai.components.buttons.KikoExtraButton
import com.kiko.kareerai.components.outlined.KikoOutlinedTextField
import com.kiko.kareerai.components.toast.KikoErrorToast
import com.kiko.kareerai.components.toast.KikoSuccessToast
import com.kiko.kareerai.navigation.Screen
import com.kiko.kareerai.ui.theme.KareerAITheme
import com.kiko.kareerai.viewmodel.UsuarioViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navController: NavController? = null,
    viewModel: UsuarioViewModel? = null
) {
    val gradientColors = listOf(
        MaterialTheme.colorScheme.outline,
        MaterialTheme.colorScheme.outlineVariant
    )
    val iconSize = 100.dp


    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var showErrorToast by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var showSuccessToast by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    // Coletando usuário logado (StateFlow)
    val usuarioCadastrado by viewModel?.currentUser?.collectAsState() ?: remember { mutableStateOf(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = androidx.compose.ui.graphics.Brush.verticalGradient(gradientColors))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Kareer AI",
                color = MaterialTheme.colorScheme.onTertiary,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 40.dp)
            )

            // Icon Pets
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "Dog Icon",
                modifier = Modifier.size(iconSize),
                tint = MaterialTheme.colorScheme.onTertiary
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .background(
                        color = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.3f),
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "LOGIN",
                    color = MaterialTheme.colorScheme.onTertiary,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 24.dp)
                )

                val textFieldModifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .padding(top = 16.dp)

                KikoOutlinedTextField(
                    label = "Digite o Usuário",
                    value = username,
                    onValueChange = { username = it },
                    modifier = textFieldModifier,
                    leadingIcon = Icons.Filled.Person
                )

                KikoOutlinedTextField(
                    label = "Digite a senha",
                    value = password,
                    onValueChange = { password = it },
                    modifier = textFieldModifier,
                    leadingIcon = Icons.Filled.Lock,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                KikoExtraButton(
                    text = "ACESSAR",
                    onClick = {
                        scope.launch {
                            if (username.isBlank() || password.isBlank()) {
                                errorMessage = "Preencha todos os campos"
                                showErrorToast = true
                                delay(2000)
                                showErrorToast = false
                                return@launch
                            }

                            if (viewModel != null) {
                                val usuarioBanco = viewModel.login(username, password)
                                if (usuarioBanco != null) {
                                    showSuccessToast = true
                                    delay(1500)
                                    showSuccessToast = false

                                    navController?.navigate(Screen.Main.route) {
                                        popUpTo(Screen.Login.route) { inclusive = true }
                                    }
                                } else {
                                    errorMessage = "Usuário ou senha inválido"
                                    showErrorToast = true
                                    username = ""
                                    password = ""
                                    delay(2000)
                                    showErrorToast = false
                                }
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(top = 32.dp)
                )

                // 🔹 ESQUECEU A SENHA sempre aparece
                Text(
                    text = "Esqueceu a senha?",
                    color = MaterialTheme.colorScheme.onTertiary,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .clickable { navController?.navigate(Screen.Forgot.route) }
                )
            }
        }

        if (showErrorToast) {
            KikoErrorToast(
                message = errorMessage,
                modifier = Modifier.align(Alignment.TopCenter).padding(top = 16.dp)
            )
        }

        if (showSuccessToast) {
            KikoSuccessToast(
                message = "Bem-vindo!",
                modifier = Modifier.align(Alignment.TopCenter).padding(top = 16.dp)
            )
        }
    }
}


@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Preview(showBackground = true, widthDp = 411, heightDp = 891)
@Preview(showBackground = true, widthDp = 800, heightDp = 1280)
@Composable
fun LoginScreenLightPreview() {
    KareerAITheme (darkTheme = false) {
        LoginScreen()
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Preview(showBackground = true, widthDp = 411, heightDp = 891)
@Preview(showBackground = true, widthDp = 800, heightDp = 1280)
@Composable
fun LoginScreenDarkPreview() {
    KareerAITheme (darkTheme = true) {
        LoginScreen()
    }
}
