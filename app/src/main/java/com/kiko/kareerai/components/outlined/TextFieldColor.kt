package com.kiko.kareerai.components.outlined

import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun kikoTextFieldColors(isError: Boolean = false): TextFieldColors {
    val colors = MaterialTheme.colorScheme
    val selectionColors = TextSelectionColors(
        handleColor = colors.tertiary,
        backgroundColor = colors.tertiary.copy(alpha = 0.4f)
    )

    return TextFieldDefaults.colors(
        focusedTextColor = colors.tertiary,
        unfocusedTextColor = colors.tertiary,
        disabledTextColor = colors.onSurface.copy(alpha = 0.38f),
        errorTextColor = colors.error,
        focusedContainerColor = colors.onPrimary,
        unfocusedContainerColor = colors.surface,
        disabledContainerColor = colors.surface,
        errorContainerColor = colors.onError,
        cursorColor = colors.tertiary,
        errorCursorColor = colors.error,
        selectionColors = selectionColors,
        focusedIndicatorColor = if (isError) colors.error else colors.tertiary,
        unfocusedIndicatorColor = if (isError) colors.error else colors.tertiary,
        disabledIndicatorColor = colors.tertiary.copy(alpha = 0.38f),
        errorIndicatorColor = colors.error,
        focusedLeadingIconColor = if (isError) colors.error else colors.tertiary,
        unfocusedLeadingIconColor = if (isError) colors.error else colors.tertiary,
        disabledLeadingIconColor = colors.tertiary.copy(alpha = 0.38f),
        errorLeadingIconColor = colors.error,
        focusedLabelColor = if (isError) colors.error else colors.tertiary,
        unfocusedLabelColor = colors.tertiary,
        disabledLabelColor = colors.tertiary.copy(alpha = 0.38f),
        errorLabelColor = colors.error,
        focusedSupportingTextColor = if (isError) colors.error else colors.tertiary,
        unfocusedSupportingTextColor = colors.tertiary,
        disabledSupportingTextColor = colors.tertiary.copy(alpha = 0.38f),
        errorSupportingTextColor = colors.error,
        focusedTrailingIconColor = colors.tertiary,
        unfocusedTrailingIconColor = colors.tertiary,
        disabledTrailingIconColor = colors.tertiary.copy(alpha = 0.38f),
        errorTrailingIconColor = colors.error
    )
}
