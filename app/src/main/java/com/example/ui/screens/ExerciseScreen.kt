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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.*
import com.example.ui.theme.*

@Composable
fun ExerciseScreen(
  exercises: List<Exercise>,
  studentProgress: StudentProgress,
  onRecordAttempt: (String, Boolean) -> Unit,
  initialChapterFilter: String? = null,
  modifier: Modifier = Modifier
) {
  var selectedDifficulty by remember { mutableStateOf<ExerciseDifficulty?>(null) }
  var selectedLevel by remember { mutableStateOf<SchoolLevel?>(null) }

  val filteredExercises = exercises
    .filter { selectedDifficulty == null || it.difficulty == selectedDifficulty }
    .filter { selectedLevel == null || it.level == selectedLevel }
    .filter { initialChapterFilter == null || it.chapterId == initialChapterFilter }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(SlateSurface),
    contentPadding = PaddingValues(bottom = 90.dp)
  ) {
    // Header & Score Board
    item {
      ExerciseHeader(
        totalSolved = studentProgress.solvedExerciseIds.size,
        correctCount = studentProgress.correctExerciseCount,
        points = studentProgress.points
      )
    }

    // Filter Chips
    item {
      Column(modifier = Modifier.padding(16.dp)) {
        Text(
          text = "Filtrer par niveau et difficulté :",
          style = MaterialTheme.typography.labelMedium,
          fontWeight = FontWeight.Bold,
          color = SlateMuted
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          item {
            FilterChip(
              selected = selectedLevel == null,
              onClick = { selectedLevel = null },
              label = { Text("Tous niveaux") }
            )
          }
          items(SchoolLevel.entries) { level ->
            FilterChip(
              selected = selectedLevel == level,
              onClick = { selectedLevel = if (selectedLevel == level) null else level },
              label = { Text(level.code) }
            )
          }
        }

        Spacer(modifier = Modifier.height(6.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          item {
            FilterChip(
              selected = selectedDifficulty == null,
              onClick = { selectedDifficulty = null },
              label = { Text("Toutes difficultés") }
            )
          }
          items(ExerciseDifficulty.entries) { diff ->
            FilterChip(
              selected = selectedDifficulty == diff,
              onClick = { selectedDifficulty = if (selectedDifficulty == diff) null else diff },
              label = { Text(diff.label) }
            )
          }
        }
      }
    }

    // Exercises List
    items(filteredExercises) { exercise ->
      InteractiveExerciseCard(
        exercise = exercise,
        onSolved = { isCorrect ->
          onRecordAttempt(exercise.id, isCorrect)
        }
      )
    }

    if (filteredExercises.isEmpty()) {
      item {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .padding(40.dp),
          contentAlignment = Alignment.Center
        ) {
          Text(
            text = "Aucun exercice ne correspond aux filtres sélectionnés.",
            color = SlateMuted,
            fontSize = 14.sp
          )
        }
      }
    }
  }
}

@Composable
private fun ExerciseHeader(
  totalSolved: Int,
  correctCount: Int,
  points: Int
) {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .background(Color(0xFF0F2B48))
      .padding(20.dp)
  ) {
    Column {
      Text(
        text = "Exercices corrigés interactifs",
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Black,
        color = Color.White
      )
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = "Entraîne-toi avec feedback immédiat et corrections détaillées étape par étape.",
        style = MaterialTheme.typography.bodySmall,
        color = Color(0xFFE2E8F0)
      )

      Spacer(modifier = Modifier.height(14.dp))

      // Stats row
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        StatBadge(
          title = "Tentés",
          value = "$totalSolved",
          modifier = Modifier.weight(1f)
        )
        StatBadge(
          title = "Réussis",
          value = "$correctCount",
          modifier = Modifier.weight(1f)
        )
        StatBadge(
          title = "Points",
          value = "$points pts",
          modifier = Modifier.weight(1f)
        )
      }
    }
  }
}

@Composable
private fun StatBadge(title: String, value: String, modifier: Modifier = Modifier) {
  Surface(
    modifier = modifier,
    color = Color(0x33FFFFFF),
    shape = RoundedCornerShape(10.dp)
  ) {
    Column(
      modifier = Modifier.padding(vertical = 8.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(text = value, fontWeight = FontWeight.Black, color = Color.White, fontSize = 16.sp)
      Text(text = title, color = Color(0xFFBAE6FD), fontSize = 11.sp)
    }
  }
}

@Composable
private fun InteractiveExerciseCard(
  exercise: Exercise,
  onSolved: (Boolean) -> Unit
) {
  var selectedOptionIndex by remember(exercise.id) { mutableStateOf<Int?>(null) }
  var numericInput by remember(exercise.id) { mutableStateOf("") }
  var isSubmitted by remember(exercise.id) { mutableStateOf(false) }
  var isCorrect by remember(exercise.id) { mutableStateOf(false) }
  var showHint by remember(exercise.id) { mutableStateOf(false) }
  var showCorrection by remember(exercise.id) { mutableStateOf(false) }

  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 8.dp),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = Color.White),
    border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SlateBorder))
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      // Header: level, chapter, difficulty
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
          Surface(
            color = Color(0xFFDBEAFE),
            shape = RoundedCornerShape(6.dp)
          ) {
            Text(
              text = exercise.level.code,
              color = ElectricBlue,
              fontWeight = FontWeight.Bold,
              fontSize = 11.sp,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }

          Surface(
            color = when (exercise.difficulty) {
              ExerciseDifficulty.FACILE -> Color(0xFFDCFCE7)
              ExerciseDifficulty.MOYEN -> Color(0xFFFEF3C7)
              ExerciseDifficulty.DIFFICILE -> Color(0xFFFEE2E2)
            },
            shape = RoundedCornerShape(6.dp)
          ) {
            Text(
              text = exercise.difficulty.label,
              color = when (exercise.difficulty) {
                ExerciseDifficulty.FACILE -> Color(0xFF15803D)
                ExerciseDifficulty.MOYEN -> Color(0xFFB45309)
                ExerciseDifficulty.DIFFICILE -> Color(0xFFB91C1C)
              },
              fontWeight = FontWeight.Bold,
              fontSize = 11.sp,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }
        }

        Text(
          text = exercise.chapterTitle,
          fontSize = 11.sp,
          color = SlateMuted,
          maxLines = 1
        )
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Title & Statement
      Text(
        text = exercise.title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = DarkNavyText
      )

      Spacer(modifier = Modifier.height(6.dp))

      Text(
        text = exercise.statement,
        style = MaterialTheme.typography.bodyMedium,
        color = SlateBody,
        lineHeight = 22.sp
      )

      if (!exercise.mathExpression.isNullOrBlank()) {
        Spacer(modifier = Modifier.height(8.dp))
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF8FAFC), RoundedCornerShape(8.dp))
            .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(8.dp))
            .padding(10.dp),
          contentAlignment = Alignment.Center
        ) {
          Text(
            text = exercise.mathExpression,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1E3A8A),
            fontSize = 15.sp
          )
        }
      }

      Spacer(modifier = Modifier.height(14.dp))

      // Interactive Question Body depending on type
      when (exercise.type) {
        ExerciseType.QCM, ExerciseType.TRUE_FALSE -> {
          Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            exercise.options.forEachIndexed { index, option ->
              val isSelected = selectedOptionIndex == index
              val isAnswerCorrect = index == exercise.correctAnswerIndex

              Surface(
                modifier = Modifier
                  .fillMaxWidth()
                  .clip(RoundedCornerShape(10.dp))
                  .clickable(enabled = !isSubmitted) {
                    selectedOptionIndex = index
                  },
                color = when {
                  !isSubmitted && isSelected -> Color(0xFFDBEAFE)
                  isSubmitted && isAnswerCorrect -> Color(0xFFDCFCE7)
                  isSubmitted && isSelected && !isAnswerCorrect -> Color(0xFFFEE2E2)
                  else -> Color(0xFFF8FAFC)
                },
                border = CardDefaults.outlinedCardBorder().copy(
                  brush = androidx.compose.ui.graphics.SolidColor(
                    when {
                      !isSubmitted && isSelected -> ElectricBlue
                      isSubmitted && isAnswerCorrect -> Color(0xFF16A34A)
                      isSubmitted && isSelected && !isAnswerCorrect -> Color(0xFFDC2626)
                      else -> SlateBorder
                    }
                  )
                )
              ) {
                Row(
                  modifier = Modifier.padding(12.dp),
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Text(
                    text = "${('A'.code + index).toChar()}.",
                    fontWeight = FontWeight.Bold,
                    color = ElectricBlue,
                    modifier = Modifier.width(24.dp)
                  )
                  Text(
                    text = option,
                    style = MaterialTheme.typography.bodyMedium,
                    color = DarkNavyText,
                    modifier = Modifier.weight(1f)
                  )
                  if (isSubmitted && isAnswerCorrect) {
                    Icon(Icons.Default.CheckCircle, contentDescription = null, tint = MathSuccess)
                  } else if (isSubmitted && isSelected && !isAnswerCorrect) {
                    Icon(Icons.Default.Cancel, contentDescription = null, tint = MathError)
                  }
                }
              }
            }
          }
        }

        ExerciseType.NUMERIC -> {
          Column {
            Text(
              text = "Saisis la valeur numérique exacte :",
              style = MaterialTheme.typography.bodySmall,
              fontWeight = FontWeight.Medium,
              color = SlateMuted
            )
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
              value = numericInput,
              onValueChange = { numericInput = it },
              placeholder = { Text("Ex: 12 ou 2.5") },
              singleLine = true,
              enabled = !isSubmitted,
              modifier = Modifier.fillMaxWidth(),
              shape = RoundedCornerShape(10.dp)
            )
          }
        }

        ExerciseType.ORDER_STEPS -> {
          Text(text = "Ordonne les étapes de la solution", color = SlateMuted)
        }
      }

      Spacer(modifier = Modifier.height(14.dp))

      // Submit Button and Hint
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        if (!isSubmitted) {
          Button(
            onClick = {
              if (exercise.type == ExerciseType.QCM || exercise.type == ExerciseType.TRUE_FALSE) {
                if (selectedOptionIndex != null) {
                  isSubmitted = true
                  isCorrect = (selectedOptionIndex == exercise.correctAnswerIndex)
                  onSolved(isCorrect)
                }
              } else if (exercise.type == ExerciseType.NUMERIC) {
                val entered = numericInput.toDoubleOrNull()
                if (entered != null && exercise.numericCorrectAnswer != null) {
                  isSubmitted = true
                  isCorrect = Math.abs(entered - exercise.numericCorrectAnswer) <= exercise.tolerance
                  onSolved(isCorrect)
                }
              }
            },
            enabled = (exercise.type == ExerciseType.NUMERIC && numericInput.isNotBlank()) ||
                ((exercise.type == ExerciseType.QCM || exercise.type == ExerciseType.TRUE_FALSE) && selectedOptionIndex != null),
            colors = ButtonDefaults.buttonColors(containerColor = ElectricBlue),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.weight(1f)
          ) {
            Text("Valider la réponse", fontWeight = FontWeight.Bold)
          }
        } else {
          Button(
            onClick = {
              isSubmitted = false
              selectedOptionIndex = null
              numericInput = ""
              showCorrection = false
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF1F5F9)),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.weight(1f)
          ) {
            Text("Réessayer", color = ElectricBlue, fontWeight = FontWeight.Bold)
          }
        }

        OutlinedButton(
          onClick = { showHint = !showHint },
          shape = RoundedCornerShape(10.dp)
        ) {
          Icon(Icons.Default.Lightbulb, contentDescription = null, tint = AmberFormula, modifier = Modifier.size(18.dp))
          Spacer(modifier = Modifier.width(4.dp))
          Text(if (showHint) "Masquer l'indice" else "Indice")
        }
      }

      // Hint Display
      AnimatedVisibility(visible = showHint) {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .background(Color(0xFFFEF3C7), RoundedCornerShape(10.dp))
            .padding(12.dp)
        ) {
          Text(
            text = "💡 Indice : ${exercise.hint}",
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFF92400E)
          )
        }
      }

      // Feedback message after submission
      AnimatedVisibility(visible = isSubmitted) {
        Column(modifier = Modifier.padding(top = 12.dp)) {
          Surface(
            color = if (isCorrect) Color(0xFFDCFCE7) else Color(0xFFFEE2E2),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth()
          ) {
            Row(
              modifier = Modifier.padding(12.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Icon(
                imageVector = if (isCorrect) Icons.Default.CheckCircle else Icons.Default.Cancel,
                contentDescription = null,
                tint = if (isCorrect) MathSuccess else MathError
              )
              Spacer(modifier = Modifier.width(8.dp))
              Text(
                text = if (isCorrect) "Bravo ! Réponse correcte (+15 pts)" else "Réponse incorrecte. Consulte la correction.",
                fontWeight = FontWeight.Bold,
                color = if (isCorrect) Color(0xFF14532D) else Color(0xFF991B1B),
                fontSize = 13.sp
              )
            }
          }

          Spacer(modifier = Modifier.height(10.dp))

          // Button to toggle detailed correction
          Button(
            onClick = { showCorrection = !showCorrection },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF8FAFC)),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(ElectricBlue)),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth()
          ) {
            Icon(
              imageVector = if (showCorrection) Icons.Default.VisibilityOff else Icons.Default.Visibility,
              contentDescription = null,
              tint = ElectricBlue,
              modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = if (showCorrection) "Masquer la correction détaillée" else "Voir la correction détaillée",
              color = ElectricBlue,
              fontWeight = FontWeight.Bold
            )
          }

          // Detailed Correction View
          AnimatedVisibility(visible = showCorrection) {
            Box(
              modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .background(Color(0xFFF0FDF4), RoundedCornerShape(12.dp))
                .border(1.dp, Color(0xFF86EFAC), RoundedCornerShape(12.dp))
                .padding(14.dp)
            ) {
              Column {
                Text(
                  text = "Correction détaillée pas à pas :",
                  fontWeight = FontWeight.Bold,
                  color = Color(0xFF15803D),
                  fontSize = 13.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                exercise.stepByStepSolution.forEach { step ->
                  Text(
                    text = step,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF14532D),
                    lineHeight = 20.sp,
                    modifier = Modifier.padding(vertical = 2.dp)
                  )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                  text = "➔ Réponse finale : ${exercise.finalAnswer}",
                  fontWeight = FontWeight.Bold,
                  color = ElectricBlueDark,
                  fontSize = 13.sp
                )
              }
            }
          }
        }
      }
    }
  }
}
