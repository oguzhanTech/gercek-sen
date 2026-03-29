package com.gerceksen.app.model

/**
 * Extended reflection copy for a quiz result ([QuizResultDefinition.id]).
 * Kept separate from [QuizResultDefinition] so share/screenshot copy stays stable
 * and this block can later move to locale/JSON.
 */
data class ResultReflectionContent(
    /** One-line teaser on the "why" preview card. */
    val whyPreview: String,
    /** Full text for "Neden böyle hissediyorum?" */
    val whyBody: String,
    /** One-line teaser on the "balance" preview card. */
    val balancePreview: String,
    /** 2–4 short tips for "Bunu nasıl dengelerim?" */
    val balanceTips: List<String>,
    /** One-line teaser on the "today" preview card. */
    val todayPreview: String,
    /** Single actionable line for "Bugün ne yapabilirim?" */
    val todayAction: String,
)
