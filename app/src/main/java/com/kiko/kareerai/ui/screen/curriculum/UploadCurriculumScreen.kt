package com.kiko.kareerai.ui.screen.curriculum

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kiko.kareerai.components.buttons.KikoExtraButton
import com.kiko.kareerai.components.layout.LayoutKiko
import com.kiko.kareerai.components.progress.ProgressKiko
import com.kiko.kareerai.viewmodel.UsuarioViewModel

@Composable
fun UploadCurriculumScreen(
    navController: NavController,
    usuarioViewModel: UsuarioViewModel,
    isLoading: Boolean = false,
    onAnalisarCurriculo: (String) -> Unit
) {
    var curriculoTexto by remember { mutableStateOf("") }
    var fileName by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    // Launcher para selecionar arquivos
    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            fileName = it.lastPathSegment
            // Aqui você integraria uma lógica para ler o conteúdo do PDF/DOCX
            // Por enquanto, vamos simular que o texto foi extraído
            curriculoTexto = "Conteúdo extraído do arquivo: $fileName"
        }
    }

    LayoutKiko(
        navController = navController,
        usuarioViewModel = usuarioViewModel,
        title = "Análise de Currículo"
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.CloudUpload,
                    contentDescription = null,
                    modifier = Modifier.size(80.dp),
                    tint = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Turbine sua carreira com IA",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Selecione seu arquivo ou cole o texto do seu currículo para que nossa IA faça o match com as melhores vagas.",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    color = Color.Gray,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Área de Upload de Arquivo
                OutlinedCard(
                    onClick = { filePickerLauncher.launch("application/*") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.large
                ) {
                    Row(
                        modifier = Modifier.padding(24.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Description, contentDescription = null)
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = fileName ?: "Selecionar PDF ou Word",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "OU", style = MaterialTheme.typography.labelSmall, color = Color.Gray)

                Spacer(modifier = Modifier.height(16.dp))

                // Área de Texto
                OutlinedTextField(
                    value = curriculoTexto,
                    onValueChange = { curriculoTexto = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 200.dp),
                    label = { Text("Cole seu currículo aqui") },
                    placeholder = { Text("Ex: Sou desenvolvedor Kotlin com 5 anos de experiência...") },
                    shape = MaterialTheme.shapes.medium
                )

                Spacer(modifier = Modifier.height(32.dp))

                KikoExtraButton(
                    text = "ANALISAR COM IA",
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        if (curriculoTexto.isNotBlank()) {
                            onAnalisarCurriculo(curriculoTexto)
                        }
                    }
                )
            }

            // Overlay de Loading
            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.5f)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        ProgressKiko(modifier = Modifier.size(64.dp))
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "A IA está analisando seu perfil...",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
