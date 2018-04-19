package arrow.ap.tests

import arrow.fold.AutoFoldProcessor
import arrow.optics.OpticsProcessor

class AutoFoldTest : APTest("arrow.ap.objects.autofold") {

  init {

    testProcessor(AnnotationProcessor(
      name = "Autofold can be generated for sealed classes",
      sourceFile = "AutoFold.java",
      destFile = "AutoFold.kt",
      processor = AutoFoldProcessor()
    ))

    testProcessor(AnnotationProcessor(
      name = "Autofold can be generated for sealed classes with generics",
      sourceFile = "AutoFoldWithGenerics.java",
      destFile = "AutoFoldWithGenerics.kt",
      processor = AutoFoldProcessor()
    ))

    testProcessor(AnnotationProcessor(
      name = "Autofold cannot be generated for sealed class with less generic information than variant",
      sourceFile = "FailGenerics.java",
      errorMessage = """
      |@autofold cannot create a fold method for sealed class arrow.ap.objects.autofold.FailGenerics
      |  sealed class arrow.ap.objects.autofold.FailGenerics
      |  ${" ".repeat("sealed class arrow.ap.objects.autofold.FailGenerics".length)} ^ contains less generic information than variant
      |
      |  arrow.ap.objects.autofold.FailGenerics.Second<A, B>
      |  ${" ".repeat("arrow.ap.objects.autofold.FailGenerics.Second".length)} ^
      """.trimMargin(),
      processor = OpticsProcessor()
    ))

  }

}