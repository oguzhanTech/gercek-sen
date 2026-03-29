package com.gerceksen.app.data.catalog

import android.content.Context
import android.util.Log
import kotlinx.serialization.json.Json

private const val ASSET_NAME = "quiz_catalog.json"
private const val TAG = "QuizCatalogLoader"

private val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
    coerceInputValues = true
}

fun loadQuizCatalog(context: Context): QuizCatalogDomain? {
    val resolver = ResourcesDrawableResolver(
        resources = context.resources,
        packageName = context.packageName,
    )
    return try {
        val text = context.assets.open(ASSET_NAME).bufferedReader().use { it.readText() }
        val dto = json.decodeFromString<QuizCatalogDto>(text)
        mapCatalogDtoToDomain(dto, resolver)
    } catch (e: Exception) {
        Log.e(TAG, "Failed to load $ASSET_NAME", e)
        null
    }
}
