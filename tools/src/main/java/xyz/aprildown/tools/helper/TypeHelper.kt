@file:Suppress("NOTHING_TO_INLINE", "unused")

package xyz.aprildown.tools.helper

/**
 * Created on 2017/12/3.
 */

fun Boolean.toInt(): Int = if (this) 1 else 0

fun Int.toBoolean(): Boolean = this != 0
