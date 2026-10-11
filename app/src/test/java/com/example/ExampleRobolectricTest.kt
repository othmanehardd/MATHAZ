package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.MathData
import com.example.data.MathRepository
import com.example.model.SchoolLevel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("A-Z MATH", appName)
  }

  @Test
  fun `curriculum chapters and exams are properly loaded`() {
    assertTrue(MathData.chapters.isNotEmpty())
    assertTrue(MathData.lessons.isNotEmpty())
    assertTrue(MathData.regionalExams.isNotEmpty())
    assertTrue(MathData.exercises.isNotEmpty())
  }

  @Test
  fun `search returns relevant math results`() {
    val repo = MathRepository.instance
    val results = repo.search("Pythagore", SchoolLevel.AC3)
    assertTrue("Search for Pythagore should find lessons or chapters", results.isNotEmpty())
  }

  @Test
  fun `adding and removing PDF document to lesson works correctly`() {
    val repo = MathRepository.instance
    val lesson = repo.lessons.value.first()
    val initialPdfCount = lesson.pdfDocuments.size

    val newDoc = com.example.model.LessonPdfDocument(
      id = "test_pdf_123",
      title = "Fiche test PDF",
      fileName = "Test_Document.pdf"
    )
    repo.addPdfToLesson(lesson.id, newDoc)
    val updatedLesson = repo.lessons.value.first { it.id == lesson.id }
    assertEquals(initialPdfCount + 1, updatedLesson.pdfDocuments.size)
    assertTrue(updatedLesson.pdfDocuments.any { it.id == "test_pdf_123" })

    repo.removePdfFromLesson(lesson.id, "test_pdf_123")
    val removedLesson = repo.lessons.value.first { it.id == lesson.id }
    assertEquals(initialPdfCount, removedLesson.pdfDocuments.size)
  }
}
