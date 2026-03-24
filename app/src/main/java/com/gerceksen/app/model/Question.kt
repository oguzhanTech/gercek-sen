package com.gerceksen.app.model

data class Question(
    val text: String,
    val options: List<AnswerOption>,
) {
    init {
        require(options.size == 4) { "Each question must have exactly 4 options." }
    }
}
