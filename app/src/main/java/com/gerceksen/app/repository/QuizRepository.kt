package com.gerceksen.app.repository

import com.gerceksen.app.model.Category
import com.gerceksen.app.model.Quiz
import com.gerceksen.app.model.QuizTag

interface QuizRepository {
    fun getCategories(): List<Category>
    fun getQuizzesForCategory(categoryId: String): List<Quiz>
    fun getQuizById(quizId: String): Quiz?
    fun getPopularQuizzes(limit: Int = 4): List<Quiz>
    fun getRecommendations(excludeQuizId: String, limit: Int = 3): List<Quiz>
}
