package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.DiagramType
import com.example.ui.theme.ElectricBlue
import com.example.ui.theme.TurquoisePrimary
import com.example.ui.theme.VioletAccent

@Composable
fun MathDiagramCard(
  diagramType: DiagramType,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .background(Color.White, RoundedCornerShape(16.dp))
      .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(16.dp))
      .padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = when (diagramType) {
        DiagramType.PYTHAGORAS_TRIANGLE -> "Figure géométrique : Triangle rectangle en A"
        DiagramType.THALES_CONFIG -> "Figure géométrique : Configuration de Thalès"
        DiagramType.CARTESIAN_FUNCTION -> "Repère cartésien : Droites des fonctions affines"
        DiagramType.TRIGONOMETRY_TRIANGLE -> "Triangle rectangle & Rapports trigonométriques"
        DiagramType.FRACTIONS_BAR -> "Représentation visuelle des fractions"
      },
      style = MaterialTheme.typography.titleSmall,
      fontWeight = FontWeight.Bold,
      color = Color(0xFF0F172A),
      modifier = Modifier.padding(bottom = 12.dp)
    )

    when (diagramType) {
      DiagramType.PYTHAGORAS_TRIANGLE, DiagramType.TRIGONOMETRY_TRIANGLE -> {
        PythagorasDiagram(modifier = Modifier.height(200.dp).fillMaxWidth())
      }
      DiagramType.THALES_CONFIG -> {
        ThalesDiagram(modifier = Modifier.height(200.dp).fillMaxWidth())
      }
      DiagramType.CARTESIAN_FUNCTION -> {
        CartesianFunctionDiagram(modifier = Modifier.height(200.dp).fillMaxWidth())
      }
      DiagramType.FRACTIONS_BAR -> {
        FractionsDiagram(modifier = Modifier.height(160.dp).fillMaxWidth())
      }
    }
  }
}

@Composable
fun PythagorasDiagram(modifier: Modifier = Modifier) {
  Canvas(modifier = modifier) {
    val width = size.width
    val height = size.height

    // Triangle vertices
    val pA = Offset(width * 0.25f, height * 0.78f) // Right angle at A
    val pB = Offset(width * 0.85f, height * 0.78f) // Base vertex B
    val pC = Offset(width * 0.25f, height * 0.18f) // Top vertex C

    // Draw triangle fill
    val trianglePath = Path().apply {
      moveTo(pA.x, pA.y)
      lineTo(pB.x, pB.y)
      lineTo(pC.x, pC.y)
      close()
    }
    drawPath(
      path = trianglePath,
      color = Color(0xFFEFF6FF)
    )
    drawPath(
      path = trianglePath,
      color = ElectricBlue,
      style = Stroke(width = 4f)
    )

    // Right angle mark at A
    val squareSize = 24f
    val rightAnglePath = Path().apply {
      moveTo(pA.x, pA.y - squareSize)
      lineTo(pA.x + squareSize, pA.y - squareSize)
      lineTo(pA.x + squareSize, pA.y)
    }
    drawPath(
      path = rightAnglePath,
      color = VioletAccent,
      style = Stroke(width = 3f)
    )

    // Vertex points
    drawCircle(color = ElectricBlue, radius = 6f, center = pA)
    drawCircle(color = ElectricBlue, radius = 6f, center = pB)
    drawCircle(color = ElectricBlue, radius = 6f, center = pC)

    // Text labels using nativeCanvas
    drawContext.canvas.nativeCanvas.apply {
      val paint = android.graphics.Paint().apply {
        color = android.graphics.Color.parseColor("#0F172A")
        textSize = 34f
        isFakeBoldText = true
      }
      drawText("A (90°)", pA.x - 70f, pA.y + 45f, paint)
      drawText("B", pB.x + 15f, pB.y + 10f, paint)
      drawText("C", pC.x - 30f, pC.y - 15f, paint)

      // Side labels
      val labelPaint = android.graphics.Paint().apply {
        color = android.graphics.Color.parseColor("#1D4ED8")
        textSize = 30f
        isFakeBoldText = true
      }
      drawText("c = Hypoténuse", (pB.x + pC.x) / 2 + 15f, (pB.y + pC.y) / 2 - 10f, labelPaint)
      drawText("b (côté)", pA.x - 65f, (pA.y + pC.y) / 2, labelPaint)
      drawText("a (base)", (pA.x + pB.x) / 2 - 20f, pA.y + 40f, labelPaint)
    }
  }
}

@Composable
fun ThalesDiagram(modifier: Modifier = Modifier) {
  Canvas(modifier = modifier) {
    val width = size.width
    val height = size.height

    val pA = Offset(width * 0.5f, height * 0.15f)
    val pB = Offset(width * 0.15f, height * 0.85f)
    val pC = Offset(width * 0.85f, height * 0.85f)

    // Intermediary points M and N
    val ratio = 0.55f
    val pM = Offset(pA.x + (pB.x - pA.x) * ratio, pA.y + (pB.y - pA.y) * ratio)
    val pN = Offset(pA.x + (pC.x - pA.x) * ratio, pA.y + (pC.y - pA.y) * ratio)

    // Main triangle
    val mainPath = Path().apply {
      moveTo(pA.x, pA.y)
      lineTo(pB.x, pB.y)
      lineTo(pC.x, pC.y)
      close()
    }
    drawPath(mainPath, color = Color(0xFFF0FDF4))
    drawPath(mainPath, color = TurquoisePrimary, style = Stroke(width = 3.5f))

    // Parallel line MN
    drawLine(
      color = VioletAccent,
      start = pM,
      end = pN,
      strokeWidth = 4f
    )

    // Parallel indicators
    drawCircle(color = VioletAccent, radius = 5f, center = pM)
    drawCircle(color = VioletAccent, radius = 5f, center = pN)
    drawCircle(color = TurquoisePrimary, radius = 5f, center = pA)
    drawCircle(color = TurquoisePrimary, radius = 5f, center = pB)
    drawCircle(color = TurquoisePrimary, radius = 5f, center = pC)

    drawContext.canvas.nativeCanvas.apply {
      val paint = android.graphics.Paint().apply {
        color = android.graphics.Color.parseColor("#0F172A")
        textSize = 32f
        isFakeBoldText = true
      }
      drawText("A", pA.x - 10f, pA.y - 15f, paint)
      drawText("M", pM.x - 40f, pM.y + 5f, paint)
      drawText("N", pN.x + 15f, pN.y + 5f, paint)
      drawText("B", pB.x - 35f, pB.y + 25f, paint)
      drawText("C", pC.x + 15f, pC.y + 25f, paint)

      val tagPaint = android.graphics.Paint().apply {
        color = android.graphics.Color.parseColor("#7C3AED")
        textSize = 28f
        isFakeBoldText = true
      }
      drawText("(MN) // (BC)", width * 0.5f - 70f, pM.y - 12f, tagPaint)
    }
  }
}

@Composable
fun CartesianFunctionDiagram(modifier: Modifier = Modifier) {
  Canvas(modifier = modifier) {
    val width = size.width
    val height = size.height

    val origin = Offset(width * 0.35f, height * 0.65f)

    // Grid lines subtle
    val gridColor = Color(0xFFF1F5F9)
    val step = 40f
    var x = 0f
    while (x < width) {
      drawLine(gridColor, Offset(x, 0f), Offset(x, height), strokeWidth = 1f)
      x += step
    }
    var y = 0f
    while (y < height) {
      drawLine(gridColor, Offset(0f, y), Offset(width, y), strokeWidth = 1f)
      y += step
    }

    // Axes
    val axisColor = Color(0xFF475569)
    // X Axis
    drawLine(axisColor, Offset(20f, origin.y), Offset(width - 20f, origin.y), strokeWidth = 3f)
    // Y Axis
    drawLine(axisColor, Offset(origin.x, height - 20f), Offset(origin.x, 20f), strokeWidth = 3f)

    // Line 1: Linear f(x) = 2x (passes origin)
    val fStart = Offset(origin.x - 100f, origin.y + 120f)
    val fEnd = Offset(origin.x + 160f, origin.y - 192f)
    drawLine(ElectricBlue, fStart, fEnd, strokeWidth = 4f)

    // Line 2: Affine g(x) = -0.8x + 60
    val gStart = Offset(origin.x - 120f, origin.y - 160f)
    val gEnd = Offset(origin.x + 220f, origin.y + 112f)
    drawLine(TurquoisePrimary, gStart, gEnd, strokeWidth = 4f)

    // Origin point
    drawCircle(Color(0xFF0F172A), radius = 5f, center = origin)

    drawContext.canvas.nativeCanvas.apply {
      val axisPaint = android.graphics.Paint().apply {
        color = android.graphics.Color.parseColor("#334155")
        textSize = 28f
        isFakeBoldText = true
      }
      drawText("x", width - 40f, origin.y - 10f, axisPaint)
      drawText("y", origin.x + 15f, 40f, axisPaint)
      drawText("O(0,0)", origin.x - 65f, origin.y + 35f, axisPaint)

      val fPaint = android.graphics.Paint().apply {
        color = android.graphics.Color.parseColor("#2563EB")
        textSize = 28f
        isFakeBoldText = true
      }
      drawText("f(x) = ax (Linéaire)", fEnd.x - 150f, fEnd.y + 20f, fPaint)

      val gPaint = android.graphics.Paint().apply {
        color = android.graphics.Color.parseColor("#0D9488")
        textSize = 28f
        isFakeBoldText = true
      }
      drawText("g(x) = ax + b (Affine)", gEnd.x - 160f, gEnd.y - 15f, gPaint)
    }
  }
}

@Composable
fun FractionsDiagram(modifier: Modifier = Modifier) {
  Canvas(modifier = modifier) {
    val width = size.width
    val height = size.height

    val barHeight = 36f
    val marginX = 40f
    val barWidth = width - (marginX * 2)

    // Bar 1: 3/4
    val y1 = height * 0.25f
    drawRect(Color(0xFFE2E8F0), Offset(marginX, y1), Size(barWidth, barHeight))
    val w34 = barWidth * (3f / 4f)
    drawRect(ElectricBlue, Offset(marginX, y1), Size(w34, barHeight))
    for (i in 1..3) {
      val lx = marginX + (barWidth * (i / 4f))
      drawLine(Color.White, Offset(lx, y1), Offset(lx, y1 + barHeight), strokeWidth = 2.5f)
    }

    // Bar 2: 7/8
    val y2 = height * 0.65f
    drawRect(Color(0xFFE2E8F0), Offset(marginX, y2), Size(barWidth, barHeight))
    val w78 = barWidth * (7f / 8f)
    drawRect(TurquoisePrimary, Offset(marginX, y2), Size(w78, barHeight))
    for (i in 1..7) {
      val lx = marginX + (barWidth * (i / 8f))
      drawLine(Color.White, Offset(lx, y2), Offset(lx, y2 + barHeight), strokeWidth = 2.5f)
    }

    drawContext.canvas.nativeCanvas.apply {
      val paint = android.graphics.Paint().apply {
        color = android.graphics.Color.parseColor("#0F172A")
        textSize = 26f
        isFakeBoldText = true
      }
      drawText("Fraction 3/4 = 6/8", marginX, y1 - 10f, paint)
      drawText("Somme 3/4 + 1/8 = 7/8", marginX, y2 - 10f, paint)
    }
  }
}
