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
fun Context.dp(value: Float): Float = value * density

fun Context.sp(value: Int): Float = value * scaledDensity
fun Context.sp(value: Float): Float = value * scaledDensity
