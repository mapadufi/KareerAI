package com.kiko.kareerai.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme

val DarkDefaultColorScheme = darkColorScheme(
    primary = Primary900,
    onPrimary = Dark700,
    primaryContainer = Dark700,
    onPrimaryContainer = Dark700,
    inversePrimary = Dark100,

    secondary = Primary700,
    onSecondary = Dark950,
    secondaryContainer = Dark700,
    onSecondaryContainer = Dark200,

    tertiary = Primary400,
    onTertiary = Primary400,
    tertiaryContainer = Dark700,
    onTertiaryContainer = Dark200,

    background = Dark900,
    onBackground = Dark100,

    surface = Dark950,
    onSurface = Dark100,
    surfaceVariant = Dark600,
    onSurfaceVariant = Dark300,
    surfaceTint = Dark950,

    inverseSurface = Dark900,
    inverseOnSurface = Dark100,

    error = Error900,
    onError = Error200,
    errorContainer = Error200,
    onErrorContainer = Error900,

    outline = Dark950,
    outlineVariant = Dark800,
    scrim = Dark700,

    )

val LightDefaultColorScheme = lightColorScheme(
    primary = Primary900,
    onPrimary = BrancoApp,
    primaryContainer = Alternative200,
    onPrimaryContainer = Primary900,
    inversePrimary = Primary50,

    secondary = Primary700,
    onSecondary = BrancoApp,
    secondaryContainer = Alternative100,
    onSecondaryContainer = Primary700,

    tertiary = Primary900,
    onTertiary = BrancoApp,
    tertiaryContainer = Primary100,
    onTertiaryContainer = Primary900,

    background = BrancoApp,
    onBackground = Primary900,

    surface = BrancoApp,
    onSurface = Dark950,
    surfaceVariant = Dark300,
    onSurfaceVariant = Dark600,
    surfaceTint = Primary900,

    inverseSurface = Dark900,
    inverseOnSurface = BrancoApp,

    error = Error900,
    onError = Error200,
    errorContainer = Error200,
    onErrorContainer = Error900,

    outline = Primary900,
    outlineVariant = Primary700,
    scrim = Primary100,

    )
