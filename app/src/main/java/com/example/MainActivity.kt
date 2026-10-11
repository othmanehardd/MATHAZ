package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.MathRepository
import com.example.model.SchoolLevel
import com.example.ui.components.AZMathTopBar
import com.example.ui.components.SearchDialog
import com.example.ui.screens.*
import com.example.ui.theme.*

enum class AppNavTab(val label: String, val selectedIcon: ImageVector, val unselectedIcon: ImageVector) {
  HOME("Accueil", Icons.Filled.Home, Icons.Outlined.Home),
  LEVELS("Niveaux", Icons.Filled.School, Icons.Outlined.School),
  EXERCISES("Exercices", Icons.Filled.AssignmentTurnedIn, Icons.Outlined.AssignmentTurnedIn),
  EXAMS("Examens", Icons.Filled.FactCheck, Icons.Outlined.FactCheck),
  DASHBOARD("Mon Espace", Icons.Filled.Person, Icons.Outlined.Person)
}

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MyApplicationTheme {
        AZMathApp()
      }
    }
  }
}

@Composable
fun AZMathApp(
  repository: MathRepository = MathRepository.instance
) {
  var currentTab by remember { mutableStateOf(AppNavTab.HOME) }
  var selectedLevel by remember { mutableStateOf(SchoolLevel.AC3) }
  var currentLessonId by remember { mutableStateOf<String?>(null) }
  var exerciseChapterFilter by remember { mutableStateOf<String?>(null) }
  var showSearchDialog by remember { mutableStateOf(false) }
  var showAdminScreen by remember { mutableStateOf(false) }

  val chapters by repository.chapters.collectAsStateWithLifecycle()
  val lessons by repository.lessons.collectAsStateWithLifecycle()
  val exercises by repository.exercises.collectAsStateWithLifecycle()
  val regionalExams by repository.regionalExams.collectAsStateWithLifecycle()
  val studentProgress by repository.studentProgress.collectAsStateWithLifecycle()

  // Handle system back navigation
  BackHandler(enabled = showAdminScreen || currentLessonId != null || currentTab != AppNavTab.HOME) {
    when {
      showAdminScreen -> showAdminScreen = false
      currentLessonId != null -> currentLessonId = null
      currentTab != AppNavTab.HOME -> currentTab = AppNavTab.HOME
    }
  }

  Scaffold(
    modifier = Modifier
      .fillMaxSize()
      .testTag("azmath_main_scaffold"),
    topBar = {
      if (!showAdminScreen && currentLessonId == null) {
        AZMathTopBar(
          onSearchClick = { showSearchDialog = true },
          onAdminClick = { showAdminScreen = true },
          onLevelSelected = { lvl ->
            selectedLevel = lvl
            currentTab = AppNavTab.LEVELS
          },
          selectedLevel = if (currentTab == AppNavTab.LEVELS) selectedLevel else null,
          bookmarkCount = studentProgress.bookmarkedResourceIds.size
        )
      }
    },
    bottomBar = {
      if (!showAdminScreen && currentLessonId == null) {
        NavigationBar(
          containerColor = Color.White,
          tonalElevation = 6.dp,
          windowInsets = WindowInsets.navigationBars
        ) {
          AppNavTab.entries.forEach { tab ->
            val isSelected = currentTab == tab
            NavigationBarItem(
              selected = isSelected,
              onClick = {
                currentTab = tab
                if (tab == AppNavTab.EXERCISES) {
                  exerciseChapterFilter = null
                }
              },
              icon = {
                Icon(
                  imageVector = if (isSelected) tab.selectedIcon else tab.unselectedIcon,
                  contentDescription = tab.label
                )
              },
              label = {
                Text(
                  text = tab.label,
                  fontSize = 11.sp
                )
              },
              colors = NavigationBarItemDefaults.colors(
                selectedIconColor = ElectricBlue,
                selectedTextColor = ElectricBlue,
                indicatorColor = ElectricBlueContainer,
                unselectedIconColor = SlateMuted,
                unselectedTextColor = SlateMuted
              ),
              modifier = Modifier.testTag("nav_item_${tab.name.lowercase()}")
            )
          }
        }
      }
    }
  ) { innerPadding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
    ) {
      when {
        showAdminScreen -> {
          AdminScreen(
            repository = repository,
            onClose = { showAdminScreen = false }
          )
        }

        currentLessonId != null -> {
          val activeLesson = lessons.find { it.id == currentLessonId } ?: lessons.first()
          val isCompleted = studentProgress.completedLessonIds.contains(activeLesson.id)
          val isBookmarked = studentProgress.bookmarkedResourceIds.contains(activeLesson.id)

          LessonReaderScreen(
            lesson = activeLesson,
            isCompleted = isCompleted,
            isBookmarked = isBookmarked,
            onToggleCompleted = { repository.toggleLessonCompleted(activeLesson.id) },
            onToggleBookmark = { repository.toggleBookmark(activeLesson.id) },
            onStartExercises = { chId ->
              exerciseChapterFilter = chId
              currentLessonId = null
              currentTab = AppNavTab.EXERCISES
            },
            onAddPdf = { newDoc ->
              repository.addPdfToLesson(activeLesson.id, newDoc)
            },
            onRemovePdf = { pdfId ->
              repository.removePdfFromLesson(activeLesson.id, pdfId)
            },
            onBack = { currentLessonId = null }
          )
        }

        else -> {
          when (currentTab) {
            AppNavTab.HOME -> {
              HomeScreen(
                onNavigateToLevel = { lvl ->
                  selectedLevel = lvl
                  currentTab = AppNavTab.LEVELS
                },
                onNavigateToLesson = { lessonId ->
                  currentLessonId = lessonId
                },
                onNavigateToExercises = {
                  exerciseChapterFilter = null
                  currentTab = AppNavTab.EXERCISES
                },
                onNavigateToExams = {
                  currentTab = AppNavTab.EXAMS
                },
                onOpenSearch = { showSearchDialog = true },
                popularLessons = lessons
              )
            }

            AppNavTab.LEVELS -> {
              LevelScreen(
                level = selectedLevel,
                chapters = chapters,
                lessons = lessons,
                onSelectLesson = { lessonId ->
                  currentLessonId = lessonId
                },
                onStartChapterExercises = { chId ->
                  exerciseChapterFilter = chId
                  currentTab = AppNavTab.EXERCISES
                }
              )
            }

            AppNavTab.EXERCISES -> {
              ExerciseScreen(
                exercises = exercises,
                studentProgress = studentProgress,
                onRecordAttempt = { exId, isCorrect ->
                  repository.recordExerciseAttempt(exId, isCorrect)
                },
                initialChapterFilter = exerciseChapterFilter
              )
            }

            AppNavTab.EXAMS -> {
              ExamsScreen(exams = regionalExams)
            }

            AppNavTab.DASHBOARD -> {
              DashboardScreen(
                progress = studentProgress,
                allLessons = lessons,
                onContinueLearning = {
                  val nextLesson = lessons.find { !studentProgress.completedLessonIds.contains(it.id) } ?: lessons.first()
                  currentLessonId = nextLesson.id
                },
                onOpenLesson = { lessonId ->
                  currentLessonId = lessonId
                },
                onOpenExercises = {
                  currentTab = AppNavTab.EXERCISES
                }
              )
            }
          }
        }
      }

      // Search Dialog Overlay
      if (showSearchDialog) {
        SearchDialog(
          onDismiss = { showSearchDialog = false },
          onSelectResult = { result ->
            showSearchDialog = false
            when {
              result.id.startsWith("lesson_") -> {
                currentLessonId = result.id
              }
              result.id.startsWith("ex_") -> {
                currentTab = AppNavTab.EXERCISES
              }
              result.id.startsWith("exam_") -> {
                currentTab = AppNavTab.EXAMS
              }
              else -> {
                selectedLevel = result.level
                currentTab = AppNavTab.LEVELS
              }
            }
          },
          repository = repository
        )
      }
    }
  }
}
