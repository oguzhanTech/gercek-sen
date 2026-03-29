package com.gerceksen.app

import android.content.Context
import com.gerceksen.app.data.catalog.loadQuizCatalog
import com.gerceksen.app.repository.LocalQuizRepository
import com.gerceksen.app.repository.QuizRepository

object AppContainer {

    lateinit var quizRepository: QuizRepository
        private set

    fun init(context: Context) {
        val loaded = loadQuizCatalog(context.applicationContext)
        val categories = loaded?.categories.orEmpty()
        val quizzes = loaded?.quizzes.orEmpty()
        quizRepository = LocalQuizRepository(categories = categories, quizzes = quizzes)
    }
}
