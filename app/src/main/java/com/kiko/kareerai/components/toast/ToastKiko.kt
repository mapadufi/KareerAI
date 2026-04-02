package com.kiko.kareerai.components.toast

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kiko.kareerai.ui.theme.KareerAITheme

@Composable
fun KikoSuccessToast(
    message: String,
    modifier: Modifier = Modifier
) {
    val backgroundColor = Color(0xFF08830C).copy(alpha = 0.7f)
    val textColor = Color(0xFFFFFFFF)

    Row(
        modifier = modifier
            .background(color = backgroundColor, shape = MaterialTheme.shapes.medium)
            .border(width = 1.dp, color = backgroundColor, shape = MaterialTheme.shapes.medium)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.CheckCircle,
            contentDescription = "Success",
            tint = textColor,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = message,
            color = textColor,
            fontSize = 16.sp
        )
    }
}

@Composable
fun KikoErrorToast(
    message: String,
    modifier: Modifier = Modifier
) {
    val backgroundColor = Color(0xFFB10303).copy(alpha = 0.7f)
    val textColor = Color(0xFFFFFFFF)

    Row(
        modifier = modifier
            .background(color = backgroundColor, shape = MaterialTheme.shapes.medium)
            .border(width = 1.dp, color = backgroundColor, shape = MaterialTheme.shapes.medium)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.Error,
            contentDescription = "Error",
            tint = textColor,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = message,
            color = textColor,
            fontSize = 16.sp
        )
    }
}

@Composable
fun KikoAttentionToast(
    message: String,
    modifier: Modifier = Modifier
) {
    val backgroundColor = Color(0xFFA48C04).copy(alpha = 0.7f)
    val textColor = Color(0xFFFFFFFF)

    Row(
        modifier = modifier
            .background(color = backgroundColor, shape = MaterialTheme.shapes.medium)
            .border(width = 1.dp, color = backgroundColor, shape = MaterialTheme.shapes.medium)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.Warning,
            contentDescription = "Attention",
            tint = textColor,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = message,
            color = textColor,
            fontSize = 16.sp
        )
    }
}

@Composable
fun KikoDeleteToast(
    message: String,
    modifier: Modifier = Modifier
) {
    val backgroundColor = Color(0xFF000000).copy(alpha = 0.7f)
    val textColor = Color(0xFFFFFFFF)

    Row(
        modifier = modifier
            .background(color = backgroundColor, shape = MaterialTheme.shapes.medium)
            .border(width = 1.dp, color = backgroundColor, shape = MaterialTheme.shapes.medium)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = "Attention",
            tint = textColor,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = message,
            color = textColor,
            fontSize = 16.sp
        )
    }
}

@Composable
private fun ToastGroupPreview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        KikoSuccessToast(message = "Operação realizada com sucesso!")
        KikoErrorToast(message = "Erro ao cadastrar!")
        KikoAttentionToast(message = "Atenção!")
        KikoDeleteToast(message = "Dados excluídos com sucesso!")
    }
}

// ==============================
// Previews Light
// ==============================
@Preview(showBackground = true, name = "Toast Light")
@Composable
fun ToastKikoPreviewLight() {
    KareerAITheme (darkTheme = false) {
        ToastGroupPreview()
    }
}

// ==============================
// Previews Dark
// ==============================
@Preview(showBackground = true, name = "Toast Dark")
@Composable
fun ToastKikoPreviewDark() {
    KareerAITheme (darkTheme = true) {
        ToastGroupPreview()
    }
}
