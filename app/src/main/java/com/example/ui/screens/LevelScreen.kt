package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.*
import com.example.ui.theme.*

@Composable
fun LevelScreen(
  level: SchoolLevel,
  chapters: List<Chapter>,
  lessons: List<Lesson>,
  onSelectLesson: (String) -> Unit,
  onStartChapterExercises: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  var selectedSemester by remember(level) { mutableStateOf(Semester.S1) }
  var searchFilter by remember { mutableStateOf("") }
  var selectedCategoryFilter by remember { mutableStateOf("Tous") }
  val expandedChapterIds = remember { mutableStateMapOf<String, Boolean>() }

  val filteredChapters = chapters
    .filter { it.level == level && it.semester == selectedSemester }
    .filter {
      searchFilter.isBlank() ||
          it.title.contains(searchFilter, ignoreCase = true) ||
          it.subtitle.contains(searchFilter, ignoreCase = true)
    }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(SlateSurface),
    contentPadding = PaddingValues(bottom = 90.dp)
  ) {
    // Top Level Header
    item {
      LevelHeader(level = level)
    }

    // Semester Selector
    item {
      SemesterTabs(
        selectedSemester = selectedSemester,
        onSemesterSelected = { selectedSemester = it }
      )
    }

    // Search and Resource Type Filters
    item {
      Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
        OutlinedTextField(
          value = searchFilter,
          onValueChange = { searchFilter = it },
          placeholder = { Text("Filtrer les chapitres de ${level.code}...") },
          leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = ElectricBlue) },
          trailingIcon = {
            if (searchFilter.isNotEmpty()) {
              IconButton(onClick = { searchFilter = "" }) {
                Icon(Icons.Default.Clear, contentDescription = null)
              }
            }
          },
          singleLine = true,
          modifier = Modifier.fillMaxWidth(),
          shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Resource Type Chips
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          val filters = listOf("Tous", "Cours", "Résumés", "Exercices", "Devoirs")
          items(filters) { filterName ->
            FilterChip(
              selected = selectedCategoryFilter == filterName,
              onClick = { selectedCategoryFilter = filterName },
              label = { Text(filterName, fontSize = 12.sp) },
              colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = ElectricBlue,
                selectedLabelColor = Color.White
              )
            )
          }
        }
      }
    }

    // Chapters count info
    item {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "${filteredChapters.size} chapitres au programme",
          fontWeight = FontWeight.Bold,
          fontSize = 13.sp,
          color = SlateMuted
        )
        Text(
          text = "${selectedSemester.label}",
          fontSize = 12.sp,
          color = ElectricBlue,
          fontWeight = FontWeight.SemiBold
        )
      }
    }

    // Chapters Accordion List
    items(filteredChapters) { chapter ->
      val isExpanded = expandedChapterIds[chapter.id] ?: true
      val chapterLesson = lessons.find { it.chapterId == chapter.id }

      ChapterAccordionCard(
        chapter = chapter,
        hasLesson = chapterLesson != null,
        lessonTitle = chapterLesson?.title,
        isExpanded = isExpanded,
        onToggleExpand = {
          expandedChapterIds[chapter.id] = !isExpanded
        },
        onOpenLesson = {
          if (chapterLesson != null) {
            onSelectLesson(chapterLesson.id)
          } else {
            // Fallback to first available lesson if exact match not seeded
            val fallback = lessons.find { it.level == level } ?: lessons.first()
            onSelectLesson(fallback.id)
          }
        },
        onOpenExercises = {
          onStartChapterExercises(chapter.id)
        }
      )
    }

    if (filteredChapters.isEmpty()) {
      item {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .padding(40.dp),
          contentAlignment = Alignment.Center
        ) {
          Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("📚", fontSize = 36.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
              text = "Aucun chapitre trouvé",
              fontWeight = FontWeight.Bold,
              color = SlateMuted
            )
          }
        }
      }
    }
  }
}

@Composable
private fun LevelHeader(level: SchoolLevel) {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .background(
        when (level) {
          SchoolLevel.AC1 -> Color(0xFF0F2B48)
          SchoolLevel.AC2 -> Color(0xFF0D3338)
          SchoolLevel.AC3 -> Color(0xFF1E1B4B)
        }
      )
      .padding(20.dp)
  ) {
    Column {
      Surface(
        color = Color(0x33FFFFFF),
        shape = RoundedCornerShape(100.dp)
      ) {
        Text(
          text = "Niveau Collège • Maroc",
          color = Color.White,
          fontSize = 11.sp,
          fontWeight = FontWeight.SemiBold,
          modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
      }

      Spacer(modifier = Modifier.height(8.dp))

      Text(
        text = "${level.code} — ${level.fullName}",
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Black,
        color = Color.White
      )

      Spacer(modifier = Modifier.height(6.dp))

      Text(
        text = level.description,
        style = MaterialTheme.typography.bodyMedium,
        color = Color(0xFFE2E8F0),
        lineHeight = 20.sp
      )
    }
  }
}

@Composable
private fun SemesterTabs(
  selectedSemester: Semester,
  onSemesterSelected: (Semester) -> Unit
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .background(Color.White)
      .padding(horizontal = 16.dp, vertical = 8.dp),
    horizontalArrangement = Arrangement.spacedBy(10.dp)
  ) {
    Semester.entries.forEach { sem ->
      val isSelected = selectedSemester == sem
      Button(
        onClick = { onSemesterSelected(sem) },
        colors = ButtonDefaults.buttonColors(
          containerColor = if (isSelected) ElectricBlue else Color(0xFFF1F5F9),
          contentColor = if (isSelected) Color.White else Color(0xFF475569)
        ),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.weight(1f)
      ) {
        Text(
          text = sem.label,
          fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
        )
      }
    }
  }
}

@Composable
private fun ChapterAccordionCard(
  chapter: Chapter,
  hasLesson: Boolean,
  lessonTitle: String?,
  isExpanded: Boolean,
  onToggleExpand: () -> Unit,
  onOpenLesson: () -> Unit,
  onOpenExercises: () -> Unit
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 6.dp),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = Color.White),
    border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SlateBorder))
  ) {
    Column {
      // Header row
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clickable { onToggleExpand() }
          .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Box(
          modifier = Modifier
            .size(36.dp)
            .background(Color(0xFFEFF6FF), RoundedCornerShape(10.dp)),
          contentAlignment = Alignment.Center
        ) {
          Text(
            text = "${chapter.order}",
            fontWeight = FontWeight.Bold,
            color = ElectricBlue,
            fontSize = 14.sp
          )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
          Text(
            text = chapter.title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = DarkNavyText,
            lineHeight = 20.sp
          )
          Text(
            text = chapter.subtitle,
            style = MaterialTheme.typography.bodySmall,
            color = SlateMuted,
            maxLines = 1
          )
        }

        IconButton(onClick = onToggleExpand) {
          Icon(
            imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
            contentDescription = if (isExpanded) "Réduire" else "Dérouler",
            tint = SlateMuted
          )
        }
      }

      // Expandable section with resources
      AnimatedVisibility(visible = isExpanded) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF8FAFC))
            .padding(16.dp),
          verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          // 1. Cours détaillé
          ResourceActionRow(
            icon = Icons.Default.MenuBook,
            iconTint = ElectricBlue,
            title = "Cours & Résumé essentiel",
            badgeText = if (hasLesson) "Complet" else "Fiche",
            actionLabel = "Lire le cours",
            onClick = onOpenLesson
          )

          // 2. Fiches PDF
          ResourceActionRow(
            icon = Icons.Default.PictureAsPdf,
            iconTint = Color(0xFFDC2626),
            title = "Documents & Fiches PDF",
            badgeText = "Téléchargeable",
            actionLabel = "Fiches PDF",
            onClick = onOpenLesson
          )

          // 3. Exercices d'application
          ResourceActionRow(
            icon = Icons.Default.AssignmentTurnedIn,
            iconTint = TurquoisePrimary,
            title = "Exercices corrigés pas à pas",
            badgeText = "Interactif",
            actionLabel = "S'entraîner",
            onClick = onOpenExercises
          )

          // 3. Devoir associé
          ResourceActionRow(
            icon = Icons.Default.FactCheck,
            iconTint = VioletAccent,
            title = "Devoir surveillé & Contrôle continu",
            badgeText = "Évaluation",
            actionLabel = "Voir devoir",
            onClick = onOpenExercises
          )
        }
      }
    }
  }
}

@Composable
private fun ResourceActionRow(
  icon: androidx.compose.ui.graphics.vector.ImageVector,
  iconTint: Color,
  title: String,
  badgeText: String,
  actionLabel: String,
  onClick: () -> Unit
) {
  Surface(
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(12.dp))
      .clickable { onClick() },
    color = Color.White,
    border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SlateBorder))
  ) {
    Row(
      modifier = Modifier.padding(12.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.weight(1f)
      ) {
        Icon(imageVector = icon, contentDescription = null, tint = iconTint, modifier = Modifier.size(20.dp))
        Text(
          text = title,
          style = MaterialTheme.typography.bodyMedium,
          fontWeight = FontWeight.SemiBold,
          color = DarkNavyText
        )
      }

      Surface(
        color = Color(0xFFEFF6FF),
        shape = RoundedCornerShape(6.dp)
      ) {
        Text(
          text = actionLabel,
          color = ElectricBlue,
          fontSize = 11.sp,
          fontWeight = FontWeight.Bold,
          modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
      }
    }
  }
}
