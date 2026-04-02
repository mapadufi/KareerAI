package com.kiko.kareerai.ui.screen.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.kiko.kareerai.components.layout.LayoutKiko
import com.kiko.kareerai.components.layout.getDrawerItems
import com.kiko.kareerai.navigation.Screen
import com.kiko.kareerai.ui.theme.*
import com.kiko.kareerai.viewmodel.ThemeViewModel
import com.kiko.kareerai.viewmodel.UsuarioViewModel

@Composable
fun MainScreen(
    navController: NavController? = null,
    usuarioViewModel: UsuarioViewModel? = null,
    themeViewModel: ThemeViewModel = viewModel()
) {
    val isDarkTheme by themeViewModel.isDarkMode.collectAsState()
    val backgroundColor = if (isDarkTheme) Dark950 else BrancoApp

    LayoutKiko(
        navController = navController,
        usuarioViewModel = usuarioViewModel,
        title = "Kareer AI"
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Bem-vindo ao KareerAI",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Seu assistente inteligente de carreira",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.7f)
            )

            Spacer(modifier = Modifier.height(32.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    MainCard(
                        title = "Enviar Currículo",
                        subtitle = "Análise por IA",
                        icon = Icons.Default.Description,
                        backgroundColor = Color(0xFFD1ECFF),
                        contentColor = Color(0xFF003399),
                        border = BorderStroke(2.dp, Color(0xFF003399)),
                        onClick = { 
                            navController?.navigate(Screen.UploadCurriculum.route)
                        }
                    )
                }
                item {
                    MainCard(
                        title = "Ver Resultado",
                        subtitle = "Feedback IA",
                        icon = Icons.Default.ListAlt,
                        backgroundColor = Color(0xFFF5E7FF),
                        contentColor = Color(0xFF62188B),
                        border = BorderStroke(2.dp, Color(0xFF62188B)),
                        onClick = { 
                            navController?.navigate(Screen.CurriculumResult.route)
                        }
                    )
                }
                item {
                    MainCard(
                        title = "Buscar Vagas",
                        subtitle = "Encontre jobs",
                        icon = Icons.Default.Search,
                        backgroundColor = Color(0xFFCCFFC8),
                        contentColor = Color(0xFF105715),
                        border = BorderStroke(2.dp, Color(0xFF105715)),
                        onClick = { 
                            navController?.navigate(Screen.JobList.route)
                        }
                    )
                }
                item {
                    MainCard(
                        title = "Vagas Salvas",
                        subtitle = "Seus favoritos",
                        icon = Icons.Default.Star,
                        backgroundColor = Color(0xFFFFDDDD),
                        contentColor = Color(0xFF920A0A),
                        border = BorderStroke(2.dp, Color(0xFF920A0A)),
                        onClick = { 
                            navController?.navigate(Screen.SavedJobs.route)
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Dica do dia: Mantenha seu currículo sempre atualizado para melhores resultados com a IA.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }
            }
        }
    }
}

@Composable
fun MainCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    backgroundColor: Color,
    contentColor: Color = Color.White,
    border: BorderStroke? = null,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        border = border
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(contentColor.copy(alpha = 0.2f), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = contentColor,
                    modifier = Modifier.size(24.dp)
                )
            }

            Column {
                Text(
                    text = title,
                    color = contentColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = subtitle,
                    color = contentColor.copy(alpha = 0.8f),
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Main Light")
@Composable
fun MainScreenPreviewLight() {
    KareerAITheme(darkTheme = false) {
        MainScreen()
    }
}

@Preview(showBackground = true, name = "Main Dark")
@Composable
fun MainScreenPreviewDark() {
    KareerAITheme(darkTheme = true) {
        MainScreen()
    }
}
