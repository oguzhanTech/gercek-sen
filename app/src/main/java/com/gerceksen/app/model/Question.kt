package com.gerceksen.app.model

import androidx.annotation.DrawableRes

data class Question(
    val text: String,
    val options: List<AnswerOption>,
    /** Tek görsel: tüm şıkların üstünde gösterilir (isteğe bağlı). */
    @DrawableRes val imageResId: Int? = null,
) {
    init {
        require(options.size == 4) { "Each question must have exactly 4 options." }
    }
}
