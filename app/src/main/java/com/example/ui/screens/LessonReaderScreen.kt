package com.example.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import com.example.model.Lesson
import com.example.model.LessonPdfDocument
import com.example.ui.components.*
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonReaderScreen(
  lesson: Lesson,
  isCompleted: Boolean,
  isBookmarked: Boolean,
  onToggleCompleted: () -> Unit,
  onToggleBookmark: () -> Unit,
  onStartExercises: (String) -> Unit,
  onAddPdf: (LessonPdfDocument) -> Unit,
  onRemovePdf: (String) -> Unit,
  onBack: () -> Unit,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  var viewingPdfDocument by remember { mutableStateOf<LessonPdfDocument?>(null) }
  var showAddPdfDialog by remember { mutableStateOf(false) }

  Box(modifier = modifier.fillMaxSize()) {
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .background(SlateSurface),
      contentPadding = PaddingValues(bottom = 90.dp)
    ) {
      // Top Bar Navigation
      item {
        Surface(
          color = Color.White,
          shadowElevation = 2.dp,
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 8.dp, vertical = 6.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Retour")
              }
              Column {
                Text(
                  text = "${lesson.level.code} • ${lesson.chapterTitle}",
                  style = MaterialTheme.typography.labelSmall,
                  color = ElectricBlue,
                  fontWeight = FontWeight.Bold
                )
                Text(
                  text = "Cours complet",
                  style = MaterialTheme.typography.titleSmall,
                  fontWeight = FontWeight.Bold,
                  color = DarkNavyText
                )
              }
            }

            Row {
              IconButton(onClick = onToggleBookmark) {
                Icon(
                  imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                  contentDescription = "Favori",
                  tint = if (isBookmarked) AmberFormula else Color(0xFF64748B)
                )
              }
              IconButton(onClick = { showAddPdfDialog = true }) {
                Icon(Icons.Default.PostAdd, contentDescription = "Ajouter PDF", tint = ElectricBlue)
              }
            }
          }
        }
      }

      // Breadcrumb & Title Section
      item {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            Text("Accueil", fontSize = 11.sp, color = SlateMuted)
            Text("›", fontSize = 11.sp, color = SlateMuted)
            Text(lesson.level.code, fontSize = 11.sp, color = SlateMuted)
            Text("›", fontSize = 11.sp, color = SlateMuted)
            Text(lesson.chapterTitle, fontSize = 11.sp, color = ElectricBlue, fontWeight = FontWeight.SemiBold)
          }

          Spacer(modifier = Modifier.height(10.dp))

          Text(
            text = lesson.title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Black,
            color = DarkNavyText,
            lineHeight = 30.sp
          )

          Spacer(modifier = Modifier.height(8.dp))

          Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "⏱ Temps d'étude : ~${lesson.estimatedMinutes} min",
              fontSize = 12.sp,
              color = SlateMuted
            )
            Text(
              text = "📄 ${lesson.pdfDocuments.size} fiches PDF",
              fontSize = 12.sp,
              fontWeight = FontWeight.SemiBold,
              color = ElectricBlue
            )
            if (isCompleted) {
              Surface(
                color = Color(0xFFDCFCE7),
                shape = RoundedCornerShape(6.dp)
              ) {
                Text(
                  text = "✓ Terminé",
                  color = MathSuccess,
                  fontWeight = FontWeight.Bold,
                  fontSize = 11.sp,
                  modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
              }
            }
          }
        }
      }

      // Summary Box
      item {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color(0xFFEFF6FF), RoundedCornerShape(14.dp))
            .border(1.dp, Color(0xFFBFDBFE), RoundedCornerShape(14.dp))
            .padding(16.dp)
        ) {
          Column {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              Icon(Icons.Default.AutoStories, contentDescription = null, tint = ElectricBlue, modifier = Modifier.size(18.dp))
              Text(
                text = "Résumé introductif",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E3A8A),
                fontSize = 13.sp
              )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
              text = lesson.summary,
              style = MaterialTheme.typography.bodyMedium,
              color = Color(0xFF1E293B),
              lineHeight = 22.sp
            )
          }
        }
      }

      // Table of contents
      item {
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
          colors = CardDefaults.cardColors(containerColor = Color.White),
          border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SlateBorder))
        ) {
          Column(modifier = Modifier.padding(16.dp)) {
            Text(
              text = "📑 Sommaire de la leçon",
              style = MaterialTheme.typography.titleSmall,
              fontWeight = FontWeight.Bold,
              color = DarkNavyText
            )
            Spacer(modifier = Modifier.height(8.dp))
            lesson.tableOfContents.forEach { tocItem ->
              Text(
                text = tocItem,
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF334155),
                modifier = Modifier.padding(vertical = 3.dp)
              )
            }
          }
        }
      }

      // SECTION DÉDIÉE : DOCUMENTS ET FICHES PDF ATTACHÉES
      item {
        Spacer(modifier = Modifier.height(24.dp))
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Icon(Icons.Default.PictureAsPdf, contentDescription = null, tint = Color(0xFFDC2626), modifier = Modifier.size(24.dp))
            Text(
              text = "Documents & Fiches PDF (${lesson.pdfDocuments.size})",
              style = MaterialTheme.typography.titleMedium,
              fontWeight = FontWeight.Bold,
              color = DarkNavyText
            )
          }

          Button(
            onClick = { showAddPdfDialog = true },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEFF6FF), contentColor = ElectricBlue),
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp)
          ) {
            Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text("Ajouter PDF", fontSize = 11.sp, fontWeight = FontWeight.Bold)
          }
        }
        Spacer(modifier = Modifier.height(8.dp))
      }

      if (lesson.pdfDocuments.isEmpty()) {
        item {
          Card(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SlateBorder))
          ) {
            Box(
              modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
              contentAlignment = Alignment.Center
            ) {
              Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("📄", fontSize = 28.sp)
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                  text = "Aucun document PDF n'est rattaché pour l'instant.",
                  color = SlateMuted,
                  fontSize = 13.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                  onClick = { showAddPdfDialog = true },
                  colors = ButtonDefaults.buttonColors(containerColor = ElectricBlue),
                  shape = RoundedCornerShape(8.dp)
                ) {
                  Text("+ Ajouter le premier PDF de cette leçon", fontSize = 12.sp)
                }
              }
            }
          }
        }
      } else {
        items(lesson.pdfDocuments) { doc ->
          Card(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 16.dp, vertical = 5.dp),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SlateBorder))
          ) {
            Column(modifier = Modifier.padding(14.dp)) {
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
              ) {
                Row(
                  verticalAlignment = Alignment.Top,
                  horizontalArrangement = Arrangement.spacedBy(10.dp),
                  modifier = Modifier.weight(1f)
                ) {
                  Box(
                    modifier = Modifier
                      .size(38.dp)
                      .background(Color(0xFFFEE2E2), RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                  ) {
                    Text("PDF", color = Color(0xFFDC2626), fontWeight = FontWeight.Black, fontSize = 11.sp)
                  }

                  Column {
                    Text(
                      text = doc.title,
                      style = MaterialTheme.typography.titleSmall,
                      fontWeight = FontWeight.Bold,
                      color = DarkNavyText,
                      lineHeight = 18.sp
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                      text = "${doc.category} • ${doc.fileName} • ${doc.fileSize} (${doc.pagesCount} pages)",
                      style = MaterialTheme.typography.bodySmall,
                      color = SlateMuted,
                      fontSize = 11.sp
                    )
                  }
                }

                IconButton(
                  onClick = { onRemovePdf(doc.id) },
                  modifier = Modifier.size(28.dp)
                ) {
                  Icon(Icons.Default.DeleteOutline, contentDescription = "Supprimer", tint = Color(0xFF94A3B8), modifier = Modifier.size(18.dp))
                }
              }

              if (doc.description.isNotBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                  text = doc.description,
                  style = MaterialTheme.typography.bodySmall,
                  color = Color(0xFF475569),
                  fontSize = 11.sp
                )
              }

              Spacer(modifier = Modifier.height(12.dp))

              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
              ) {
                Button(
                  onClick = { viewingPdfDocument = doc },
                  colors = ButtonDefaults.buttonColors(containerColor = ElectricBlue),
                  shape = RoundedCornerShape(8.dp),
                  modifier = Modifier.weight(1f),
                  contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                  Icon(Icons.Default.Visibility, contentDescription = null, modifier = Modifier.size(16.dp))
                  Spacer(modifier = Modifier.width(6.dp))
                  Text("Lire le PDF", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }

                OutlinedButton(
                  onClick = {
                    Toast.makeText(context, "Fichier « ${doc.fileName} » téléchargé dans vos documents !", Toast.LENGTH_SHORT).show()
                  },
                  colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF0F172A)),
                  border = ButtonDefaults.outlinedButtonBorder.copy(brush = androidx.compose.ui.graphics.SolidColor(SlateBorder)),
                  shape = RoundedCornerShape(8.dp),
                  modifier = Modifier.weight(1f),
                  contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                  Icon(Icons.Default.Download, contentDescription = null, modifier = Modifier.size(16.dp))
                  Spacer(modifier = Modifier.width(6.dp))
                  Text("Télécharger", fontSize = 12.sp)
                }
              }
            }
          }
        }
      }

      // Section 1: Définitions
      if (lesson.definitions.isNotEmpty()) {
        item {
          Spacer(modifier = Modifier.height(20.dp))
          Text(
            text = "I. Définitions et Vocabulaire",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = DarkNavyText,
            modifier = Modifier.padding(horizontal = 16.dp)
          )
          Spacer(modifier = Modifier.height(8.dp))
        }
        items(lesson.definitions) { def ->
          Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)) {
            DefinitionCard(definition = def)
          }
        }
      }

      // Section 2: Théorèmes et Propriétés
      if (lesson.theorems.isNotEmpty()) {
        item {
          Spacer(modifier = Modifier.height(20.dp))
          Text(
            text = "II. Propriétés et Théorèmes fondamentaux",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = DarkNavyText,
            modifier = Modifier.padding(horizontal = 16.dp)
          )
          Spacer(modifier = Modifier.height(8.dp))
        }
        items(lesson.theorems) { thm ->
          Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)) {
            TheoremCard(theorem = thm)
          }
        }
      }

      // Geometric Diagram if present
      if (lesson.diagramType != null) {
        item {
          Spacer(modifier = Modifier.height(20.dp))
          Text(
            text = "III. Figure géométrique & Schéma explicatif",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = DarkNavyText,
            modifier = Modifier.padding(horizontal = 16.dp)
          )
          Spacer(modifier = Modifier.height(8.dp))
          Box(modifier = Modifier.padding(horizontal = 16.dp)) {
            MathDiagramCard(diagramType = lesson.diagramType)
          }
        }
      }

      // Section 4: Exemples résolus
      if (lesson.workedExamples.isNotEmpty()) {
        item {
          Spacer(modifier = Modifier.height(20.dp))
          Text(
            text = "IV. Méthodes & Exemples résolus pas à pas",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = DarkNavyText,
            modifier = Modifier.padding(horizontal = 16.dp)
          )
          Spacer(modifier = Modifier.height(8.dp))
        }
        items(lesson.workedExamples) { ex ->
          Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)) {
            WorkedExampleCard(example = ex)
          }
        }
      }

      // Section 5: Pièges fréquents
      if (lesson.commonMistakes.isNotEmpty()) {
        item {
          Spacer(modifier = Modifier.height(20.dp))
          Box(modifier = Modifier.padding(horizontal = 16.dp)) {
            CommonMistakesCard(mistakes = lesson.commonMistakes)
          }
        }
      }

      // Section 6: Points clés
      if (lesson.keyPoints.isNotEmpty()) {
        item {
          Spacer(modifier = Modifier.height(20.dp))
          Card(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF0FDF4)),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(Color(0xFF86EFAC)))
          ) {
            Column(modifier = Modifier.padding(16.dp)) {
              Text(
                text = "💡 Points essentiels à retenir pour le brevet",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF15803D),
                fontSize = 15.sp
              )
              Spacer(modifier = Modifier.height(10.dp))
              lesson.keyPoints.forEach { pt ->
                Row(
                  modifier = Modifier.padding(vertical = 3.dp),
                  verticalAlignment = Alignment.Top
                ) {
                  Text("✔ ", color = MathSuccess, fontWeight = FontWeight.Bold)
                  Text(
                    text = pt,
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

      // Bottom Action Buttons
      item {
        Spacer(modifier = Modifier.height(28.dp))
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
          verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          Button(
            onClick = { onStartExercises(lesson.chapterId) },
            colors = ButtonDefaults.buttonColors(containerColor = ElectricBlue),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
          ) {
            Icon(Icons.Default.Quiz, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("S'entraîner : Faire les exercices du chapitre", fontWeight = FontWeight.Bold)
          }

          OutlinedButton(
            onClick = onToggleCompleted,
            colors = ButtonDefaults.outlinedButtonColors(
              contentColor = if (isCompleted) MathSuccess else DarkNavyText
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
          ) {
            Icon(
              imageVector = if (isCompleted) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
              contentDescription = null,
              tint = if (isCompleted) MathSuccess else SlateMuted
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(if (isCompleted) "Leçon validée ✓" else "Marquer comme terminée")
          }
        }
      }
    }

    // Modal PDF Viewer Dialog
    viewingPdfDocument?.let { doc ->
      PdfViewerDialog(
        document = doc,
        lessonTitle = lesson.title,
        levelCode = lesson.level.code,
        onDismiss = { viewingPdfDocument = null }
      )
    }

    // Modal Add PDF Dialog
    if (showAddPdfDialog) {
      AddPdfDialog(
        lessonTitle = lesson.title,
        onDismiss = { showAddPdfDialog = false },
        onConfirmAdd = { newDoc ->
          onAddPdf(newDoc)
        }
      )
    }
  }
}
