@file:Suppress("NOTHING_TO_INLINE", "unused")

package xyz.aprildown.tools.helper

/**
 * Created on 2017/12/3.
 */

fun Boolean.toInt(): Int = if (this) 1 else 0

fun Int.toBoolean(): Boolean = this != 0

inline fun <reified T : Enum<T>> safeValueOf(enumName: String?, default: T? = null): T? {
    return if (enumName != null &&
        enumValues<T>().any { it.name == enumName }
    ) enumValueOf<T>(enumName) else default
}
