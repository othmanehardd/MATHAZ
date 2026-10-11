package com.example.ui.screens

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.MathData
import com.example.model.RegionalExam
import com.example.model.SchoolLevel
import com.example.ui.theme.*

@Composable
fun ExamsScreen(
  exams: List<RegionalExam>,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  var selectedRegion by remember { mutableStateOf("Toutes les régions") }
  var selectedYear by remember { mutableStateOf<Int?>(null) }
  var activeExamId by remember { mutableStateOf<String?>(null) }
  var viewModeCorrection by remember { mutableStateOf(false) }

  val filteredExams = exams
    .filter { selectedRegion == "Toutes les régions" || it.region == selectedRegion }
    .filter { selectedYear == null || it.year == selectedYear }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(SlateSurface),
    contentPadding = PaddingValues(bottom = 90.dp)
  ) {
    // Header
    item {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .background(Color(0xFF1E1B4B))
          .padding(20.dp)
      ) {
        Column {
          Surface(
            color = Color(0x33A5B4FC),
            shape = RoundedCornerShape(100.dp)
          ) {
            Text(
              text = "3AC • Examen Régional Normalisé",
              color = Color(0xFFC7D2FE),
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold,
              modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
            )
          }

          Spacer(modifier = Modifier.height(10.dp))

          Text(
            text = "Annales d'examens régionaux & Devoirs",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Black,
            color = Color.White
          )

          Spacer(modifier = Modifier.height(6.dp))

          Text(
            text = "Sujets complets et corrigés officiels des différentes académies régionales d'éducation et de formation du Royaume du Maroc.",
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFFE0E7FF),
            lineHeight = 18.sp
          )
        }
      }
    }

    // Filter controls: Region & Year
    item {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .background(Color.White)
          .padding(16.dp)
      ) {
        Text(
          text = "Filtrer par Académie Régionale :",
          style = MaterialTheme.typography.labelMedium,
          fontWeight = FontWeight.Bold,
          color = SlateMuted
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          items(MathData.moroccanRegions) { region ->
            FilterChip(
              selected = selectedRegion == region,
              onClick = { selectedRegion = region },
              label = { Text(region, fontSize = 12.sp) },
              colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = VioletAccent,
                selectedLabelColor = Color.White
              )
            )
          }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
          text = "Session / Année :",
          style = MaterialTheme.typography.labelMedium,
          fontWeight = FontWeight.Bold,
          color = SlateMuted
        )

        Spacer(modifier = Modifier.height(6.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          FilterChip(
            selected = selectedYear == null,
            onClick = { selectedYear = null },
            label = { Text("Toutes années") }
          )
          listOf(2024, 2023, 2022).forEach { year ->
            FilterChip(
              selected = selectedYear == year,
              onClick = { selectedYear = if (selectedYear == year) null else year },
              label = { Text("$year") }
            )
          }
        }
      }
    }

    // Active Exam Reader Dialog / In-place view
    if (activeExamId != null) {
      val activeExam = exams.find { it.id == activeExamId }
      if (activeExam != null) {
        item {
          Card(
            modifier = Modifier
              .fillMaxWidth()
              .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(ElectricBlue))
          ) {
            Column(modifier = Modifier.padding(16.dp)) {
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Column {
                  Text(
                    text = "${activeExam.region} • ${activeExam.year}",
                    color = VioletAccent,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                  )
                  Text(
                    text = activeExam.title,
                    fontWeight = FontWeight.Black,
                    fontSize = 16.sp,
                    color = DarkNavyText
                  )
                }

                IconButton(onClick = { activeExamId = null }) {
                  Icon(Icons.Default.Close, contentDescription = "Fermer")
                }
              }

              Spacer(modifier = Modifier.height(8.dp))

              Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Text("⏱ Durée : ${activeExam.duration}", fontSize = 12.sp, color = SlateMuted)
                Text("⚖ Coefficient : ${activeExam.coefficient}", fontSize = 12.sp, color = SlateMuted)
                Text("Barème : /20", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = ElectricBlue)
              }

              Spacer(modifier = Modifier.height(14.dp))

              // Switch between Sujet and Corrigé
              Row(
                modifier = Modifier
                  .fillMaxWidth()
                  .background(Color(0xFFF1F5F9), RoundedCornerShape(10.dp))
                  .padding(4.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
              ) {
                Button(
                  onClick = { viewModeCorrection = false },
                  colors = ButtonDefaults.buttonColors(
                    containerColor = if (!viewModeCorrection) ElectricBlue else Color.Transparent,
                    contentColor = if (!viewModeCorrection) Color.White else DarkNavyText
                  ),
                  shape = RoundedCornerShape(8.dp),
                  modifier = Modifier.weight(1f)
                ) {
                  Text("📄 Sujet de l'épreuve", fontWeight = FontWeight.Bold)
                }

                Button(
                  onClick = { viewModeCorrection = true },
                  colors = ButtonDefaults.buttonColors(
                    containerColor = if (viewModeCorrection) MathSuccess else Color.Transparent,
                    contentColor = if (viewModeCorrection) Color.White else DarkNavyText
                  ),
                  shape = RoundedCornerShape(8.dp),
                  modifier = Modifier.weight(1f)
                ) {
                  Text("✅ Corrigé officiel", fontWeight = FontWeight.Bold)
                }
              }

              Spacer(modifier = Modifier.height(16.dp))

              // Exercises of the exam
              activeExam.exercises.forEach { section ->
                Card(
                  modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                  colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAFC)),
                  border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SlateBorder))
                ) {
                  Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                      modifier = Modifier.fillMaxWidth(),
                      horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                      Text(
                        text = "${section.exerciseNumber} : ${section.theme}",
                        fontWeight = FontWeight.Bold,
                        color = DarkNavyText,
                        fontSize = 14.sp
                      )
                      Surface(
                        color = Color(0xFFDBEAFE),
                        shape = RoundedCornerShape(6.dp)
                      ) {
                        Text(
                          text = section.points,
                          color = ElectricBlue,
                          fontWeight = FontWeight.Bold,
                          fontSize = 11.sp,
                          modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                      }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    if (!viewModeCorrection) {
                      // Show subject questions
                      section.questions.forEach { q ->
                        Text(
                          text = q,
                          style = MaterialTheme.typography.bodySmall,
                          color = Color(0xFF1E293B),
                          lineHeight = 20.sp,
                          modifier = Modifier.padding(vertical = 3.dp)
                        )
                      }
                    } else {
                      // Show detailed step by step correction
                      section.detailedCorrection.forEach { c ->
                        Row(
                          modifier = Modifier.padding(vertical = 3.dp),
                          verticalAlignment = Alignment.Top
                        ) {
                          Text("✓ ", color = MathSuccess, fontWeight = FontWeight.Bold)
                          Text(
                            text = c,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF14532D),
                            lineHeight = 20.sp
                          )
                        }
                      }
                    }
                  }
                }
              }

              Spacer(modifier = Modifier.height(10.dp))

              Button(
                onClick = {
                  Toast.makeText(context, "Impression du sujet officiel format examen régional prêt", Toast.LENGTH_SHORT).show()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF1F5F9)),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()
              ) {
                Icon(Icons.Default.Print, contentDescription = null, tint = DarkNavyText)
                Spacer(modifier = Modifier.width(6.dp))
                Text("Mode impression / Examen blanc", color = DarkNavyText)
              }
            }
          }
        }
      }
    }

    // List of available exams
    items(filteredExams) { exam ->
      ExamListCard(
        exam = exam,
        isSelected = activeExamId == exam.id,
        onOpenSubject = {
          activeExamId = exam.id
          viewModeCorrection = false
        },
        onOpenCorrection = {
          activeExamId = exam.id
          viewModeCorrection = true
        }
      )
    }

    if (filteredExams.isEmpty()) {
      item {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .padding(40.dp),
          contentAlignment = Alignment.Center
        ) {
          Text(
            text = "Aucun examen ne correspond aux filtres de région ou d'année.",
            color = SlateMuted,
            fontSize = 14.sp
          )
        }
      }
    }
  }
}

@Composable
private fun ExamListCard(
  exam: RegionalExam,
  isSelected: Boolean,
  onOpenSubject: () -> Unit,
  onOpenCorrection: () -> Unit
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 6.dp),
    shape = RoundedCornerShape(14.dp),
    colors = CardDefaults.cardColors(containerColor = Color.White),
    border = if (isSelected) {
      CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(VioletAccent))
    } else {
      CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SlateBorder))
    }
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Surface(
          color = Color(0xFFEDE9FE),
          shape = RoundedCornerShape(6.dp)
        ) {
          Text(
            text = "${exam.region} • ${exam.year}",
            color = VioletAccent,
            fontWeight = FontWeight.Bold,
            fontSize = 11.sp,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
          )
        }

        Text(
          text = "⏱ ${exam.duration}",
          fontSize = 11.sp,
          color = SlateMuted
        )
      }

      Spacer(modifier = Modifier.height(8.dp))

      Text(
        text = exam.title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = DarkNavyText,
        lineHeight = 20.sp
      )

      Spacer(modifier = Modifier.height(6.dp))

      Text(
        text = "${exam.exercises.size} exercices variés : algèbre, géométrie, repère, statistiques.",
        style = MaterialTheme.typography.bodySmall,
        color = SlateBody
      )

      Spacer(modifier = Modifier.height(12.dp))

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Button(
          onClick = onOpenSubject,
          colors = ButtonDefaults.buttonColors(containerColor = ElectricBlue),
          shape = RoundedCornerShape(8.dp),
          modifier = Modifier.weight(1f)
        ) {
          Text("Ouvrir le sujet", fontWeight = FontWeight.Bold, fontSize = 12.sp)
        }

        OutlinedButton(
          onClick = onOpenCorrection,
          colors = ButtonDefaults.outlinedButtonColors(contentColor = MathSuccess),
          border = ButtonDefaults.outlinedButtonBorder.copy(brush = androidx.compose.ui.graphics.SolidColor(MathSuccess)),
          shape = RoundedCornerShape(8.dp),
          modifier = Modifier.weight(1f)
        ) {
          Text("Voir le corrigé", fontWeight = FontWeight.Bold, fontSize = 12.sp)
        }
      }
    }
  }
}
