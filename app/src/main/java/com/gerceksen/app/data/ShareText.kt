package com.gerceksen.app.data

import com.gerceksen.app.model.QuizResultDefinition

fun buildShareText(
    quizTitle: String,
    result: QuizResultDefinition,
    appName: String = "Gerçek Sen",
): String = buildString {
    appendLine("✦ $appName")
    appendLine()
    appendLine(quizTitle)
    appendLine("→ ${result.title}")
    appendLine()
    appendLine(result.shareTeaser)
    appendLine()
    append("Sen de kendi sonucunu keşfet.")
}
