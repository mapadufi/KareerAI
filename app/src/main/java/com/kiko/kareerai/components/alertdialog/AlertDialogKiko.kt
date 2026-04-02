package com.kiko.kareerai.components.alertdialog

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.kiko.kareerai.components.buttons.KikoExtraButton
import com.kiko.kareerai.ui.theme.KareerAITheme


@Composable
fun KikoAlertDialog(
    onDismissRequest: () -> Unit,
    title: String,
    text: String,
    onConfirm: () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(4.dp, RoundedCornerShape(12.dp))
                .border(1.dp, MaterialTheme.colorScheme.tertiary, RoundedCornerShape(12.dp)),
            shape = RoundedCornerShape(15.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.tertiary),
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    KikoExtraButton(
                        onClick = onConfirm,
                        text = "OK"
                    )
                }
            }
        }
    }
}

// ==============================
// Previews Light
// ==============================
@Preview(showBackground = true, name = "AlertDialog Light")
@Composable
fun KikoAlertDialogPreviewLight() {
    KareerAITheme (darkTheme = false) {
        KikoAlertDialog(
            onDismissRequest = {},
            title = "Atenção",
            text = "Só será permitido selecionar imagens com extensão PNG",
            onConfirm = {}
        )
    }
}

// ==============================
// Previews Dark
// ==============================
@Preview(showBackground = true, name = "AlertDialog Dark")
@Composable
fun KikoAlertDialogPreviewDark() {
    KareerAITheme (darkTheme = true) {
        KikoAlertDialog(
            onDismissRequest = {},
            title = "Atenção",
            text = "Só será permitido selecionar imagens com extensão PNG",
            onConfirm = {}
        )
    }
}
