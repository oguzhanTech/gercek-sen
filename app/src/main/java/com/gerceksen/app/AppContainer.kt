package com.gerceksen.app

import com.gerceksen.app.repository.LocalQuizRepository
import com.gerceksen.app.repository.QuizRepository

object AppContainer {
    val quizRepository: QuizRepository by lazy { LocalQuizRepository() }
}
