package com.gerceksen.app.model

data class QuizResultDefinition(
    val id: String,
    val title: String,
    val subtitle: String,
    val description: String,
    /** Short line optimized for share sheet / screenshot caption. */
    val shareTeaser: String,
    val accent: ResultAccent,
)
