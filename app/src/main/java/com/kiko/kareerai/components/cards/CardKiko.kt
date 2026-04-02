package com.kiko.kareerai.components.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kiko.kareerai.ui.theme.KareerAITheme

@Composable
fun PersonCardKiko(
    modifier: Modifier = Modifier,
    avatarLetter: String = "A",
    avatarBackgroundColor: Color = MaterialTheme.colorScheme.tertiary,
    title: String = "Card Personalizado",
    subtitle: String = "Mostrando um Card personalizado",
    description: String = "Este tipo de card pode ser personalizado de várias maneiras."
) {
    Card(
        modifier = modifier.padding(32.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.tertiary),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(avatarBackgroundColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = avatarLetter,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.tertiaryContainer
                        )
                    )
                }

                IconButton(onClick = { /* ação do ícone */ }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Configurações",
                        tint = MaterialTheme.colorScheme.tertiary
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.tertiary
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.tertiary
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}

@Composable
fun CardsColumnKiko() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            PersonCardKiko()
        }
    }
}

// ==============================
// Previews Light
// ==============================
@Preview(showBackground = true, name = "Card Light")
@Composable
fun CardKikoPreviewLight() {
    KareerAITheme (darkTheme = false) {
        CardsColumnKiko()
    }
}

// ==============================
// Previews Dark
// ==============================
@Preview(showBackground = true, name = "Card Dark")
@Composable
fun CardKikoPreviewDark() {
    KareerAITheme (darkTheme = true) {
        CardsColumnKiko()
    }
}
