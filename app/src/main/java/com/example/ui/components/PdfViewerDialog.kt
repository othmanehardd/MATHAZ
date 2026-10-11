package com.example.ui.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.model.LessonPdfDocument
import com.example.ui.theme.DarkNavyText
import com.example.ui.theme.ElectricBlue
import com.example.ui.theme.SlateBorder
import com.example.ui.theme.TurquoisePrimary

@Composable
fun PdfViewerDialog(
  document: LessonPdfDocument,
  lessonTitle: String,
  levelCode: String,
  onDismiss: () -> Unit
) {
  val context = LocalContext.current
  var currentPage by remember { mutableStateOf(1) }
  val totalPages = document.pagesCount.coerceAtLeast(1)

  Dialog(
    onDismissRequest = onDismiss,
    properties = DialogProperties(usePlatformDefaultWidth = false)
  ) {
    Card(
      modifier = Modifier
        .fillMaxWidth(0.96f)
        .fillMaxHeight(0.92f),
      shape = RoundedCornerShape(20.dp),
      colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F5F9))
    ) {
      Column(modifier = Modifier.fillMaxSize()) {
        // PDF Viewer Top Bar
        Surface(
          color = Color(0xFF0F172A),
          shadowElevation = 4.dp
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
              Box(
                modifier = Modifier
                  .size(36.dp)
                  .background(Color(0xFFDC2626), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
              ) {
                Text(
                  text = "PDF",
                  color = Color.White,
                  fontWeight = FontWeight.Black,
                  fontSize = 11.sp
                )
              }

              Column {
                Text(
                  text = document.fileName,
                  style = MaterialTheme.typography.titleSmall,
                  fontWeight = FontWeight.Bold,
                  color = Color.White,
                  maxLines = 1
                )
                Text(
                  text = "${document.category} • ${document.fileSize} • Page $currentPage/$totalPages",
                  style = MaterialTheme.typography.bodySmall,
                  color = Color(0xFF94A3B8),
                  fontSize = 11.sp
                )
              }
            }

            Row {
              IconButton(onClick = {
                Toast.makeText(context, "Téléchargement de « ${document.fileName} » terminé (enregistré)", Toast.LENGTH_SHORT).show()
              }) {
                Icon(Icons.Default.Download, contentDescription = "Télécharger", tint = Color.White)
              }
              IconButton(onClick = {
                Toast.makeText(context, "Préparation de l'impression du document PDF...", Toast.LENGTH_SHORT).show()
              }) {
                Icon(Icons.Default.Print, contentDescription = "Imprimer", tint = Color.White)
              }
              IconButton(onClick = onDismiss) {
                Icon(Icons.Default.Close, contentDescription = "Fermer", tint = Color.White)
              }
            }
          }
        }

        // PDF Page Document Canvas
        LazyColumn(
          modifier = Modifier
            .weight(1f)
            .fillMaxWidth()
            .padding(14.dp),
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
          item {
            // Document Paper Sheet Layout (like real printable A4 PDF)
            Card(
              modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
              shape = RoundedCornerShape(8.dp),
              colors = CardDefaults.cardColors(containerColor = Color.White),
              elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
              Column(modifier = Modifier.padding(20.dp)) {
                // Official Document Header
                Row(
                  modifier = Modifier.fillMaxWidth(),
                  horizontalArrangement = Arrangement.SpaceBetween,
                  verticalAlignment = Alignment.Top
                ) {
                  Column {
                    Text(
                      text = "ROYAUME DU MAROC",
                      fontWeight = FontWeight.Black,
                      fontSize = 11.sp,
                      color = DarkNavyText
                    )
                    Text(
                      text = "Ministère de l'Éducation Nationale",
                      fontSize = 10.sp,
                      color = Color(0xFF64748B)
                    )
                    Text(
                      text = "Enseignement Collégial — $levelCode",
                      fontWeight = FontWeight.Bold,
                      fontSize = 11.sp,
                      color = ElectricBlue
                    )
                  }

                  Box(
                    modifier = Modifier
                      .background(Color(0xFFEFF6FF), RoundedCornerShape(6.dp))
                      .padding(horizontal = 10.dp, vertical = 6.dp)
                  ) {
                    Text(
                      text = "A-Z MATH",
                      fontWeight = FontWeight.Black,
                      fontSize = 12.sp,
                      color = ElectricBlue
                    )
                  }
                }

                HorizontalDivider(
                  modifier = Modifier.padding(vertical = 12.dp),
                  color = SlateBorder,
                  thickness = 1.dp
                )

                // Title of Document
                Text(
                  text = document.title,
                  style = MaterialTheme.typography.titleMedium,
                  fontWeight = FontWeight.Black,
                  color = DarkNavyText
                )

                Text(
                  text = "Module : $lessonTitle",
                  style = MaterialTheme.typography.bodySmall,
                  color = TurquoisePrimary,
                  fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Content lines
                val previewLines = if (document.samplePreviewContent.isNotEmpty()) {
                  document.samplePreviewContent
                } else {
                  listOf(
                    "Document pédagogique officiel conforme aux orientations pédagogiques marocaines.",
                    "1. Synthèse des définitions et théorèmes fondamentaux.",
                    "2. Méthodes de résolution types et astuces pour réussir les épreuves.",
                    "3. Exercices d'entraînement progressifs et devoirs surveillés.",
                    "Ce document est certifié par A-Z MATH pour la révision et la préparation aux examens."
                  )
                }

                previewLines.forEachIndexed { idx, line ->
                  Box(
                    modifier = Modifier
                      .fillMaxWidth()
                      .padding(vertical = 4.dp)
                      .background(
                        if (line.contains("=") || line.contains("²") || line.contains("√")) Color(0xFFF8FAFC) else Color.Transparent,
                        RoundedCornerShape(6.dp)
                      )
                      .padding(vertical = 4.dp, horizontal = if (line.contains("=")) 8.dp else 0.dp)
                  ) {
                    Text(
                      text = line,
                      style = MaterialTheme.typography.bodySmall,
                      fontFamily = if (line.contains("=") || line.contains("²")) FontFamily.Monospace else FontFamily.Default,
                      fontWeight = if (idx == 0 || line.startsWith("I.") || line.startsWith("II.")) FontWeight.Bold else FontWeight.Normal,
                      color = if (idx == 0) ElectricBlue else DarkNavyText,
                      lineHeight = 20.sp
                    )
                  }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Footer of PDF page
                Row(
                  modifier = Modifier.fillMaxWidth(),
                  horizontalArrangement = Arrangement.SpaceBetween
                ) {
                  Text(
                    text = "A-Z MATH — Fiche officielle • ${document.uploadDate}",
                    fontSize = 9.sp,
                    color = Color(0xFF94A3B8)
                  )
                  Text(
                    text = "Page $currentPage / $totalPages",
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkNavyText
                  )
                }
              }
            }
          }
        }

        // Bottom Page Selector Controls
        Surface(
          color = Color.White,
          shadowElevation = 8.dp
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Button(
              onClick = { if (currentPage > 1) currentPage-- },
              enabled = currentPage > 1,
              colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF1F5F9), contentColor = DarkNavyText),
              shape = RoundedCornerShape(8.dp)
            ) {
              Icon(Icons.Default.NavigateBefore, contentDescription = "Précédent")
              Text("Page précédente", fontSize = 12.sp)
            }

            Text(
              text = "$currentPage / $totalPages",
              fontWeight = FontWeight.Bold,
              fontSize = 13.sp,
              color = DarkNavyText
            )

            Button(
              onClick = { if (currentPage < totalPages) currentPage++ },
              enabled = currentPage < totalPages,
              colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF1F5F9), contentColor = DarkNavyText),
              shape = RoundedCornerShape(8.dp)
            ) {
              Text("Page suivante", fontSize = 12.sp)
              Icon(Icons.Default.NavigateNext, contentDescription = "Suivant")
            }
          }
        }
      }
    }
  }
}
