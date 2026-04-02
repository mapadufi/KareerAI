package com.kiko.kareerai.ui.screen.forgot

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.telephony.SmsManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.kiko.kareerai.components.buttons.KikoExtraButton
import com.kiko.kareerai.components.toast.KikoErrorToast
import com.kiko.kareerai.components.toast.KikoSuccessToast
import com.kiko.kareerai.navigation.Screen
import com.kiko.kareerai.viewmodel.UsuarioViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ForgotScreen(
    navController: NavController? = null,
    viewModel: UsuarioViewModel? = null
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val backgroundColor = MaterialTheme.colorScheme.surface
    val textColor = MaterialTheme.colorScheme.tertiary

    var phone by remember { mutableStateOf("") }
    var generatedCode by remember { mutableStateOf("") }
    var showSuccessToast by remember { mutableStateOf(false) }
    var showErrorToast by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    // Launcher para pedir permissão de SMS
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Se o usuário acabou de permitir, ele deve clicar novamente para enviar
            // ou você pode disparar a função de envio aqui
        } else {
            errorMessage = "Permissão de SMS negada. Não podemos enviar o código."
            showErrorToast = true
        }
    }

    LaunchedEffect(viewModel) {
        viewModel?.let { vm ->
            val usuarioPhone = vm.getUsuarioTelefone()
            if (!usuarioPhone.isNullOrEmpty()) {
                phone = if (usuarioPhone.startsWith("55")) usuarioPhone else "55$usuarioPhone"
            }
        }
    }

    fun enviarSms() {
        try {
            generatedCode = (100000..999999).random().toString()
            val smsManager: SmsManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                context.getSystemService(SmsManager::class.java)
            } else {
                @Suppress("DEPRECATION")
                SmsManager.getDefault()
            }

            smsManager.sendTextMessage(phone, null, "Código Zeus: $generatedCode", null, null)

            scope.launch {
                showSuccessToast = true
                delay(1500)
                navController?.navigate(Screen.RecoverUser.createRoute(generatedCode))
            }
        } catch (e: Exception) {
            errorMessage = "Erro ao enviar: ${e.message}"
            showErrorToast = true
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(backgroundColor)) {
        Box(
            modifier = Modifier.fillMaxWidth().height(80.dp).background(
                brush = Brush.verticalGradient(colors = listOf(Color(0xFF003399), Color(0xFF0048FF)))
            ),
            contentAlignment = Alignment.Center
        ) {
            Text("Recuperação de Senha", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(top = 96.dp, start = 24.dp, end = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "O código de recuperação será enviado para o telefone cadastrado.",
                style = MaterialTheme.typography.bodyMedium, color = textColor
            )

            Spacer(modifier = Modifier.height(24.dp))

            KikoExtraButton(
                text = "ENVIAR CÓDIGO",
                onClick = {
                    if (phone.isEmpty()) {
                        errorMessage = "Telefone não encontrado no cadastro."
                        showErrorToast = true
                        return@KikoExtraButton
                    }

                    // Verifica se já tem permissão
                    when (ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS)) {
                        PackageManager.PERMISSION_GRANTED -> {
                            enviarSms()
                        }
                        else -> {
                            // Pede permissão em tempo de execução
                            requestPermissionLauncher.launch(Manifest.permission.SEND_SMS)
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            KikoExtraButton(
                text = "VOLTAR",
                onClick = { navController?.popBackStack() },
                modifier = Modifier.fillMaxWidth()
            )
        }

        if (showSuccessToast) {
            KikoSuccessToast(message = "Código enviado com sucesso!", modifier = Modifier.align(Alignment.TopCenter).padding(top = 96.dp))
        }

        if (showErrorToast) {
            KikoErrorToast(message = errorMessage, modifier = Modifier.align(Alignment.TopCenter).padding(top = 96.dp))
            LaunchedEffect(showErrorToast) { delay(3000); showErrorToast = false }
        }
    }
}
