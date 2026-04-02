package com.kiko.kareerai.ui.screen.curriculum

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kiko.kareerai.components.buttons.KikoExtraButton
import com.kiko.kareerai.components.layout.LayoutKiko
import com.kiko.kareerai.components.outlined.KikoOutlinedTextField
import com.kiko.kareerai.data.model.Perfil
import com.kiko.kareerai.viewmodel.UsuarioViewModel

@Composable
fun CurriculumResultScreen(
    navController: NavController? = null,
    usuarioViewModel: UsuarioViewModel? = null,
    perfil: Perfil,
    onBuscarVagas: (Perfil) -> Unit
) {
    var pais by remember { mutableStateOf("") }
    var cidade by remember { mutableStateOf("") }

    LayoutKiko(
        navController = navController,
        usuarioViewModel = usuarioViewModel,
        title = "Refinar Busca"
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFD1ECFF)),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = Color(0xFF003399),
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = "Análise concluída!",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF003399)
                    )
                    Text(
                        text = "Confirme sua localização para encontrarmos as melhores vagas para você.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF003399).copy(alpha = 0.7f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Onde você deseja trabalhar?",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Box(modifier = Modifier.weight(1f)) {
                    KikoOutlinedTextField(
                        label = "País",
                        value = pais,
                        onValueChange = { pais = it },
                        leadingIcon = Icons.Default.Public
                    )
                }
                Box(modifier = Modifier.weight(1f)) {
                    KikoOutlinedTextField(
                        label = "Cidade",
                        value = cidade,
                        onValueChange = { cidade = it },
                        leadingIcon = Icons.Default.LocationCity
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Perfil Identificado:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(8.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Surface(
                    color = Color(0xFFF5E7FF),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "${perfil.cargo} (${perfil.nivel})",
                        color = Color(0xFF62188B),
                        modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            KikoExtraButton(
                text = "BUSCAR VAGAS NESTE LOCAL",
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    val perfilAtualizado = perfil.copy(
                        localizacao = if (cidade.isNotBlank()) "$cidade, $pais" else pais
                    )
                    onBuscarVagas(perfilAtualizado)
                }
            )
        }
    }
}
