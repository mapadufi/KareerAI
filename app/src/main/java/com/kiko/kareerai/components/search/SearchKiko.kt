package com.kiko.kareerai.components.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kiko.kareerai.ui.theme.KareerAITheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KikoSearchBar(
    hint: String = "Pesquisar...",
    onSearch: (String) -> Unit = {},
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.surface
) {
    var text by remember { mutableStateOf("") }
    val colors = MaterialTheme.colorScheme
    val selectionColors = LocalTextSelectionColors.current
    val isError = false

    Box(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(56.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(28.dp)
                )
                .border(
                    width = 1.dp,
                    color = if (isError) colors.error else colors.tertiary,
                    shape = RoundedCornerShape(28.dp)
                )
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search icon",
                tint = if (isError) colors.error else colors.tertiary
            )

            Spacer(modifier = Modifier.width(8.dp))

            TextField(
                value = text,
                onValueChange = {
                    text = it
                    onSearch(it)
                },
                placeholder = {
                    Text(text = hint, color = colors.onSurface.copy(alpha = 0.6f))
                },
                isError = isError,
                singleLine = true,
                maxLines = 1,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = backgroundColor,
                    unfocusedContainerColor = backgroundColor,
                    disabledContainerColor = backgroundColor,
                    errorContainerColor = backgroundColor,
                    focusedTextColor = colors.tertiary,
                    unfocusedTextColor = colors.onSurface,
                    cursorColor = colors.tertiary,
                    errorCursorColor = colors.error,
                    selectionColors = selectionColors,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                shape = RoundedCornerShape(28.dp)
            )

            // Ícone de limpar (X) ao invés do microfone
            if (text.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Limpar pesquisa",
                    tint = colors.tertiary,
                    modifier = Modifier
                        .clickable {
                            text = ""
                            onSearch("")
                        }
                        .padding(4.dp)
                )
            }
        }
    }
}

// ==============================
// Previews Light
// ==============================
@Preview(showBackground = true, name = "SearchBar Light")
@Composable
fun SearchBarKikoPreviewLight() {
    KareerAITheme (darkTheme = false) {
        KikoSearchBar(backgroundColor = MaterialTheme.colorScheme.surface)
    }
}

// ==============================
// Previews Dark
// ==============================
@Preview(showBackground = true, name = "SearchBar Dark")
@Composable
fun SearchBarKikoPreviewDark() {
    KareerAITheme (darkTheme = true) {
        KikoSearchBar(backgroundColor = MaterialTheme.colorScheme.surface)
    }
}
