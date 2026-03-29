package com.gerceksen.app.data.catalog

import android.util.Log
import com.gerceksen.app.model.AnswerOption
import com.gerceksen.app.model.Category
import com.gerceksen.app.model.CategoryIcon
import com.gerceksen.app.model.Question
import com.gerceksen.app.model.Quiz
import com.gerceksen.app.model.QuizResultDefinition
import com.gerceksen.app.model.QuizTag
import com.gerceksen.app.model.ResultAccent
import com.gerceksen.app.model.ResultReflectionContent

private const val TAG = "QuizCatalogMapper"

data class QuizCatalogDomain(
    val categories: List<Category>,
    val quizzes: List<Quiz>,
)

fun mapCatalogDtoToDomain(dto: QuizCatalogDto, drawable: DrawableResolver): QuizCatalogDomain {
    val categories = dto.categories.mapNotNull { mapCategory(it) }
    val catIds = categories.map { it.id }.toSet()
    val quizzes = dto.quizzes.mapNotNull { mapQuiz(it, catIds, drawable) }
    return QuizCatalogDomain(categories = categories, quizzes = quizzes)
}

private fun mapCategory(dto: CategoryDto): Category? {
    val icon = runCatching { CategoryIcon.valueOf(dto.iconGlyph) }.getOrElse {
        Log.w(TAG, "Unknown category icon: ${dto.iconGlyph}")
        return null
    }
    return Category(
        id = dto.id,
        name = dto.name,
        shortDescription = dto.shortDescription,
        iconGlyph = icon,
    )
}

private fun mapQuiz(dto: QuizJsonDto, validCategoryIds: Set<String>, drawable: DrawableResolver): Quiz? {
    if (dto.categoryId !in validCategoryIds) {
        Log.w(TAG, "Quiz ${dto.id}: unknown categoryId ${dto.categoryId}")
        return null
    }
    if (dto.resultTieBreakOrder.isEmpty()) {
        Log.w(TAG, "Quiz ${dto.id}: empty resultTieBreakOrder")
        return null
    }
    val questions = dto.questions.mapIndexed { idx, q ->
        mapQuestion(dto.id, idx, q, drawable)
    }
    if (questions.any { it == null }) return null
    val results = dto.results.mapNotNull { mapResult(it) }
    if (results.size != dto.results.size) return null
    return Quiz(
        id = dto.id,
        title = dto.title,
        subtitle = dto.subtitle,
        categoryId = dto.categoryId,
        tags = dto.tags.mapNotNull { parseQuizTag(it) }.toSet(),
        questions = questions.filterNotNull(),
        resultDefinitions = results,
        resultTieBreakOrder = dto.resultTieBreakOrder,
        coverImageResId = drawable.drawableId(dto.coverImage),
    )
}

private fun parseQuizTag(raw: String): QuizTag? =
    runCatching { QuizTag.valueOf(raw) }.getOrElse {
        Log.w(TAG, "Unknown quiz tag: $raw")
        null
    }

private fun mapQuestion(quizId: String, index: Int, dto: QuestionJsonDto, drawable: DrawableResolver): Question? {
    if (dto.options.size != 4) {
        Log.w(TAG, "Quiz $quizId question $index: expected 4 options, got ${dto.options.size}")
        return null
    }
    val options = dto.options.map { o ->
        AnswerOption(text = o.text, scores = o.scores)
    }
    return Question(
        text = dto.text,
        options = options,
        imageResId = drawable.drawableId(dto.image),
    )
}

private fun mapResult(dto: ResultJsonDto): QuizResultDefinition? {
    val accent = runCatching { ResultAccent.valueOf(dto.accent) }.getOrElse {
        Log.w(TAG, "Unknown accent: ${dto.accent} for result ${dto.id}")
        return null
    }
    val reflection = dto.reflection?.let { r ->
        ResultReflectionContent(
            whyPreview = r.whyPreview,
            whyBody = r.whyBody,
            balancePreview = r.balancePreview,
            balanceTips = r.balanceTips,
            todayPreview = r.todayPreview,
            todayAction = r.todayAction,
        )
    }
    return QuizResultDefinition(
        id = dto.id,
        title = dto.title,
        subtitle = dto.subtitle,
        description = dto.description,
        shareTeaser = dto.shareTeaser,
        accent = accent,
        reflection = reflection,
    )
}
