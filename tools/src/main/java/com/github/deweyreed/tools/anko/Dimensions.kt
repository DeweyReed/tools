@file:Suppress("unused")

package com.github.deweyreed.tools.anko

import android.content.Context
import android.util.TypedValue

fun Context.dp(value: Int): Float {
    return dp(value.toFloat())
}

fun Context.dp(value: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, resources.displayMetrics)
}

fun Context.sp(value: Int): Float {
    return sp(value.toFloat())
}

/*
 * [https://developer.android.com/about/versions/14/features#convert-pixels]
 */
fun Context.sp(value: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, resources.displayMetrics)
}
