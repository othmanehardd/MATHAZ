package com.example.ui.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.model.LessonPdfDocument
import com.example.ui.theme.DarkNavyText
import com.example.ui.theme.ElectricBlue
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun AddPdfDialog(
  lessonTitle: String,
  onDismiss: () -> Unit,
  onConfirmAdd: (LessonPdfDocument) -> Unit
) {
  val context = LocalContext.current
  var title by remember { mutableStateOf("") }
  var fileName by remember { mutableStateOf("") }
  var category by remember { mutableStateOf("Fiche de cours") }
  var pagesCountText by remember { mutableStateOf("3") }
  var fileSizeText by remember { mutableStateOf("1.4 Mo") }
  var description by remember { mutableStateOf("") }
  var previewLinesText by remember { mutableStateOf("") }

  val categories = listOf(
    "Fiche de cours",
    "Résumé synthétique",
    "Série d'exercices",
    "Devoir surveillé",
    "Corrigé officiel"
  )

  Dialog(onDismissRequest = onDismiss) {
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .heightIn(max = 680.dp),
      shape = RoundedCornerShape(20.dp),
      colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
      Column(modifier = Modifier.padding(20.dp)) {
        // Dialog Header
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            Box(
              modifier = Modifier
                .size(38.dp)
                .background(Color(0xFFFEE2E2), RoundedCornerShape(10.dp)),
              contentAlignment = Alignment.Center
            ) {
              Icon(Icons.Default.PictureAsPdf, contentDescription = null, tint = Color(0xFFDC2626))
            }
            Column {
              Text(
                text = "Ajouter un document PDF",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = DarkNavyText
              )
              Text(
                text = "Pour la leçon : $lessonTitle",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF64748B),
                maxLines = 1
              )
            }
          }

          IconButton(onClick = onDismiss) {
            Icon(Icons.Default.Close, contentDescription = "Fermer")
          }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Title input
        OutlinedTextField(
          value = title,
          onValueChange = {
            title = it
            if (fileName.isBlank()) {
              val clean = it.lowercase().replace(" ", "_").replace(Regex("[^a-z0-9_]"), "")
              fileName = if (clean.isNotBlank()) "${clean}.pdf" else ""
            }
          },
          label = { Text("Titre du document PDF") },
          placeholder = { Text("Ex: Résumé des théorèmes et exercices types") },
          singleLine = true,
          modifier = Modifier.fillMaxWidth(),
          shape = RoundedCornerShape(10.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        // File name input
        OutlinedTextField(
          value = fileName,
          onValueChange = { fileName = it },
          label = { Text("Nom du fichier (.pdf)") },
          placeholder = { Text("Ex: Cours_Complet_3AC.pdf") },
          singleLine = true,
          modifier = Modifier.fillMaxWidth(),
          shape = RoundedCornerShape(10.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Category chips
        Text(
          text = "Type de ressource :",
          style = MaterialTheme.typography.labelMedium,
          fontWeight = FontWeight.Bold,
          color = Color(0xFF475569)
        )
        Spacer(modifier = Modifier.height(6.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
          items(categories) { cat ->
            FilterChip(
              selected = category == cat,
              onClick = { category = cat },
              label = { Text(cat, fontSize = 11.sp) }
            )
          }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Pages & Size row
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          OutlinedTextField(
            value = pagesCountText,
            onValueChange = { pagesCountText = it },
            label = { Text("Pages") },
            singleLine = true,
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(10.dp)
          )
          OutlinedTextField(
            value = fileSizeText,
            onValueChange = { fileSizeText = it },
            label = { Text("Taille (ex: 1.2 Mo)") },
            singleLine = true,
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(10.dp)
          )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Description / Preview notes
        OutlinedTextField(
          value = description,
          onValueChange = { description = it },
          label = { Text("Description pédagogique") },
          placeholder = { Text("Contenu du document, objectifs, consignes...") },
          minLines = 2,
          modifier = Modifier.fillMaxWidth(),
          shape = RoundedCornerShape(10.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Submit action
        Button(
          onClick = {
            if (title.isNotBlank()) {
              val formattedDate = SimpleDateFormat("d MMMM yyyy", Locale.FRENCH).format(Date())
              val safeFileName = if (fileName.endsWith(".pdf", ignoreCase = true)) fileName else "${fileName.ifBlank { "Document" }}.pdf"
              val pages = pagesCountText.toIntOrNull() ?: 3
              val newDoc = LessonPdfDocument(
                id = "pdf_doc_${System.currentTimeMillis()}",
                title = title,
                fileName = safeFileName,
                fileSize = fileSizeText.ifBlank { "1.2 Mo" },
                pagesCount = pages,
                category = category,
                uploadDate = formattedDate,
                description = description.ifBlank { "Document officiel rattaché à la leçon $lessonTitle" },
                samplePreviewContent = listOf(
                  "Royaume du Maroc — Ministère de l'Éducation Nationale",
                  "Document officiel rattaché : $title",
                  "Catégorie : $category • Date de publication : $formattedDate",
                  "1. Fiche de travail et cours conforme aux programmes officiels.",
                  "2. Notions abordées : Définitions, propriétés, applications directes.",
                  "3. Conseils méthodologiques pour l'évaluation et l'examen."
                )
              )
              onConfirmAdd(newDoc)
              Toast.makeText(context, "Fiche PDF « $safeFileName » ajoutée avec succès !", Toast.LENGTH_SHORT).show()
              onDismiss()
            } else {
              Toast.makeText(context, "Veuillez saisir un titre pour le document", Toast.LENGTH_SHORT).show()
            }
          },
          colors = ButtonDefaults.buttonColors(containerColor = ElectricBlue),
          shape = RoundedCornerShape(10.dp),
          modifier = Modifier.fillMaxWidth()
        ) {
          Icon(Icons.Default.UploadFile, contentDescription = null)
          Spacer(modifier = Modifier.width(8.dp))
          Text("Joindre et publier le document PDF", fontWeight = FontWeight.Bold)
        }
      }
    }
  }
}
