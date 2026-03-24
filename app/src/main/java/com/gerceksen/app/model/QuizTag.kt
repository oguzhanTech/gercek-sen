package com.gerceksen.app.model

enum class QuizTag(val label: String) {
    POPULAR("Popüler"),
    NEW("Yeni"),
    /** Kısa etiket: dar kartlarda taşmayı önler */
    MOST_SHARED("Çok paylaşılan"),
}
