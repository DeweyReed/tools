@file:Suppress("NOTHING_TO_INLINE", "unused")

package xyz.aprildown.tools.helper

import android.content.ContentResolver
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.annotation.AnyRes
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.PluralsRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import java.text.NumberFormat

/**
 * Created on 2017/11/3.
 */

// Android Resource Naming Cheat Sheet
// Layout: what_where.xml(activity_main.xml)
// String: where_description(all_done)
// Drawable: where_description_size(main_background)
// ID: what_where_description(linear_layout_main_fragment_container)
// Dimen: what_where_description_size(key_line_all_text)

// region Resources

@Deprecated("Use resource id to avoid proguard hell")
@AnyRes
inline fun Context.findResourceId(type: String, name: String, @AnyRes default: Int = 0): Int {
    val id = resources.getIdentifier(name, type, packageName)
    return if (id == 0) default else id
}

/**
 * Is -night resources used?
 */
val Resources.isDarkTheme: Boolean
    get() = configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES

// endregion Resources

// region String

fun Context.getNumberFormattedQuantityString(@PluralsRes id: Int, quantity: Int): String =
    resources.getNumberFormattedQuantityString(id, quantity)

fun Resources.getNumberFormattedQuantityString(@PluralsRes id: Int, quantity: Int): String =
    getQuantityString(id, quantity, NumberFormat.getInstance().format(quantity))

// endregion String

// region Drawable

fun Context.drawable(@DrawableRes res: Int): Drawable {
    val drawable = AppCompatResources.getDrawable(this, res)
    requireNotNull(drawable) { "Unable to find drawable $res" }
    return drawable
}

fun Drawable.tinted(@ColorInt color: Int): Drawable =
    DrawableCompat.wrap(this).mutate().apply { setTint(color) }

// endregion Drawable

// region Color

@ColorInt
fun Context.color(@ColorRes res: Int): Int = ContextCompat.getColor(this, res)

@ColorInt
fun Context.themeColor(@AttrRes attrRes: Int): Int {
    val a = obtainStyledAttributes(null, intArrayOf(attrRes))
    try {
        return a.getColor(0, Color.RED)
    } finally {
        a.recycle()
    }
}

fun @receiver:ColorInt Int.toColorStateList(): ColorStateList = ColorStateList.valueOf(this)

// endregion Color

// region Dimension

fun Context.dimen(@DimenRes resource: Int): Int = resources.getDimensionPixelSize(resource)

fun Context.float(@DimenRes resource: Int): Float = ResourcesCompat.getFloat(resources, resource)

fun Context.themeDimen(@AttrRes attrRes: Int): Int {
    val a = obtainStyledAttributes(null, intArrayOf(attrRes))
    try {
        return a.getDimensionPixelSize(0, 0)
    } finally {
        a.recycle()
    }
}

// region Uri

/**
 * @param resourceId identifies an application resource
 * @return the Uri by which the application resource is accessed
 */
fun Context.getResourceUri(@AnyRes resourceId: Int): Uri = Uri.Builder()
    .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
    .authority(packageName)
    .path(resourceId.toString())
    .build()

// endregion Uri
