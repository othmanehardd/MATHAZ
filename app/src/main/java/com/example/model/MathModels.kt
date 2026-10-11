package com.example.model

enum class SchoolLevel(val code: String, val shortName: String, val fullName: String, val description: String) {
  AC1(
    code = "1AC",
    shortName = "1AC Collège",
    fullName = "1ère Année Collège",
    description = "Construis des bases solides en mathématiques : opérations, fractions, relatifs, géométrie."
  ),
  AC2(
    code = "2AC",
    shortName = "2AC Collège",
    fullName = "2ème Année Collège",
    description = "Développe ton raisonnement et maîtrise les méthodes de calcul : puissances, Pythagore, Thalès."
  ),
  AC3(
    code = "3AC",
    shortName = "3AC Collège",
    fullName = "3ème Année Collège",
    description = "Révise efficacement et prépare-toi avec succès à l'examen régional normalisé."
  );

  companion object {
    fun fromCode(code: String): SchoolLevel = entries.find { it.code.equals(code, ignoreCase = true) } ?: AC3
  }
}

enum class Semester(val code: String, val label: String) {
  S1("S1", "Semestre 1"),
  S2("S2", "Semestre 2")
}

enum class ResourceType(val label: String, val iconName: String) {
  COURS("Cours & Leçons", "MenuBook"),
  RESUME("Résumés de cours", "AutoStories"),
  EXERCICE("Exercices corrigés", "AssignmentTurnedIn"),
  SERIE("Séries d'exercices", "ListAlt"),
  DEVOIR("Devoirs surveillés", "FactCheck"),
  EXAMEN_REGIONAL("Examens régionaux", "School"),
  FICHES("Fiches de révision", "Description"),
  QUIZ("Quiz interactifs", "Quiz")
}

enum class DiagramType {
  PYTHAGORAS_TRIANGLE,
  THALES_CONFIG,
  CARTESIAN_FUNCTION,
  TRIGONOMETRY_TRIANGLE,
  FRACTIONS_BAR
}

enum class ExerciseDifficulty(val label: String) {
  FACILE("Facile"),
  MOYEN("Moyen"),
  DIFFICILE("Difficile")
}

enum class ExerciseType {
  QCM,
  NUMERIC,
  TRUE_FALSE,
  ORDER_STEPS
}

data class Chapter(
  val id: String,
  val level: SchoolLevel,
  val semester: Semester,
  val title: String,
  val subtitle: String,
  val order: Int,
  val lessonsCount: Int = 1,
  val exercisesCount: Int = 4,
  val hasDevoir: Boolean = true
)

data class MathDefinition(
  val term: String,
  val explanation: String,
  val formula: String? = null
)

data class MathTheorem(
  val title: String,
  val statement: String,
  val formula: String,
  val conditions: String? = null
)

data class MathExample(
  val title: String,
  val problem: String,
  val steps: List<String>,
  val finalAnswer: String
)

data class LessonPdfDocument(
  val id: String,
  val title: String,
  val fileName: String,
  val fileSize: String = "1.2 Mo",
  val pagesCount: Int = 3,
  val category: String = "Fiche de cours",
  val uploadDate: String = "11 Octobre 2026",
  val description: String = "Document pédagogique conforme au programme marocain",
  val samplePreviewContent: List<String> = emptyList()
)

data class Lesson(
  val id: String,
  val chapterId: String,
  val level: SchoolLevel,
  val title: String,
  val chapterTitle: String,
  val summary: String,
  val tableOfContents: List<String>,
  val definitions: List<MathDefinition>,
  val theorems: List<MathTheorem>,
  val workedExamples: List<MathExample>,
  val diagramType: DiagramType? = null,
  val commonMistakes: List<String>,
  val keyPoints: List<String>,
  val estimatedMinutes: Int = 25,
  val pdfDocuments: List<LessonPdfDocument> = emptyList(),
  val pdfAvailable: Boolean = true,
  val pdfName: String = "Fiche_Synthese.pdf"
)

data class Exercise(
  val id: String,
  val chapterId: String,
  val chapterTitle: String,
  val level: SchoolLevel,
  val title: String,
  val difficulty: ExerciseDifficulty,
  val type: ExerciseType,
  val statement: String,
  val mathExpression: String? = null,
  val options: List<String> = emptyList(),
  val correctAnswerIndex: Int = 0,
  val numericCorrectAnswer: Double? = null,
  val tolerance: Double = 0.05,
  val trueFalseCorrect: Boolean = true,
  val hint: String,
  val stepByStepSolution: List<String>,
  val finalAnswer: String
)

data class RegionalExam(
  val id: String,
  val year: Int,
  val region: String,
  val level: SchoolLevel = SchoolLevel.AC3,
  val semester: Semester = Semester.S2,
  val title: String,
  val duration: String = "2 heures",
  val coefficient: Int = 3,
  val exercises: List<ExamExerciseSection>,
  val pdfAvailable: Boolean = true
)

data class ExamExerciseSection(
  val exerciseNumber: String,
  val points: String,
  val theme: String,
  val questions: List<String>,
  val detailedCorrection: List<String>
)

data class StudentProgress(
  val completedLessonIds: Set<String> = emptySet(),
  val bookmarkedResourceIds: Set<String> = emptySet(),
  val solvedExerciseIds: Set<String> = emptySet(),
  val correctExerciseCount: Int = 0,
  val totalAttemptedCount: Int = 0,
  val points: Int = 0
)

data class SearchResult(
  val id: String,
  val title: String,
  val snippet: String,
  val level: SchoolLevel,
  val category: ResourceType,
  val chapterId: String
)
