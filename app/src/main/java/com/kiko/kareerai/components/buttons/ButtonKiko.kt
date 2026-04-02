package com.kiko.kareerai.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kiko.kareerai.ui.theme.KareerAITheme
import com.kiko.kareerai.ui.theme.*

/** -------------------- Extra Button (Flat, com borda) -------------------- */
@Composable
fun KikoExtraButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(
            // Fundo semi-transparente
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.6f),
            // Cor do texto e ícones
            contentColor = MaterialTheme.colorScheme.primary
        ),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.tertiary)
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.tertiary
        )
    }
}


/** -------------------- Text Button -------------------- */
@Composable
fun KikoTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String = "Text Button"
) {
    TextButton(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(15.dp) // shape padronizado
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.tertiary
        )
    }
}

/** -------------------- Base Gradient Button -------------------- */
@Composable
fun KikoGradientButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textColor: Color = BrancoApp,
    gradientColors: List<Color>
) {
    Box(
        modifier = modifier
            .background(
                brush = Brush.verticalGradient(colors = gradientColors),
                shape = RoundedCornerShape(15.dp)
            )
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp, horizontal = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

/** -------------------- Predefined Gradient Buttons -------------------- */
@Composable
fun KikoAzulButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    KikoGradientButton(
        text = text,
        onClick = onClick,
        modifier = modifier,
        gradientColors = listOf(Primary700, Primary900)
    )
}

@Composable
fun KikoVermelhoButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    KikoGradientButton(
        text = text,
        onClick = onClick,
        modifier = modifier,
        gradientColors = listOf(Error700, Error900)
    )
}

@Composable
fun KikoVerdeButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    KikoGradientButton(
        text = text,
        onClick = onClick,
        modifier = modifier,
        gradientColors = listOf(Success700, Success900)
    )
}

@Composable
private fun ButtonGroupPreview() {
    Column {
        KikoExtraButton(
            text = "Extra",
            onClick = {}
        )
        KikoTextButton(onClick = {})
        KikoAzulButton(
            text = "Entrar",
            onClick = {}
        )
        KikoVermelhoButton(
            text = "Cancelar",
            onClick = {}
        )
        KikoVerdeButton(
            text = "Salvar",
            onClick = {}
        )
    }
}

// ==============================
// Previews Light
// ==============================
@Preview(showBackground = true, name = "Button Light")
@Composable
fun PreviewKikoButtonLight() {
    KareerAITheme(darkTheme = false) {
        ButtonGroupPreview()
    }
}

// ==============================
// Previews Dark
// ==============================
@Preview(showBackground = true, name = "Button Dark")
@Composable
fun PreviewKikoButtonDark() {
    KareerAITheme(darkTheme = true) {
        ButtonGroupPreview()
    }
}
