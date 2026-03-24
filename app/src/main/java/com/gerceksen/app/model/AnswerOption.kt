package com.gerceksen.app.model

import androidx.annotation.DrawableRes

data class AnswerOption(
    val text: String,
    /** Points per result outcome id (e.g. "a" -> 2). */
    val scores: Map<String, Int>,
    /**
     * Optional drawable shown above the label in quiz options.
     * Use `R.drawable.*` from [app/src/main/res/drawable].
     */
    @DrawableRes val imageResId: Int? = null,
)
