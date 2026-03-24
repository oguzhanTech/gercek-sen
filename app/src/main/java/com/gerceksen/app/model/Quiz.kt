package com.gerceksen.app.model

import androidx.annotation.DrawableRes

data class Quiz(
    val id: String,
    val title: String,
    val subtitle: String,
    val categoryId: String,
    val tags: Set<QuizTag>,
    val questions: List<Question>,
    val resultDefinitions: List<QuizResultDefinition>,
    /**
     * When scores tie, the first id in this list among tied winners is chosen.
     * Should list every result id used in scoring.
     */
    val resultTieBreakOrder: List<String>,
    /** Liste / ana sayfa kartında gösterilecek kapak; yoksa gradient placeholder. */
    @DrawableRes val coverImageResId: Int? = null,
) {
    val questionCount: Int get() = questions.size
}
