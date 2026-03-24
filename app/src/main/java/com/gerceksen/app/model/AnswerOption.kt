package com.gerceksen.app.model

data class AnswerOption(
    val text: String,
    /** Points per result outcome id (e.g. "a" -> 2). */
    val scores: Map<String, Int>,
)
