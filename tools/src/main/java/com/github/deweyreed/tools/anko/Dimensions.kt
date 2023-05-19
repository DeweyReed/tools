@file:Suppress("unused")

package com.github.deweyreed.tools.anko

import android.content.Context
import android.util.TypedValue

/**
 * [TypedValue.applyDimension]
 */

private inline val Context.density: Float get() = resources.displayMetrics.density
private inline val Context.scaledDensity: Float get() = resources.displayMetrics.scaledDensity

fun Context.dp(value: Int): Float = value * density

@Deprecated(message = "Use dp", replaceWith = ReplaceWith(expression = "dp(value).toInt()"))
fun Context.dip(value: Int): Int = dp(value).toInt()

fun Context.dp(value: Float): Float = value * density

@Deprecated(message = "Use dp", replaceWith = ReplaceWith(expression = "dp(value).toInt()"))
fun Context.dip(value: Float): Int = dp(value).toInt()

fun Context.sp(value: Int): Float = value * scaledDensity
fun Context.sp(value: Float): Float = value * scaledDensity
