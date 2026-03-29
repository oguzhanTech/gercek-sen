package com.gerceksen.app.data

import com.gerceksen.app.model.ResultReflectionContent

/**
 * Maps [com.gerceksen.app.model.QuizResultDefinition.id] to reflection copy.
 * Add entries as content is authored; missing id means no reflection section.
 */
object ResultReflectionCatalog {

    private val byResultId: Map<String, ResultReflectionContent> = mapOf(
        reflectionItC(),
    )

    fun get(resultId: String): ResultReflectionContent? = byResultId[resultId]

    private fun reflectionItC(): Pair<String, ResultReflectionContent> =
        "it_c" to ResultReflectionContent(
            whyPreview = "İçeride çok şey dönerken dışarı az sinyal vermek yorucu olabilir.",
            whyBody = """
                Bazen düşünmek, sindirmek ve doğru kelimeyi beklemek uzun sürer. Bu tempo dışarıdan ‘sessiz’ veya ‘kapalı’ sanılabilir; oysa içeride sürekli bir işleyiş vardır. Yakın çevre “ne olduğunu bilemeden” merak edebilir — bu da senin için ekstra bir yük gibi hissedilir. Bu bir bozukluk değil; sadece iç/dış ritim farkı.
            """.trimIndent(),
            balancePreview = "Küçük sinyaller büyük yükü azaltır.",
            balanceTips = listOf(
                "Kısa bir ‘şu an şöyleyim’ cümlesi bile beklentiyi yumuşatır.",
                "Yazılı mesajda tek satırlık durum paylaşımı (yorgun / düşünüyorum) yeterli olabilir.",
                "Sessiz kaldığın anları nazikçe adlandır: ‘biraz içime çekildim’ gibi.",
                "Kendinden önce ‘ne hissettiğini’ söylemek, açıklamadan önce gelir.",
            ),
            todayPreview = "Tek bir küçük işaret yeter.",
            todayAction = "Bugün birine tek cümleyle iç halini yaz veya söyle: örn. ‘Bugün biraz içimdeyim, akşam konuşuruz.’",
        )
}
