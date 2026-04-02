package com.kiko.kareerai.components.outlined

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kiko.kareerai.ui.theme.KareerAITheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KikoOutlinedTextField(
    label: String = "Digite algo",
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    isError: Boolean = false,
    supportingText: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE
) {
    val colors = MaterialTheme.colorScheme

    val selectionColors = TextSelectionColors(
        handleColor = colors.tertiary,
        backgroundColor = colors.tertiary.copy(alpha = 0.4f)
    )

    CompositionLocalProvider(LocalTextSelectionColors provides selectionColors) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            modifier = modifier.fillMaxWidth(),
            singleLine = singleLine,
            maxLines = maxLines,
            isError = isError,
            shape = RoundedCornerShape(16.dp),
            enabled = enabled,
            leadingIcon = leadingIcon?.let {
                {
                    Icon(
                        imageVector = it,
                        contentDescription = "$label Icon",
                        tint = if (isError) colors.error else colors.tertiary
                    )
                }
            },
            supportingText = supportingText?.let {
                {
                    Text(
                        text = it,
                        color = if (isError) colors.error else colors.tertiary
                    )
                }
            },
            colors = kikoTextFieldColors(isError = isError),
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions
        )
    }
}

@Composable
private fun TextFieldGroupPreview() {
    Column(modifier = Modifier.padding(16.dp)) {
        KikoOutlinedTextField(label = "Usuário", value = "kiko", onValueChange = {}, leadingIcon = Icons.Filled.Person)
        KikoOutlinedTextField(label = "Desabilitado", value = "kiko", onValueChange = {}, leadingIcon = Icons.Filled.Person, enabled = false)
        KikoOutlinedTextField(label = "Erro", value = "", onValueChange = {}, leadingIcon = Icons.Filled.Person, isError = true, supportingText = "Campo obrigatório")
    }
}

// ==============================
// Previews Light
// ==============================
@Preview(showBackground = true, name = "Outlined Light")
@Composable
fun KikoOutlinedTextFieldPreviewLight() {
    KareerAITheme (darkTheme = false) {
        TextFieldGroupPreview()
    }
}

// ==============================
// Previews Dark
// ==============================
@Preview(showBackground = true, name = "Outlined Dark")
@Composable
fun KikoOutlinedTextFieldPreviewDark() {
    KareerAITheme (darkTheme = true) {
        TextFieldGroupPreview()
    }
}
