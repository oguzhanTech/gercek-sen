package com.gerceksen.app.repository

import com.gerceksen.app.model.Category
import com.gerceksen.app.model.Quiz
import com.gerceksen.app.model.QuizTag

class LocalQuizRepository(
    private val categories: List<Category>,
    private val quizzes: List<Quiz>,
) : QuizRepository {

    override fun getCategories(): List<Category> = categories

    override fun getQuizzesForCategory(categoryId: String): List<Quiz> =
        quizzes.filter { it.categoryId == categoryId }

    override fun getQuizById(quizId: String): Quiz? =
        quizzes.find { it.id == quizId }

    override fun getPopularQuizzes(limit: Int): List<Quiz> =
        quizzes
            .filter { QuizTag.POPULAR in it.tags }
            .take(limit)

    override fun getRecommendations(excludeQuizId: String, limit: Int): List<Quiz> {
        val current = getQuizById(excludeQuizId) ?: return emptyList()
        val sameCategory = quizzes.filter {
            it.categoryId == current.categoryId && it.id != excludeQuizId
        }
        val others = quizzes.filter {
            it.categoryId != current.categoryId && it.id != excludeQuizId
        }
        return (sameCategory + others).distinctBy { it.id }.take(limit)
    }
}
