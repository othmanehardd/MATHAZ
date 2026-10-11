package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.MathDefinition
import com.example.model.MathExample
import com.example.model.MathTheorem
import com.example.ui.theme.ElectricBlue
import com.example.ui.theme.TurquoisePrimary
import com.example.ui.theme.VioletAccent

@Composable
fun TheoremCard(
  theorem: MathTheorem,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier.fillMaxWidth(),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAFC)),
    border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(Color(0xFF93C5FD)))
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Box(
          modifier = Modifier
            .size(28.dp)
            .background(ElectricBlue, RoundedCornerShape(8.dp)),
          contentAlignment = Alignment.Center
        ) {
          Text("★", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
        Text(
          text = theorem.title,
          style = MaterialTheme.typography.titleMedium,
          fontWeight = FontWeight.Bold,
          color = Color(0xFF1E3A8A)
        )
      }

      Spacer(modifier = Modifier.height(10.dp))

      Text(
        text = theorem.statement,
        style = MaterialTheme.typography.bodyMedium,
        color = Color(0xFF1E293B),
        lineHeight = 22.sp
      )

      Spacer(modifier = Modifier.height(12.dp))

      // Formula Highlight Box
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(12.dp))
          .background(Color(0xFFDBEAFE))
          .border(1.5.dp, ElectricBlue, RoundedCornerShape(12.dp))
          .padding(vertical = 12.dp, horizontal = 16.dp),
        contentAlignment = Alignment.Center
      ) {
        Text(
          text = theorem.formula,
          fontFamily = FontFamily.Monospace,
          fontWeight = FontWeight.Bold,
          fontSize = 18.sp,
          color = Color(0xFF1D4ED8)
        )
      }

      if (!theorem.conditions.isNullOrBlank()) {
        Spacer(modifier = Modifier.height(10.dp))
        Row(
          verticalAlignment = Alignment.Top,
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Icon(
            imageVector = Icons.Default.Info,
            contentDescription = null,
            tint = Color(0xFF2563EB),
            modifier = Modifier.size(16.dp).padding(top = 2.dp)
          )
          Text(
            text = theorem.conditions,
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFF3B82F6),
            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
          )
        }
      }
    }
  }
}

@Composable
fun DefinitionCard(
  definition: MathDefinition,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier.fillMaxWidth(),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFFF0FDFA)),
    border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(Color(0xFF99F6E4)))
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Box(
          modifier = Modifier
            .size(28.dp)
            .background(TurquoisePrimary, RoundedCornerShape(8.dp)),
          contentAlignment = Alignment.Center
        ) {
          Text("Def", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
        }
        Text(
          text = definition.term,
          style = MaterialTheme.typography.titleMedium,
          fontWeight = FontWeight.Bold,
          color = Color(0xFF115E59)
        )
      }

      Spacer(modifier = Modifier.height(8.dp))

      Text(
        text = definition.explanation,
        style = MaterialTheme.typography.bodyMedium,
        color = Color(0xFF134E4A),
        lineHeight = 22.sp
      )

      if (!definition.formula.isNullOrBlank()) {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
          text = definition.formula,
          fontFamily = FontFamily.Monospace,
          fontWeight = FontWeight.SemiBold,
          fontSize = 14.sp,
          color = Color(0xFF0F766E),
          modifier = Modifier
            .background(Color(0xFFCCFBF1), RoundedCornerShape(6.dp))
            .padding(horizontal = 10.dp, vertical = 6.dp)
        )
      }
    }
  }
}

@Composable
fun WorkedExampleCard(
  example: MathExample,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier.fillMaxWidth(),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = Color.White),
    border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(Color(0xFFE2E8F0)))
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Icon(
          imageVector = Icons.Default.Lightbulb,
          contentDescription = null,
          tint = Color(0xFFD97706),
          modifier = Modifier.size(22.dp)
        )
        Text(
          text = example.title,
          style = MaterialTheme.typography.titleMedium,
          fontWeight = FontWeight.Bold,
          color = Color(0xFF0F172A)
        )
      }

      Spacer(modifier = Modifier.height(8.dp))

      Text(
        text = "Énoncé : ${example.problem}",
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Medium,
        color = Color(0xFF334155),
        lineHeight = 22.sp
      )

      Spacer(modifier = Modifier.height(12.dp))

      Text(
        text = "Résolution détaillée pas à pas :",
        style = MaterialTheme.typography.bodySmall,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF64748B)
      )

      Spacer(modifier = Modifier.height(6.dp))

      example.steps.forEach { step ->
        Row(
          modifier = Modifier.padding(vertical = 3.dp),
          verticalAlignment = Alignment.Top
        ) {
          Text("▸ ", color = ElectricBlue, fontWeight = FontWeight.Bold)
          Text(
            text = step,
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFF1E293B),
            lineHeight = 20.sp
          )
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      Box(
        modifier = Modifier
          .fillMaxWidth()
          .background(Color(0xFFF0FDF4), RoundedCornerShape(8.dp))
          .border(1.dp, Color(0xFF86EFAC), RoundedCornerShape(8.dp))
          .padding(10.dp)
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            imageVector = Icons.Default.Check,
            contentDescription = null,
            tint = Color(0xFF16A34A),
            modifier = Modifier.size(18.dp)
          )
          Spacer(modifier = Modifier.width(6.dp))
          Text(
            text = "Résultat final : ${example.finalAnswer}",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF15803D)
          )
        }
      }
    }
  }
}

@Composable
fun CommonMistakesCard(
  mistakes: List<String>,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier.fillMaxWidth(),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF1F2)),
    border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(Color(0xFFFECDD3)))
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Icon(
          imageVector = Icons.Default.Warning,
          contentDescription = null,
          tint = Color(0xFFE11D48),
          modifier = Modifier.size(22.dp)
        )
        Text(
          text = "Pièges fréquents à éviter",
          style = MaterialTheme.typography.titleMedium,
          fontWeight = FontWeight.Bold,
          color = Color(0xFF9F1239)
        )
      }

      Spacer(modifier = Modifier.height(10.dp))

      mistakes.forEach { mistake ->
        Row(
          modifier = Modifier.padding(vertical = 4.dp),
          verticalAlignment = Alignment.Top
        ) {
          Text("⚠️ ", fontSize = 13.sp)
          Text(
            text = mistake,
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFF881337),
            lineHeight = 20.sp
          )
        }
      }
    }
  }
}
