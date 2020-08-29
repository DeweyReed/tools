@file:Suppress("NOTHING_TO_INLINE", "unused")

package xyz.aprildown.tools.anko

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment

private inline val Context.density: Float get() = resources.displayMetrics.density
private inline val Context.scaledDensity: Float get() = resources.displayMetrics.scaledDensity

fun Context.dp(value: Int): Float = value * density
fun Context.dip(value: Int): Int = dp(value).toInt()
fun Context.dp(value: Float): Float = value * density
fun Context.dip(value: Float): Int = dp(value).toInt()
fun Context.sp(value: Int): Float = value * scaledDensity
fun Context.sp(value: Float): Float = value * scaledDensity
// fun Context.px2dip(px: Int): Float = px.toFloat() / density
// fun Context.px2sp(px: Int): Float = px.toFloat() / scaledDensity

inline fun View.dp(value: Int): Float = context.dp(value)
inline fun View.dip(value: Int): Int = context.dip(value)
inline fun View.dp(value: Float): Float = context.dp(value)
inline fun View.dip(value: Float): Int = context.dip(value)
inline fun View.sp(value: Int): Float = context.sp(value)
inline fun View.sp(value: Float): Float = context.sp(value)
// inline fun View.px2dip(px: Int): Float = context.px2dip(px)
// inline fun View.px2sp(px: Int): Float = context.px2sp(px)

inline fun Fragment.dp(value: Int): Float = requireContext().dp(value)
inline fun Fragment.dip(value: Int): Int = requireContext().dip(value)
inline fun Fragment.dp(value: Float): Float = requireContext().dp(value)
inline fun Fragment.dip(value: Float): Int = requireContext().dip(value)
inline fun Fragment.sp(value: Int): Float = requireContext().sp(value)
inline fun Fragment.sp(value: Float): Float = requireContext().sp(value)
// inline fun Fragment.px2dip(px: Int): Float = requireContext().px2dip(px)
// inline fun Fragment.px2sp(px: Int): Float = requireContext().px2sp(px)
