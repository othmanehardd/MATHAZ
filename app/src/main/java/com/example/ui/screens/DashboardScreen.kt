package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Lesson
import com.example.model.SchoolLevel
import com.example.model.StudentProgress
import com.example.ui.theme.*

@Composable
fun DashboardScreen(
  progress: StudentProgress,
  allLessons: List<Lesson>,
  onContinueLearning: () -> Unit,
  onOpenLesson: (String) -> Unit,
  onOpenExercises: () -> Unit,
  modifier: Modifier = Modifier
) {
  val completedLessons = allLessons.filter { progress.completedLessonIds.contains(it.id) }
  val bookmarkedLessons = allLessons.filter { progress.bookmarkedResourceIds.contains(it.id) }

  // Level progress percentage
  val total1AC = 8f
  val total2AC = 8f
  val total3AC = 15f
  val completed3AC = completedLessons.count { it.level == SchoolLevel.AC3 }.toFloat()
  val pct3AC = ((completed3AC / total3AC) * 100).toInt().coerceAtMost(100)

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(SlateSurface),
    contentPadding = PaddingValues(bottom = 90.dp)
  ) {
    // Student Profile Header
    item {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .background(Color(0xFF0A2540))
          .padding(20.dp)
      ) {
        Column {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
          ) {
            Box(
              modifier = Modifier
                .size(54.dp)
                .background(ElectricBlue, CircleShape),
              contentAlignment = Alignment.Center
            ) {
              Text("🇲🇦", fontSize = 24.sp)
            }

            Column {
              Text(
                text = "Espace Collégien",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
              )
              Text(
                text = "Mode Invité • Session d'apprentissage active",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF94A3B8)
              )
            }
          }

          Spacer(modifier = Modifier.height(16.dp))

          // 4 Key metrics
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            MetricBox("Score", "${progress.points} pts", ElectricBlueLight, Modifier.weight(1f))
            MetricBox("Leçons", "${progress.completedLessonIds.size}", TurquoiseLight, Modifier.weight(1f))
            MetricBox("Exercices", "${progress.correctExerciseCount}", VioletLight, Modifier.weight(1f))
            MetricBox("Favoris", "${progress.bookmarkedResourceIds.size}", AmberFormula, Modifier.weight(1f))
          }

          Spacer(modifier = Modifier.height(16.dp))

          Button(
            onClick = onContinueLearning,
            colors = ButtonDefaults.buttonColors(containerColor = ElectricBlue),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth()
          ) {
            Icon(Icons.Default.PlayArrow, contentDescription = null)
            Spacer(modifier = Modifier.width(6.dp))
            Text("Reprendre mon apprentissage", fontWeight = FontWeight.Bold)
          }
        }
      }
    }

    // Progress by school level
    item {
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SlateBorder))
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Text(
            text = "📊 Progression par niveau collège",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = DarkNavyText
          )

          Spacer(modifier = Modifier.height(14.dp))

          LevelProgressBar(levelName = "3AC — Troisième Année (Brevet)", percent = pct3AC, color = VioletAccent)
          Spacer(modifier = Modifier.height(10.dp))
          LevelProgressBar(levelName = "2AC — Deuxième Année", percent = 15, color = TurquoisePrimary)
          Spacer(modifier = Modifier.height(10.dp))
          LevelProgressBar(levelName = "1AC — Première Année", percent = 20, color = ElectricBlue)
        }
      }
    }

    // Bookmarked resources
    item {
      Text(
        text = "⭐ Ressources sauvegardées (${bookmarkedLessons.size})",
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = DarkNavyText,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
      )
    }

    if (bookmarkedLessons.isEmpty()) {
      item {
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
          colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .padding(24.dp),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = "Aucune ressource dans tes favoris pour l'instant. Clique sur l'icône marque-page dans une leçon pour la sauvegarder !",
              color = SlateMuted,
              fontSize = 13.sp,
              lineHeight = 18.sp
            )
          }
        }
      }
    } else {
      items(bookmarkedLessons) { lesson ->
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable { onOpenLesson(lesson.id) },
          colors = CardDefaults.cardColors(containerColor = Color.White),
          border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SlateBorder))
        ) {
          Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Icon(Icons.Default.Bookmark, contentDescription = null, tint = AmberFormula)
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier.weight(1f)) {
              Text(text = lesson.title, fontWeight = FontWeight.Bold, color = DarkNavyText, fontSize = 13.sp)
              Text(text = "${lesson.level.code} • ${lesson.chapterTitle}", fontSize = 11.sp, color = SlateMuted)
            }
            Text("Ouvrir", color = ElectricBlue, fontWeight = FontWeight.Bold, fontSize = 12.sp)
          }
        }
      }
    }

    // Completed Lessons
    item {
      Spacer(modifier = Modifier.height(16.dp))
      Text(
        text = "✅ Leçons validées (${completedLessons.size})",
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = DarkNavyText,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
      )
    }

    if (completedLessons.isEmpty()) {
      item {
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
          colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .padding(24.dp),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = "Tu n'as pas encore validé de leçons. Termine un cours et clique sur « Marquer comme terminé » pour suivre ton avancement.",
              color = SlateMuted,
              fontSize = 13.sp,
              lineHeight = 18.sp
            )
          }
        }
      }
    } else {
      items(completedLessons) { lesson ->
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable { onOpenLesson(lesson.id) },
          colors = CardDefaults.cardColors(containerColor = Color(0xFFF0FDF4)),
          border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(Color(0xFF86EFAC)))
        ) {
          Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Icon(Icons.Default.CheckCircle, contentDescription = null, tint = MathSuccess)
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier.weight(1f)) {
              Text(text = lesson.title, fontWeight = FontWeight.Bold, color = DarkNavyText, fontSize = 13.sp)
              Text(text = "${lesson.level.code} • Cours maîtrisé", fontSize = 11.sp, color = Color(0xFF15803D))
            }
            Text("Revoir", color = ElectricBlue, fontWeight = FontWeight.Bold, fontSize = 12.sp)
          }
        }
      }
    }
  }
}

@Composable
private fun MetricBox(label: String, value: String, color: Color, modifier: Modifier = Modifier) {
  Surface(
    modifier = modifier,
    color = Color(0x221E293B),
    shape = RoundedCornerShape(10.dp),
    border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(Color(0x33475569)))
  ) {
    Column(
      modifier = Modifier.padding(vertical = 10.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(text = value, fontWeight = FontWeight.Black, color = color, fontSize = 15.sp)
      Text(text = label, color = Color(0xFFE2E8F0), fontSize = 10.sp)
    }
  }
}

@Composable
private fun LevelProgressBar(levelName: String, percent: Int, color: Color) {
  Column {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Text(text = levelName, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = DarkNavyText)
      Text(text = "$percent%", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = color)
    }
    Spacer(modifier = Modifier.height(4.dp))
    LinearProgressIndicator(
      progress = { percent / 100f },
      modifier = Modifier
        .fillMaxWidth()
        .height(8.dp)
        .clip(RoundedCornerShape(4.dp)),
      color = color,
      trackColor = Color(0xFFE2E8F0)
    )
  }
}
