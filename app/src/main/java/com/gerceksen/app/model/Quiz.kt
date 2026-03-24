package com.gerceksen.app.model

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
) {
    val questionCount: Int get() = questions.size
}
