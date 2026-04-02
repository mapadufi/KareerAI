package com.kiko.kareerai.ui.screen.forgot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.text.input.KeyboardType
import com.kiko.kareerai.components.buttons.KikoExtraButton
import com.kiko.kareerai.components.outlined.KikoOutlinedTextField
import com.kiko.kareerai.components.toast.KikoErrorToast
import com.kiko.kareerai.ui.theme.*
import com.kiko.kareerai.ui.theme.KareerAITheme
import com.kiko.kareerai.viewmodel.UsuarioViewModel

@Composable
fun RecoverUserScreen(
    navController: NavController? = null, // opcional para Preview
    codigoGerado: String = "123456",     // valor padrão para testes/Preview
    viewModel: UsuarioViewModel? = null
) {
    var verificationCode by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        // TopBar com gradiente
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
                text = "Recuperação de Usuário",
                color = BrancoApp,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // BottomBar com gradiente
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(60.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF003399), Color(0xFF0061FF))
                    )
                )
        )

        // Conteúdo central
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(top = 80.dp, bottom = 60.dp)
                .padding(horizontal = 24.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Digite o código de verificação enviado para seu telefone",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onTertiary,
            )

            KikoOutlinedTextField(
                label = "Código de Verificação",
                value = verificationCode,
                onValueChange = { verificationCode = it },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number) // 🔹 apenas números
            )

            Spacer(modifier = Modifier.height(8.dp))

            KikoExtraButton(
                text = "Verificar Código",
                onClick = {
                    when {
                        verificationCode.isBlank() -> {
                            errorMessage = "Preencha o campo Verificação"
                            showError = true
                        }
                        verificationCode == codigoGerado -> {
                            showError = false
                            navController?.navigate("edit_user") {
                                // Navega removendo a tela de recover_user da pilha
                                popUpTo("recover_user/{codigo}") { inclusive = true }
                            }
                        }
                        else -> {
                            errorMessage = "Código não confere"
                            showError = true
                            verificationCode = ""
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            KikoExtraButton(
                text = "Voltar",
                onClick = { navController?.navigate("forgot") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Toast de erro
        if (showError) {
            KikoErrorToast(
                message = errorMessage,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 16.dp)
            )
        }
    }
}

// 🔹 Previews para UI
@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Preview(showBackground = true, widthDp = 411, heightDp = 891)
@Preview(showBackground = true, widthDp = 800, heightDp = 1280)
@Composable
fun RecoverUserScreenLightPreview() {
    KareerAITheme (darkTheme = false) {
        RecoverUserScreen(navController = rememberNavController())
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Preview(showBackground = true, widthDp = 411, heightDp = 891)
@Preview(showBackground = true, widthDp = 800, heightDp = 1280)
@Composable
fun RecoverUserScreenDarkPreview() {
    KareerAITheme (darkTheme = true) {
        RecoverUserScreen(navController = rememberNavController())
    }
}
