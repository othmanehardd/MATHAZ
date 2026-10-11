package com.example.data

import com.example.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MathRepository {

  private val _chapters = MutableStateFlow<List<Chapter>>(MathData.chapters)
  val chapters: StateFlow<List<Chapter>> = _chapters.asStateFlow()

  private val _lessons = MutableStateFlow<List<Lesson>>(MathData.lessons)
  val lessons: StateFlow<List<Lesson>> = _lessons.asStateFlow()

  private val _exercises = MutableStateFlow<List<Exercise>>(MathData.exercises)
  val exercises: StateFlow<List<Exercise>> = _exercises.asStateFlow()

  private val _regionalExams = MutableStateFlow<List<RegionalExam>>(MathData.regionalExams)
  val regionalExams: StateFlow<List<RegionalExam>> = _regionalExams.asStateFlow()

  private val _studentProgress = MutableStateFlow(StudentProgress())
  val studentProgress: StateFlow<StudentProgress> = _studentProgress.asStateFlow()

  fun toggleLessonCompleted(lessonId: String) {
    _studentProgress.update { current ->
      val newSet = if (current.completedLessonIds.contains(lessonId)) {
        current.completedLessonIds - lessonId
      } else {
        current.completedLessonIds + lessonId
      }
      current.copy(
        completedLessonIds = newSet,
        points = current.points + (if (!current.completedLessonIds.contains(lessonId)) 20 else -20).coerceAtLeast(0)
      )
    }
  }

  fun toggleBookmark(resourceId: String) {
    _studentProgress.update { current ->
      val newSet = if (current.bookmarkedResourceIds.contains(resourceId)) {
        current.bookmarkedResourceIds - resourceId
      } else {
        current.bookmarkedResourceIds + resourceId
      }
      current.copy(bookmarkedResourceIds = newSet)
    }
  }

  fun recordExerciseAttempt(exerciseId: String, isCorrect: Boolean) {
    _studentProgress.update { current ->
      val wasSolved = current.solvedExerciseIds.contains(exerciseId)
      val newSolved = current.solvedExerciseIds + exerciseId
      val correctIncrement = if (isCorrect && !wasSolved) 1 else 0
      val pointsEarned = if (isCorrect && !wasSolved) 15 else if (!isCorrect && !wasSolved) 5 else 0

      current.copy(
        solvedExerciseIds = newSolved,
        correctExerciseCount = current.correctExerciseCount + correctIncrement,
        totalAttemptedCount = if (!wasSolved) current.totalAttemptedCount + 1 else current.totalAttemptedCount,
        points = current.points + pointsEarned
      )
    }
  }

  fun search(query: String, filterLevel: SchoolLevel? = null): List<SearchResult> {
    if (query.isBlank()) return emptyList()
    val cleanQuery = query.trim().lowercase()
    val results = mutableListOf<SearchResult>()

    // Search in chapters
    _chapters.value
      .filter { filterLevel == null || it.level == filterLevel }
      .filter {
        it.title.lowercase().contains(cleanQuery) ||
            it.subtitle.lowercase().contains(cleanQuery)
      }
      .forEach { chapter ->
        results.add(
          SearchResult(
            id = chapter.id,
            title = chapter.title,
            snippet = "${chapter.level.code} • ${chapter.semester.label} — ${chapter.subtitle}",
            level = chapter.level,
            category = ResourceType.COURS,
            chapterId = chapter.id
          )
        )
      }

    // Search in lessons
    _lessons.value
      .filter { filterLevel == null || it.level == filterLevel }
      .filter {
        it.title.lowercase().contains(cleanQuery) ||
            it.chapterTitle.lowercase().contains(cleanQuery) ||
            it.summary.lowercase().contains(cleanQuery) ||
            it.definitions.any { d -> d.term.lowercase().contains(cleanQuery) || d.explanation.lowercase().contains(cleanQuery) } ||
            it.theorems.any { t -> t.title.lowercase().contains(cleanQuery) || t.formula.lowercase().contains(cleanQuery) }
      }
      .forEach { lesson ->
        results.add(
          SearchResult(
            id = lesson.id,
            title = lesson.title,
            snippet = "Leçon • ${lesson.level.code} — ${lesson.summary.take(90)}...",
            level = lesson.level,
            category = ResourceType.COURS,
            chapterId = lesson.chapterId
          )
        )
      }

    // Search in exercises
    _exercises.value
      .filter { filterLevel == null || it.level == filterLevel }
      .filter {
        it.title.lowercase().contains(cleanQuery) ||
            it.statement.lowercase().contains(cleanQuery) ||
            (it.mathExpression?.lowercase()?.contains(cleanQuery) == true)
      }
      .forEach { ex ->
        results.add(
          SearchResult(
            id = ex.id,
            title = ex.title,
            snippet = "Exercice (${ex.difficulty.label}) • ${ex.chapterTitle} — ${ex.statement.take(80)}...",
            level = ex.level,
            category = ResourceType.EXERCICE,
            chapterId = ex.chapterId
          )
        )
      }

    // Search in exams
    _regionalExams.value
      .filter {
        it.title.lowercase().contains(cleanQuery) ||
            it.region.lowercase().contains(cleanQuery) ||
            it.year.toString().contains(cleanQuery)
      }
      .forEach { exam ->
        results.add(
          SearchResult(
            id = exam.id,
            title = exam.title,
            snippet = "Examen Régional ${exam.year} • ${exam.region} (${exam.duration})",
            level = exam.level,
            category = ResourceType.EXAMEN_REGIONAL,
            chapterId = ""
          )
        )
      }

    return results.distinctBy { it.id }
  }

  // Admin capabilities
  fun addLesson(lesson: Lesson) {
    _lessons.update { listOf(lesson) + it }
  }

  fun addExercise(exercise: Exercise) {
    _exercises.update { listOf(exercise) + it }
  }

  fun addRegionalExam(exam: RegionalExam) {
    _regionalExams.update { listOf(exam) + it }
  }

  fun addPdfToLesson(lessonId: String, pdfDocument: LessonPdfDocument) {
    _lessons.update { list ->
      list.map { lesson ->
        if (lesson.id == lessonId) {
          lesson.copy(
            pdfDocuments = lesson.pdfDocuments + pdfDocument,
            pdfAvailable = true
          )
        } else {
          lesson
        }
      }
    }
  }

  fun removePdfFromLesson(lessonId: String, pdfDocumentId: String) {
    _lessons.update { list ->
      list.map { lesson ->
        if (lesson.id == lessonId) {
          val updated = lesson.pdfDocuments.filterNot { it.id == pdfDocumentId }
          lesson.copy(
            pdfDocuments = updated,
            pdfAvailable = updated.isNotEmpty()
          )
        } else {
          lesson
        }
      }
    }
  }

  companion object {
    val instance: MathRepository by lazy { MathRepository() }
  }
}
