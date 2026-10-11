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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.MathRepository
import com.example.model.*
import com.example.ui.components.AddPdfDialog
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminScreen(
  repository: MathRepository = MathRepository.instance,
  onClose: () -> Unit,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  var activeTab by remember { mutableStateOf(0) } // 0: Leçon, 1: Exercice, 2: Gérer PDF

  val allLessons by repository.lessons.collectAsStateWithLifecycle()

  // Form states for New Lesson
  var lessonTitle by remember { mutableStateOf("") }
  var lessonChapter by remember { mutableStateOf("Théorème de Pythagore") }
  var lessonLevel by remember { mutableStateOf(SchoolLevel.AC3) }
  var lessonSummary by remember { mutableStateOf("") }
  var lessonTheoremTitle by remember { mutableStateOf("") }
  var lessonTheoremFormula by remember { mutableStateOf("") }
  var lessonTheoremStatement by remember { mutableStateOf("") }
  var lessonPdfTitle by remember { mutableStateOf("") }
  var lessonPdfFileName by remember { mutableStateOf("") }

  // Form states for New Exercise
  var exTitle by remember { mutableStateOf("") }
  var exChapter by remember { mutableStateOf("Théorème de Pythagore") }
  var exLevel by remember { mutableStateOf(SchoolLevel.AC3) }
  var exDifficulty by remember { mutableStateOf(ExerciseDifficulty.MOYEN) }
  var exStatement by remember { mutableStateOf("") }
  var exFormula by remember { mutableStateOf("") }
  var exOptionA by remember { mutableStateOf("") }
  var exOptionB by remember { mutableStateOf("") }
  var exOptionC by remember { mutableStateOf("") }
  var exHint by remember { mutableStateOf("") }
  var exSolution by remember { mutableStateOf("") }

  // State for Managing PDFs tab
  var selectedLessonIdForPdf by remember { mutableStateOf(allLessons.firstOrNull()?.id ?: "") }
  var showAddPdfDialogForLesson by remember { mutableStateOf(false) }

  val targetLesson = allLessons.find { it.id == selectedLessonIdForPdf } ?: allLessons.firstOrNull()

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(SlateSurface),
    contentPadding = PaddingValues(bottom = 90.dp)
  ) {
    // Admin Banner
    item {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .background(Color(0xFF0F172A))
          .padding(20.dp)
      ) {
        Column {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
              Icon(Icons.Default.AdminPanelSettings, contentDescription = null, tint = TurquoiseLight, modifier = Modifier.size(28.dp))
              Text(
                text = "Espace Administration A-Z MATH",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White
              )
            }
            IconButton(onClick = onClose) {
              Icon(Icons.Default.Close, contentDescription = "Fermer", tint = Color.White)
            }
          }

          Spacer(modifier = Modifier.height(6.dp))

          Text(
            text = "Gestion pédagogique du contenu : publiez des cours, associez des documents PDF, créez des exercices corrigés et enrichissez la plateforme.",
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFF94A3B8),
            lineHeight = 18.sp
          )
        }
      }
    }

    // Tabs: Leçons / Exercices / Gérer PDF
    item {
      TabRow(
        selectedTabIndex = activeTab,
        containerColor = Color.White,
        contentColor = ElectricBlue
      ) {
        Tab(
          selected = activeTab == 0,
          onClick = { activeTab = 0 },
          text = { Text("Ajouter Leçon", fontWeight = FontWeight.Bold, fontSize = 12.sp) }
        )
        Tab(
          selected = activeTab == 1,
          onClick = { activeTab = 1 },
          text = { Text("Ajouter Exercice", fontWeight = FontWeight.Bold, fontSize = 12.sp) }
        )
        Tab(
          selected = activeTab == 2,
          onClick = { activeTab = 2 },
          text = { Text("📄 Gérer PDF", fontWeight = FontWeight.Bold, fontSize = 12.sp) }
        )
      }
    }

    // Content: Form depending on Tab
    item {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
      ) {
        when (activeTab) {
          0 -> {
            // TAB 0: ADD LESSON FORM
            Text(
              text = "Créer une nouvelle leçon",
              style = MaterialTheme.typography.titleMedium,
              fontWeight = FontWeight.Bold,
              color = DarkNavyText
            )

            OutlinedTextField(
              value = lessonTitle,
              onValueChange = {
                lessonTitle = it
                if (lessonPdfTitle.isBlank()) lessonPdfTitle = "Fiche de synthèse — $it"
                if (lessonPdfFileName.isBlank()) lessonPdfFileName = "${it.lowercase().replace(" ", "_")}.pdf"
              },
              label = { Text("Titre de la leçon") },
              placeholder = { Text("Ex: Trigonométrie dans le triangle rectangle") },
              modifier = Modifier.fillMaxWidth(),
              shape = RoundedCornerShape(10.dp)
            )

            // Level selection
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              SchoolLevel.entries.forEach { lvl ->
                FilterChip(
                  selected = lessonLevel == lvl,
                  onClick = { lessonLevel = lvl },
                  label = { Text(lvl.code) },
                  modifier = Modifier.weight(1f)
                )
              }
            }

            OutlinedTextField(
              value = lessonSummary,
              onValueChange = { lessonSummary = it },
              label = { Text("Résumé pédagogique / Objectif") },
              placeholder = { Text("Ce que l'élève va apprendre et maîtriser...") },
              minLines = 2,
              modifier = Modifier.fillMaxWidth(),
              shape = RoundedCornerShape(10.dp)
            )

            Card(
              colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAFC)),
              border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SlateBorder))
            ) {
              Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Théorème ou Propriété clé :", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = ElectricBlue)
                OutlinedTextField(
                  value = lessonTheoremTitle,
                  onValueChange = { lessonTheoremTitle = it },
                  label = { Text("Titre du théorème") },
                  placeholder = { Text("Ex: Relations cosinus et sinus") },
                  singleLine = true,
                  modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                  value = lessonTheoremFormula,
                  onValueChange = { lessonTheoremFormula = it },
                  label = { Text("Formule mathématique") },
                  placeholder = { Text("Ex: cos²(α) + sin²(α) = 1") },
                  singleLine = true,
                  modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                  value = lessonTheoremStatement,
                  onValueChange = { lessonTheoremStatement = it },
                  label = { Text("Énoncé détaillé") },
                  minLines = 2,
                  modifier = Modifier.fillMaxWidth()
                )
              }
            }

            // PDF Document attachment on creation
            Card(
              colors = CardDefaults.cardColors(containerColor = Color(0xFFFEF2F2)),
              border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(Color(0xFFFECACA)))
            ) {
              Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                  Icon(Icons.Default.PictureAsPdf, contentDescription = null, tint = Color(0xFFDC2626), modifier = Modifier.size(18.dp))
                  Spacer(modifier = Modifier.width(6.dp))
                  Text("Document PDF attaché (Optionnel) :", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = Color(0xFF991B1B))
                }
                OutlinedTextField(
                  value = lessonPdfTitle,
                  onValueChange = { lessonPdfTitle = it },
                  label = { Text("Titre de la fiche PDF") },
                  placeholder = { Text("Ex: Fiche de synthèse et exercices") },
                  singleLine = true,
                  modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                  value = lessonPdfFileName,
                  onValueChange = { lessonPdfFileName = it },
                  label = { Text("Nom du fichier PDF") },
                  placeholder = { Text("Ex: Synthese_Trigo_3AC.pdf") },
                  singleLine = true,
                  modifier = Modifier.fillMaxWidth()
                )
              }
            }

            Button(
              onClick = {
                if (lessonTitle.isNotBlank()) {
                  val initialPdfs = if (lessonPdfTitle.isNotBlank()) {
                    listOf(
                      LessonPdfDocument(
                        id = "pdf_init_${System.currentTimeMillis()}",
                        title = lessonPdfTitle,
                        fileName = if (lessonPdfFileName.endsWith(".pdf", true)) lessonPdfFileName else "${lessonPdfFileName.ifBlank { "Cours" }}.pdf",
                        fileSize = "1.5 Mo",
                        pagesCount = 3,
                        category = "Fiche de cours",
                        description = "Fiche de cours officielle rattachée à la leçon $lessonTitle"
                      )
                    )
                  } else emptyList()

                  val newLesson = Lesson(
                    id = "custom_lesson_${System.currentTimeMillis()}",
                    chapterId = "3ac_s1_c5",
                    level = lessonLevel,
                    title = lessonTitle,
                    chapterTitle = lessonChapter,
                    summary = lessonSummary.ifBlank { "Cours complet conforme au programme." },
                    tableOfContents = listOf("1. Définition", "2. Théorèmes", "3. Applications", "4. Documents PDF"),
                    definitions = listOf(
                      MathDefinition("Notion clé", "Définition mathématique principale.")
                    ),
                    theorems = if (lessonTheoremFormula.isNotBlank()) listOf(
                      MathTheorem(
                        title = lessonTheoremTitle.ifBlank { "Propriété" },
                        statement = lessonTheoremStatement.ifBlank { "Dans tout triangle..." },
                        formula = lessonTheoremFormula
                      )
                    ) else emptyList(),
                    workedExamples = listOf(
                      MathExample("Exemple d'application", "Calcul pas à pas", listOf("Étape 1", "Étape 2"), "Résultat validé")
                    ),
                    commonMistakes = listOf("Attention aux arrondis", "Ne pas confondre les côtés"),
                    keyPoints = listOf("Retenir la formule", "Vérifier les unités"),
                    pdfDocuments = initialPdfs
                  )
                  repository.addLesson(newLesson)
                  Toast.makeText(context, "Leçon publiée avec succès !", Toast.LENGTH_SHORT).show()
                  onClose()
                } else {
                  Toast.makeText(context, "Veuillez renseigner un titre", Toast.LENGTH_SHORT).show()
                }
              },
              colors = ButtonDefaults.buttonColors(containerColor = ElectricBlue),
              shape = RoundedCornerShape(10.dp),
              modifier = Modifier.fillMaxWidth()
            ) {
              Icon(Icons.Default.Publish, contentDescription = null)
              Spacer(modifier = Modifier.width(6.dp))
              Text("Publier la leçon sur la plateforme", fontWeight = FontWeight.Bold)
            }
          }

          1 -> {
            // TAB 1: ADD EXERCISE FORM
            Text(
              text = "Créer un exercice interactif",
              style = MaterialTheme.typography.titleMedium,
              fontWeight = FontWeight.Bold,
              color = DarkNavyText
            )

            OutlinedTextField(
              value = exTitle,
              onValueChange = { exTitle = it },
              label = { Text("Titre de l'exercice") },
              placeholder = { Text("Ex: Calcul de la tangente d'un angle") },
              modifier = Modifier.fillMaxWidth(),
              shape = RoundedCornerShape(10.dp)
            )

            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              ExerciseDifficulty.entries.forEach { diff ->
                FilterChip(
                  selected = exDifficulty == diff,
                  onClick = { exDifficulty = diff },
                  label = { Text(diff.label) },
                  modifier = Modifier.weight(1f)
                )
              }
            }

            OutlinedTextField(
              value = exStatement,
              onValueChange = { exStatement = it },
              label = { Text("Énoncé de l'exercice") },
              placeholder = { Text("Soit un triangle rectangle ABC...") },
              minLines = 2,
              modifier = Modifier.fillMaxWidth(),
              shape = RoundedCornerShape(10.dp)
            )

            OutlinedTextField(
              value = exFormula,
              onValueChange = { exFormula = it },
              label = { Text("Expression mathématique associée") },
              placeholder = { Text("tan(α) = Côté opposé / Côté adjacent") },
              singleLine = true,
              modifier = Modifier.fillMaxWidth(),
              shape = RoundedCornerShape(10.dp)
            )

            Text("Options de réponses QCM (Option A = Réponse correcte) :", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = DarkNavyText)

            OutlinedTextField(
              value = exOptionA,
              onValueChange = { exOptionA = it },
              label = { Text("Option A (Bonne réponse)") },
              singleLine = true,
              modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
              value = exOptionB,
              onValueChange = { exOptionB = it },
              label = { Text("Option B") },
              singleLine = true,
              modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
              value = exOptionC,
              onValueChange = { exOptionC = it },
              label = { Text("Option C") },
              singleLine = true,
              modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
              value = exHint,
              onValueChange = { exHint = it },
              label = { Text("Indice pour l'élève") },
              singleLine = true,
              modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
              value = exSolution,
              onValueChange = { exSolution = it },
              label = { Text("Explication / Solution détaillée") },
              minLines = 2,
              modifier = Modifier.fillMaxWidth()
            )

            Button(
              onClick = {
                if (exTitle.isNotBlank() && exStatement.isNotBlank() && exOptionA.isNotBlank()) {
                  val newEx = Exercise(
                    id = "custom_ex_${System.currentTimeMillis()}",
                    chapterId = "3ac_s1_c3",
                    chapterTitle = exChapter,
                    level = exLevel,
                    title = exTitle,
                    difficulty = exDifficulty,
                    type = ExerciseType.QCM,
                    statement = exStatement,
                    mathExpression = exFormula.ifBlank { null },
                    options = listOf(exOptionA, exOptionB.ifBlank { "Autre" }, exOptionC.ifBlank { "Impossible" }),
                    correctAnswerIndex = 0,
                    hint = exHint.ifBlank { "Relis la définition du cours." },
                    stepByStepSolution = listOf(
                      "1. Identifier les données.",
                      "2. Appliquer la formule.",
                      "3. ${exSolution.ifBlank { "Résultat vérifié." }}"
                    ),
                    finalAnswer = exOptionA
                  )
                  repository.addExercise(newEx)
                  Toast.makeText(context, "Exercice ajouté avec succès !", Toast.LENGTH_SHORT).show()
                  onClose()
                } else {
                  Toast.makeText(context, "Remplissez le titre, l'énoncé et la bonne réponse", Toast.LENGTH_SHORT).show()
                }
              },
              colors = ButtonDefaults.buttonColors(containerColor = TurquoisePrimary),
              shape = RoundedCornerShape(10.dp),
              modifier = Modifier.fillMaxWidth()
            ) {
              Icon(Icons.Default.CheckCircle, contentDescription = null)
              Spacer(modifier = Modifier.width(6.dp))
              Text("Publier l'exercice interactif", fontWeight = FontWeight.Bold)
            }
          }

          2 -> {
            // TAB 2: GESTION DES PDF PAR LEÇON
            Text(
              text = "Gestion des documents PDF par leçon",
              style = MaterialTheme.typography.titleMedium,
              fontWeight = FontWeight.Bold,
              color = DarkNavyText
            )
            Text(
              text = "Sélectionnez une leçon pour voir, ajouter ou retirer des documents PDF :",
              style = MaterialTheme.typography.bodySmall,
              color = SlateMuted
            )

            // Selector for Lesson
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
              allLessons.forEach { lesson ->
                val isSelected = (lesson.id == selectedLessonIdForPdf)
                Surface(
                  modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { selectedLessonIdForPdf = lesson.id },
                  color = if (isSelected) Color(0xFFEFF6FF) else Color.White,
                  border = CardDefaults.outlinedCardBorder().copy(
                    brush = androidx.compose.ui.graphics.SolidColor(if (isSelected) ElectricBlue else SlateBorder)
                  )
                ) {
                  Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                  ) {
                    Column(modifier = Modifier.weight(1f)) {
                      Text(
                        text = "${lesson.level.code} • ${lesson.title}",
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = DarkNavyText,
                        fontSize = 13.sp
                      )
                      Text(
                        text = "${lesson.pdfDocuments.size} document(s) PDF rattaché(s)",
                        fontSize = 11.sp,
                        color = if (lesson.pdfDocuments.isNotEmpty()) ElectricBlue else SlateMuted
                      )
                    }

                    if (isSelected) {
                      Icon(Icons.Default.CheckCircle, contentDescription = null, tint = ElectricBlue, modifier = Modifier.size(20.dp))
                    }
                  }
                }
              }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Details and actions for selected lesson
            if (targetLesson != null) {
              Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SlateBorder))
              ) {
                Column(modifier = Modifier.padding(16.dp)) {
                  Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                  ) {
                    Column {
                      Text(
                        text = targetLesson.title,
                        fontWeight = FontWeight.Bold,
                        color = DarkNavyText,
                        fontSize = 14.sp
                      )
                      Text(
                        text = "${targetLesson.pdfDocuments.size} PDF actuellement disponibles",
                        fontSize = 12.sp,
                        color = TurquoisePrimary
                      )
                    }

                    Button(
                      onClick = { showAddPdfDialogForLesson = true },
                      colors = ButtonDefaults.buttonColors(containerColor = ElectricBlue),
                      shape = RoundedCornerShape(8.dp),
                      contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                      Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                      Spacer(modifier = Modifier.width(4.dp))
                      Text("Ajouter un PDF", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }
                  }

                  Spacer(modifier = Modifier.height(12.dp))

                  if (targetLesson.pdfDocuments.isEmpty()) {
                    Box(
                      modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                      contentAlignment = Alignment.Center
                    ) {
                      Text(
                        text = "Aucun PDF pour cette leçon. Cliquez sur « Ajouter un PDF » ci-dessus.",
                        color = SlateMuted,
                        fontSize = 12.sp
                      )
                    }
                  } else {
                    targetLesson.pdfDocuments.forEach { pdfDoc ->
                      Card(
                        modifier = Modifier
                          .fillMaxWidth()
                          .padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAFC)),
                        border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SlateBorder))
                      ) {
                        Row(
                          modifier = Modifier.padding(10.dp),
                          verticalAlignment = Alignment.CenterVertically,
                          horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                          Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.weight(1f)
                          ) {
                            Text("📄", fontSize = 18.sp)
                            Column {
                              Text(text = pdfDoc.title, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = DarkNavyText)
                              Text(text = "${pdfDoc.fileName} (${pdfDoc.fileSize})", fontSize = 10.sp, color = SlateMuted)
                            }
                          }

                          IconButton(
                            onClick = {
                              repository.removePdfFromLesson(targetLesson.id, pdfDoc.id)
                              Toast.makeText(context, "Document PDF retiré", Toast.LENGTH_SHORT).show()
                            }
                          ) {
                            Icon(Icons.Default.DeleteOutline, contentDescription = "Supprimer", tint = Color(0xFFDC2626), modifier = Modifier.size(18.dp))
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }

  // Add PDF Dialog in Admin
  if (showAddPdfDialogForLesson && targetLesson != null) {
    AddPdfDialog(
      lessonTitle = targetLesson.title,
      onDismiss = { showAddPdfDialogForLesson = false },
      onConfirmAdd = { newDoc ->
        repository.addPdfToLesson(targetLesson.id, newDoc)
      }
    )
  }
}
