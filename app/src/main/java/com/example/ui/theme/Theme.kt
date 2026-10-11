package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
  primary = ElectricBlue,
  onPrimary = Color.White,
  primaryContainer = ElectricBlueContainer,
  onPrimaryContainer = DarkNavyText,
  secondary = TurquoisePrimary,
  onSecondary = Color.White,
  secondaryContainer = TurquoiseContainer,
  onSecondaryContainer = Color(0xFF134E4A),
  tertiary = VioletAccent,
  onTertiary = Color.White,
  tertiaryContainer = VioletContainer,
  onTertiaryContainer = Color(0xFF4C1D95),
  background = SlateSurface,
  onBackground = DarkNavyText,
  surface = CardBackground,
  onSurface = SlateTitle,
  surfaceVariant = Color(0xFFF1F5F9),
  onSurfaceVariant = SlateBody,
  outline = SlateBorder,
  outlineVariant = Color(0xFFCBD5E1)
)

private val DarkColorScheme = darkColorScheme(
  primary = ElectricBlueLight,
  onPrimary = Color(0xFF002244),
  primaryContainer = Color(0xFF1E3A8A),
  onPrimaryContainer = Color(0xFFDBEAFE),
  secondary = TurquoiseLight,
  onSecondary = Color(0xFF003734),
  secondaryContainer = Color(0xFF115E59),
  onSecondaryContainer = Color(0xFFCCFBF1),
  tertiary = VioletLight,
  onTertiary = Color(0xFF2E1065),
  tertiaryContainer = Color(0xFF5B21B6),
  onTertiaryContainer = Color(0xFFEDE9FE),
  background = Color(0xFF0B132B),
  onBackground = Color(0xFFF1F5F9),
  surface = Color(0xFF131D38),
  onSurface = Color(0xFFF8FAFC),
  surfaceVariant = Color(0xFF1E293B),
  onSurfaceVariant = Color(0xFFCBD5E1),
  outline = Color(0xFF334155),
  outlineVariant = Color(0xFF475569)
)

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = false, // Keep brand electric blue identity consistent
  content: @Composable () -> Unit,
) {
  val colorScheme = when {
    dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
      val context = LocalContext.current
      if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    }
    darkTheme -> DarkColorScheme
    else -> LightColorScheme
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    content = content
  )
}
