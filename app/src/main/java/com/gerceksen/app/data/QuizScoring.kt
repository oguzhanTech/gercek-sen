package com.gerceksen.app.data

import com.gerceksen.app.model.Quiz

fun resolveQuizResult(quiz: Quiz, selectedOptionIndices: List<Int>): String {
    require(selectedOptionIndices.size == quiz.questions.size) {
        "Selection count must match question count."
    }
    val totals = mutableMapOf<String, Int>()
    quiz.questions.forEachIndexed { index, question ->
        val opt = question.options[selectedOptionIndices[index]]
        opt.scores.forEach { (resultId, points) ->
            totals[resultId] = (totals[resultId] ?: 0) + points
        }
    }
    val maxScore = totals.values.maxOrNull() ?: 0
    val winners = totals.filter { it.value == maxScore }.keys
    return quiz.resultTieBreakOrder.first { it in winners }
}
