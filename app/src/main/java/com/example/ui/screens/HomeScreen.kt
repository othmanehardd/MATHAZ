package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.Lesson
import com.example.model.ResourceType
import com.example.model.SchoolLevel
import com.example.ui.theme.*

@Composable
fun HomeScreen(
  onNavigateToLevel: (SchoolLevel) -> Unit,
  onNavigateToLesson: (String) -> Unit,
  onNavigateToExercises: () -> Unit,
  onNavigateToExams: () -> Unit,
  onOpenSearch: () -> Unit,
  popularLessons: List<Lesson>,
  modifier: Modifier = Modifier
) {
  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(SlateSurface),
    contentPadding = PaddingValues(bottom = 80.dp)
  ) {
    // 1. HERO SECTION
    item {
      HeroSection(
        onChooseLevel = { onNavigateToLevel(SchoolLevel.AC3) },
        onExploreCourses = { onNavigateToLevel(SchoolLevel.AC1) },
        onOpenSearch = onOpenSearch
      )
    }

    // 2. CHOOSE YOUR SCHOOL LEVEL (3 INTERACTIVE CARDS)
    item {
      SectionTitle(
        title = "Choisis ton niveau scolaire",
        subtitle = "Programme officiel marocain pour le collège"
      )
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
      ) {
        LevelCard(
          level = SchoolLevel.AC1,
          accentColor = ElectricBlue,
          badgeColor = Color(0xFFDBEAFE),
          tagText = "1AC Collège",
          chaptersCount = "8 chapitres • S1 & S2",
          description = "Construis des bases solides en mathématiques : opérations, fractions, relatifs, géométrie.",
          onClick = { onNavigateToLevel(SchoolLevel.AC1) }
        )
        LevelCard(
          level = SchoolLevel.AC2,
          accentColor = TurquoisePrimary,
          badgeColor = Color(0xFFCCFBF1),
          tagText = "2AC Collège",
          chaptersCount = "8 chapitres • S1 & S2",
          description = "Développe ton raisonnement et maîtrise les méthodes de calcul : puissances, Pythagore, Thalès.",
          onClick = { onNavigateToLevel(SchoolLevel.AC2) }
        )
        LevelCard(
          level = SchoolLevel.AC3,
          accentColor = VioletAccent,
          badgeColor = Color(0xFFEDE9FE),
          tagText = "3AC Collège (Examen Régional)",
          chaptersCount = "15 chapitres • Brevet",
          description = "Révise efficacement et prépare-toi à l'examen régional normalisé avec sujets et corrigés.",
          isHighlight = true,
          onClick = { onNavigateToLevel(SchoolLevel.AC3) }
        )
      }
    }

    // 3. MAIN LEARNING CATEGORIES
    item {
      Spacer(modifier = Modifier.height(24.dp))
      SectionTitle(
        title = "Catégories d'apprentissage",
        subtitle = "Toutes les ressources organisées pour ta réussite"
      )
      LearningCategoriesGrid(
        onSelectCategory = { type ->
          when (type) {
            ResourceType.COURS, ResourceType.RESUME, ResourceType.FICHES -> onNavigateToLevel(SchoolLevel.AC3)
            ResourceType.EXERCICE, ResourceType.SERIE, ResourceType.QUIZ -> onNavigateToExercises()
            ResourceType.DEVOIR, ResourceType.EXAMEN_REGIONAL -> onNavigateToExams()
          }
        }
      )
    }

    // 4. POPULAR & ESSENTIAL LESSONS
    item {
      Spacer(modifier = Modifier.height(24.dp))
      SectionTitle(
        title = "Leçons incontournables",
        subtitle = "Cours complets avec formules et exemples résolus"
      )
      LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp)
      ) {
        items(popularLessons) { lesson ->
          PopularResourceCard(
            lesson = lesson,
            onClick = { onNavigateToLesson(lesson.id) }
          )
        }
      }
    }

    // 5. REGIONAL EXAM BANNER
    item {
      Spacer(modifier = Modifier.height(24.dp))
      RegionalExamBanner(onViewExams = onNavigateToExams)
    }

    // 6. FOOTER
    item {
      Spacer(modifier = Modifier.height(32.dp))
      AZMathFooter()
    }
  }
}

@Composable
private fun HeroSection(
  onChooseLevel: () -> Unit,
  onExploreCourses: () -> Unit,
  onOpenSearch: () -> Unit
) {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .background(
        Brush.verticalGradient(
          colors = listOf(
            DarkNavyText,
            Color(0xFF0F2B48)
          )
        )
      )
      .padding(16.dp)
  ) {
    Column {
      // Header tag
      Surface(
        color = Color(0x332563EB),
        shape = RoundedCornerShape(100.dp),
        border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(Color(0x5560A5FA)))
      ) {
        Row(
          modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Text("🇲🇦", fontSize = 14.sp)
          Text(
            text = "Conforme au programme marocain",
            color = Color(0xFFBAE6FD),
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold
          )
        }
      }

      Spacer(modifier = Modifier.height(14.dp))

      // Main Headline
      Text(
        text = "Réussis tes maths avec A-Z MATH !",
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Black,
        color = Color.White,
        lineHeight = 34.sp
      )

      Spacer(modifier = Modifier.height(8.dp))

      // Subtitle
      Text(
        text = "Des cours clairs, des exercices corrigés et des fiches de révision pour réussir au collège au Maroc.",
        style = MaterialTheme.typography.bodyMedium,
        color = Color(0xFFCBD5E1),
        lineHeight = 22.sp
      )

      Spacer(modifier = Modifier.height(16.dp))

      // Hero Image Illustration
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .height(200.dp),
        shape = RoundedCornerShape(16.dp),
        border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(Color(0x33FFFFFF)))
      ) {
        Image(
          painter = painterResource(id = R.drawable.img_hero_math),
          contentDescription = "Illustration mathématique A-Z MATH",
          contentScale = ContentScale.Crop,
          modifier = Modifier.fillMaxSize()
        )
      }

      Spacer(modifier = Modifier.height(16.dp))

      // 3 Benefits badges
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        BenefitChip(text = "Cours clairs", icon = "📘", modifier = Modifier.weight(1f))
        BenefitChip(text = "Exercices pas à pas", icon = "⚡", modifier = Modifier.weight(1f))
        BenefitChip(text = "Examens régionaux", icon = "🏆", modifier = Modifier.weight(1f))
      }

      Spacer(modifier = Modifier.height(16.dp))

      // Search bar trigger
      Surface(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(14.dp))
          .clickable { onOpenSearch() },
        color = Color(0xFF1E293B),
        border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(Color(0xFF334155)))
      ) {
        Row(
          modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Icon(Icons.Default.Search, contentDescription = null, tint = ElectricBlueLight)
          Spacer(modifier = Modifier.width(10.dp))
          Text(
            text = "Rechercher : Pythagore, Racines, Thalès...",
            color = Color(0xFF94A3B8),
            fontSize = 13.sp
          )
        }
      }

      Spacer(modifier = Modifier.height(16.dp))

      // Action buttons
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        Button(
          onClick = onChooseLevel,
          colors = ButtonDefaults.buttonColors(containerColor = ElectricBlue),
          shape = RoundedCornerShape(12.dp),
          modifier = Modifier.weight(1f)
        ) {
          Text("Choisir mon niveau", fontWeight = FontWeight.Bold, fontSize = 13.sp)
        }
        OutlinedButton(
          onClick = onExploreCourses,
          colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
          border = ButtonDefaults.outlinedButtonBorder.copy(brush = androidx.compose.ui.graphics.SolidColor(Color(0xFF64748B))),
          shape = RoundedCornerShape(12.dp),
          modifier = Modifier.weight(1f)
        ) {
          Text("Explorer les cours", fontSize = 13.sp)
        }
      }
    }
  }
}

@Composable
private fun BenefitChip(text: String, icon: String, modifier: Modifier = Modifier) {
  Surface(
    modifier = modifier,
    color = Color(0x221E293B),
    shape = RoundedCornerShape(10.dp),
    border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(Color(0x33475569)))
  ) {
    Column(
      modifier = Modifier.padding(vertical = 8.dp, horizontal = 6.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(icon, fontSize = 16.sp)
      Spacer(modifier = Modifier.height(2.dp))
      Text(
        text = text,
        color = Color(0xFFE2E8F0),
        fontSize = 11.sp,
        fontWeight = FontWeight.Medium,
        maxLines = 1
      )
    }
  }
}

@Composable
private fun LevelCard(
  level: SchoolLevel,
  accentColor: Color,
  badgeColor: Color,
  tagText: String,
  chaptersCount: String,
  description: String,
  isHighlight: Boolean = false,
  onClick: () -> Unit
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(16.dp))
      .clickable { onClick() },
    colors = CardDefaults.cardColors(containerColor = Color.White),
    elevation = CardDefaults.cardElevation(defaultElevation = if (isHighlight) 4.dp else 1.dp),
    border = if (isHighlight) {
      CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(VioletAccent))
    } else {
      CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SlateBorder))
    }
  ) {
    Column(modifier = Modifier.padding(18.dp)) {
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
              .size(44.dp)
              .background(badgeColor, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = level.code,
              fontWeight = FontWeight.Black,
              fontSize = 16.sp,
              color = accentColor
            )
          }

          Column {
            Text(
              text = level.fullName,
              style = MaterialTheme.typography.titleMedium,
              fontWeight = FontWeight.Bold,
              color = DarkNavyText
            )
            Text(
              text = chaptersCount,
              style = MaterialTheme.typography.bodySmall,
              color = SlateMuted
            )
          }
        }

        if (isHighlight) {
          Surface(
            color = Color(0xFFEDE9FE),
            shape = RoundedCornerShape(8.dp)
          ) {
            Text(
              text = "⭐ Priorité Brevet",
              color = VioletAccent,
              fontWeight = FontWeight.Bold,
              fontSize = 11.sp,
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      Text(
        text = description,
        style = MaterialTheme.typography.bodyMedium,
        color = SlateBody,
        lineHeight = 20.sp
      )

      Spacer(modifier = Modifier.height(14.dp))

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
          LevelFeaturePill("Cours")
          LevelFeaturePill("Exercices")
          LevelFeaturePill("Devoirs")
          if (level == SchoolLevel.AC3) LevelFeaturePill("Régionaux")
        }

        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
          Text(
            text = "Accéder",
            fontWeight = FontWeight.Bold,
            color = accentColor,
            fontSize = 13.sp
          )
          Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = null,
            tint = accentColor,
            modifier = Modifier.size(16.dp)
          )
        }
      }
    }
  }
}

@Composable
private fun LevelFeaturePill(text: String) {
  Box(
    modifier = Modifier
      .background(Color(0xFFF1F5F9), RoundedCornerShape(6.dp))
      .padding(horizontal = 7.dp, vertical = 3.dp)
  ) {
    Text(text, fontSize = 10.sp, color = Color(0xFF475569), fontWeight = FontWeight.Medium)
  }
}

@Composable
private fun LearningCategoriesGrid(onSelectCategory: (ResourceType) -> Unit) {
  val categories = listOf(
    Triple(ResourceType.COURS, "Cours et leçons", "📖"),
    Triple(ResourceType.RESUME, "Résumés de cours", "📑"),
    Triple(ResourceType.EXERCICE, "Exercices corrigés", "✏️"),
    Triple(ResourceType.SERIE, "Séries d'exercices", "📂"),
    Triple(ResourceType.DEVOIR, "Devoirs surveillés", "📝"),
    Triple(ResourceType.EXAMEN_REGIONAL, "Examens régionaux", "🎓"),
    Triple(ResourceType.FICHES, "Fiches de révision", "📌"),
    Triple(ResourceType.QUIZ, "Quiz interactifs", "⚡")
  )

  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp)
  ) {
    categories.chunked(2).forEach { rowItems ->
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        rowItems.forEach { (type, label, icon) ->
          Card(
            modifier = Modifier
              .weight(1f)
              .clip(RoundedCornerShape(14.dp))
              .clickable { onSelectCategory(type) },
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SlateBorder))
          ) {
            Row(
              modifier = Modifier.padding(14.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
              Text(icon, fontSize = 24.sp)
              Column {
                Text(
                  text = label,
                  fontWeight = FontWeight.Bold,
                  fontSize = 12.sp,
                  color = DarkNavyText,
                  lineHeight = 16.sp
                )
                Text(
                  text = "Accéder",
                  fontSize = 10.sp,
                  color = ElectricBlue
                )
              }
            }
          }
        }
      }
    }
  }
}

@Composable
private fun PopularResourceCard(
  lesson: Lesson,
  onClick: () -> Unit
) {
  Card(
    modifier = Modifier
      .width(260.dp)
      .clip(RoundedCornerShape(16.dp))
      .clickable { onClick() },
    colors = CardDefaults.cardColors(containerColor = Color.White),
    border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(SlateBorder))
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Surface(
          color = Color(0xFFDBEAFE),
          shape = RoundedCornerShape(6.dp)
        ) {
          Text(
            text = "${lesson.level.code} • Semestre 1",
            color = ElectricBlue,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
          )
        }
        Text(
          text = "⏱ ${lesson.estimatedMinutes} min",
          fontSize = 11.sp,
          color = SlateMuted
        )
      }

      Spacer(modifier = Modifier.height(10.dp))

      Text(
        text = lesson.title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = DarkNavyText,
        maxLines = 2,
        lineHeight = 20.sp
      )

      Spacer(modifier = Modifier.height(6.dp))

      Text(
        text = lesson.summary,
        style = MaterialTheme.typography.bodySmall,
        color = SlateBody,
        maxLines = 2,
        lineHeight = 16.sp
      )

      Spacer(modifier = Modifier.height(14.dp))

      Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF1F5F9)),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        Text("Ouvrir la leçon", color = ElectricBlue, fontWeight = FontWeight.Bold, fontSize = 12.sp)
      }
    }
  }
}

@Composable
private fun RegionalExamBanner(onViewExams: () -> Unit) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp)
      .clip(RoundedCornerShape(16.dp))
      .clickable { onViewExams() },
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1B4B)),
    border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(Color(0xFF4338CA)))
  ) {
    Column(modifier = Modifier.padding(20.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "🎓 Examens Régionaux 3AC",
          style = MaterialTheme.typography.titleLarge,
          fontWeight = FontWeight.Bold,
          color = Color.White
        )
        Surface(
          color = VioletAccent,
          shape = RoundedCornerShape(100.dp)
        ) {
          Text(
            text = "Corrigés officiels",
            color = Color.White,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(8.dp))

      Text(
        text = "Entraîne-toi sur les épreuves des 12 académies régionales du Maroc (Casablanca, Rabat, Marrakech, Fès, Tanger...) avec barème détaillé sur 20 points.",
        style = MaterialTheme.typography.bodySmall,
        color = Color(0xFFC7D2FE),
        lineHeight = 18.sp
      )

      Spacer(modifier = Modifier.height(14.dp))

      Button(
        onClick = onViewExams,
        colors = ButtonDefaults.buttonColors(containerColor = VioletAccent),
        shape = RoundedCornerShape(10.dp)
      ) {
        Text("Consulter les examens régionaux", fontWeight = FontWeight.Bold, fontSize = 12.sp)
      }
    }
  }
}

@Composable
private fun SectionTitle(title: String, subtitle: String) {
  Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
    Text(
      text = title,
      style = MaterialTheme.typography.titleLarge,
      fontWeight = FontWeight.Bold,
      color = DarkNavyText
    )
    Text(
      text = subtitle,
      style = MaterialTheme.typography.bodySmall,
      color = SlateMuted
    )
  }
}

@Composable
fun AZMathFooter() {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .background(Color(0xFF0F172A))
      .padding(24.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      Box(
        modifier = Modifier
          .size(32.dp)
          .background(ElectricBlue, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
      ) {
        Text("A-Z", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
      }
      Text(
        text = "A-Z MATH",
        color = Color.White,
        fontWeight = FontWeight.Black,
        fontSize = 18.sp
      )
    }

    Spacer(modifier = Modifier.height(8.dp))

    Text(
      text = "Les mathématiques deviennent simples !",
      color = TurquoiseLight,
      fontWeight = FontWeight.SemiBold,
      fontSize = 13.sp
    )

    Text(
      text = "Comprendre • S'entraîner • Progresser • Réussir",
      color = Color(0xFF94A3B8),
      fontSize = 11.sp
    )

    Spacer(modifier = Modifier.height(16.dp))
    HorizontalDivider(color = Color(0xFF334155))
    Spacer(modifier = Modifier.height(16.dp))

    Text(
      text = "Plateforme éducative dédiée aux collégiens du Maroc",
      color = Color(0xFF64748B),
      fontSize = 11.sp
    )
    Text(
      text = "1AC • 2AC • 3AC — Préparation à l'examen régional",
      color = Color(0xFF64748B),
      fontSize = 11.sp
    )

    Spacer(modifier = Modifier.height(12.dp))

    Text(
      text = "© A-Z MATH — Apprendre les mathématiques autrement.",
      color = Color(0xFF94A3B8),
      fontSize = 11.sp,
      fontWeight = FontWeight.Medium
    )
  }
}
