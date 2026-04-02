package com.kiko.kareerai.ui.screen.jobs

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kiko.kareerai.components.buttons.KikoExtraButton
import com.kiko.kareerai.components.layout.LayoutKiko
import com.kiko.kareerai.components.progress.ProgressKiko
import com.kiko.kareerai.data.local.entity.Job
import com.kiko.kareerai.viewmodel.UsuarioViewModel

@Composable
fun JobDetailScreen(
    navController: NavController,
    usuarioViewModel: UsuarioViewModel,
    job: Job,
    pitch: String? = null,
    isGeneratingPitch: Boolean = false,
    onSalvar: (Job) -> Unit,
    onGerarPitch: () -> Unit,
    onLimparPitch: () -> Unit
) {
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current
    
    val matchColor = if (job.matchScore >= 80) Color(0xFF105715) else Color(0xFF62188B)
    val matchBg = if (job.matchScore >= 80) Color(0xFFCCFFC8) else Color(0xFFF5E7FF)

    DisposableEffect(Unit) {
        onDispose { onLimparPitch() }
    }

    LayoutKiko(
        navController = navController,
        usuarioViewModel = usuarioViewModel,
        title = "Detalhes da Vaga",
        onBack = { navController.popBackStack() }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = job.title,
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                            Spacer(Modifier.height(8.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Business, null, modifier = Modifier.size(18.dp), tint = Color.Gray)
                                Spacer(Modifier.width(8.dp))
                                Text(job.company, style = MaterialTheme.typography.bodyLarge, color = Color.Gray)
                            }
                        }

                        Surface(
                            color = matchBg,
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(12.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(Icons.Default.Psychology, null, tint = matchColor)
                                Text(
                                    text = "${job.matchScore}%",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = matchColor
                                )
                                Text("Match", fontSize = 10.sp, color = matchColor)
                            }
                        }
                    }

                    Spacer(Modifier.height(16.dp))
                    HorizontalDivider(modifier = Modifier.alpha(0.1f))
                    Spacer(Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        InfoItem(icon = Icons.Default.LocationOn, label = job.location)
                        InfoItem(
                            icon = Icons.Default.Router, 
                            label = if (job.isRemote) "Remoto" else "Presencial"
                        )
                    }
                }
            }

            if (isGeneratingPitch) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFD1ECFF))
                ) {
                    Row(
                        modifier = Modifier.padding(24.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        ProgressKiko(modifier = Modifier.size(24.dp))
                        Spacer(Modifier.width(16.dp))
                        Text("IA escrevendo sua apresentação...", color = Color(0xFF003399))
                    }
                }
            } else if (pitch != null) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFD1ECFF)),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.AutoAwesome, null, tint = Color(0xFF003399))
                            Spacer(Modifier.width(8.dp))
                            Text(
                                "Sua apresentação por IA",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF003399)
                            )
                        }
                        Spacer(Modifier.height(12.dp))
                        Text(
                            text = pitch,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF003399).copy(alpha = 0.8f)
                        )
                        Spacer(Modifier.height(16.dp))
                        
                        KikoExtraButton(
                            text = "COPIAR TEXTO",
                            onClick = { 
                                clipboardManager.setText(AnnotatedString(pitch))
                            },
                            modifier = Modifier.align(Alignment.End)
                        )
                    }
                }
            } else {
                KikoExtraButton(
                    text = "GERAR APRESENTAÇÃO COM IA",
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onGerarPitch
                )
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(
                        text = "Sobre a vaga",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                    Spacer(Modifier.height(12.dp))
                    Text(
                        text = job.description,
                        style = MaterialTheme.typography.bodyMedium,
                        lineHeight = 22.sp,
                        color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.8f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            KikoExtraButton(
                text = "CANDIDATAR-SE NO SITE",
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(job.url))
                    context.startActivity(intent)
                }
            )

            KikoExtraButton(
                text = "SALVAR PARA DEPOIS",
                modifier = Modifier.fillMaxWidth(),
                onClick = { onSalvar(job) }
            )
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun Modifier.alpha(alpha: Float): Modifier = this.then(Modifier.background(Color.Transparent.copy(alpha = alpha)))

@Composable
fun InfoItem(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, null, modifier = Modifier.size(16.dp), tint = Color.Gray)
        Spacer(Modifier.width(4.dp))
        Text(label, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
    }
}
