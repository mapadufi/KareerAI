package com.kiko.kareerai.ui.screen.jobs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Work
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
fun SavedJobsScreen(
    navController: NavController,
    usuarioViewModel: UsuarioViewModel,
    jobs: List<Job>,
    onRemoveJob: (Int) -> Unit,
    onClickJob: (Job) -> Unit
) {
    LayoutKiko(
        navController = navController,
        usuarioViewModel = usuarioViewModel,
        title = "Vagas Salvas"
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
                        Icon(Icons.Default.Work, null, modifier = Modifier.size(64.dp), tint = Color.LightGray)
                        Spacer(Modifier.height(16.dp))
                        Text("Você ainda não salvou nenhuma vaga.", color = Color.Gray)
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(jobs) { job ->
                        SavedJobCard(
                            job = job,
                            onClick = { onClickJob(job) },
                            onDelete = { onRemoveJob(job.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SavedJobCard(job: Job, onClick: () -> Unit, onDelete: () -> Unit) {
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
                    color = Color(0xFF920A0A) // Usando a cor do card de salvos
                )
                Text(job.company, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }

            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Remover", tint = Color.Gray)
            }
        }
    }
}
