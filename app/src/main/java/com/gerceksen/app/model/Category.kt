package com.gerceksen.app.model

data class Category(
    val id: String,
    val name: String,
    val shortDescription: String,
    val iconGlyph: CategoryIcon,
)

enum class CategoryIcon {
    Social,
    Character,
    Relations,
    Truth,
    Strength,
}
