package com.kiko.kareerai.ui.screen.jobs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kiko.kareerai.components.layout.LayoutKiko
import com.kiko.kareerai.data.local.entity.Job
import com.kiko.kareerai.viewmodel.UsuarioViewModel

@Composable
fun JobListScreen(
    navController: NavController,
    usuarioViewModel: UsuarioViewModel,
    jobs: List<Job>,
    onClickJob: (Job) -> Unit
) {
    LayoutKiko(
        navController = navController,
        usuarioViewModel = usuarioViewModel,
        title = "Vagas Encontradas"
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            if (jobs.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("🔍", fontSize = 48.sp)
                        Spacer(Modifier.height(16.dp))
                        Text("Nenhuma vaga encontrada para o seu perfil.", color = Color.Gray)
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        Text(
                            text = "Vagas compatíveis com seu perfil",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }
                    items(jobs) { job ->
                        JobCard(job = job, onClick = { onClickJob(job) })
                    }
                }
            }
        }
    }
}

@Composable
fun JobCard(job: Job, onClick: () -> Unit) {
    val matchColor = if (job.matchScore >= 80) Color(0xFF105715) else Color(0xFF62188B)
    val bgColor = if (job.matchScore >= 80) Color(0xFFCCFFC8) else Color(0xFFF5E7FF)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = job.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                
                Spacer(Modifier.height(4.dp))
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Business, null, modifier = Modifier.size(14.dp), tint = Color.Gray)
                    Spacer(Modifier.width(4.dp))
                    Text(job.company, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.LocationOn, null, modifier = Modifier.size(14.dp), tint = Color.Gray)
                    Spacer(Modifier.width(4.dp))
                    Text(job.location, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                }
            }

            // Badge de Match da IA
            Surface(
                color = bgColor,
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Psychology, null, modifier = Modifier.size(16.dp), tint = matchColor)
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = "${job.matchScore}%",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = matchColor
                    )
                }
            }
        }
    }
}
