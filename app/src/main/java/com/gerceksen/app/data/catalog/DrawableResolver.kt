package com.gerceksen.app.data.catalog

import android.content.res.Resources

fun interface DrawableResolver {
    /** @return drawable resource id or null if missing / invalid name */
    fun drawableId(name: String?): Int?
}

class ResourcesDrawableResolver(
    private val resources: Resources,
    private val packageName: String,
) : DrawableResolver {
    override fun drawableId(name: String?): Int? {
        if (name.isNullOrBlank()) return null
        val id = resources.getIdentifier(name, "drawable", packageName)
        return id.takeIf { it != 0 }
    }
}
