package com.gerceksen.app.data.catalog

import kotlinx.serialization.Serializable

@Serializable
data class QuizCatalogDto(
    val schemaVersion: Int = 1,
    val categories: List<CategoryDto> = emptyList(),
    val quizzes: List<QuizJsonDto> = emptyList(),
)

@Serializable
data class CategoryDto(
    val id: String,
    val name: String,
    val shortDescription: String,
    val iconGlyph: String,
)

@Serializable
data class QuizJsonDto(
    val id: String,
    val title: String,
    val subtitle: String,
    val categoryId: String,
    val tags: List<String> = emptyList(),
    val coverImage: String? = null,
    val resultTieBreakOrder: List<String> = emptyList(),
    val questions: List<QuestionJsonDto> = emptyList(),
    val results: List<ResultJsonDto> = emptyList(),
)

@Serializable
data class QuestionJsonDto(
    val text: String,
    val image: String? = null,
    val options: List<OptionJsonDto> = emptyList(),
)

@Serializable
data class OptionJsonDto(
    val text: String,
    val scores: Map<String, Int> = emptyMap(),
)

@Serializable
data class ResultJsonDto(
    val id: String,
    val title: String,
    val subtitle: String,
    val description: String,
    val shareTeaser: String,
    val accent: String,
    val reflection: ReflectionJsonDto? = null,
)

@Serializable
data class ReflectionJsonDto(
    val whyPreview: String,
    val whyBody: String,
    val balancePreview: String,
    val balanceTips: List<String> = emptyList(),
    val todayPreview: String,
    val todayAction: String,
)
