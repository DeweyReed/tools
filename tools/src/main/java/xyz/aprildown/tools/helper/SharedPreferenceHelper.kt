@file:Suppress("unused")

package xyz.aprildown.tools.helper

import android.content.SharedPreferences
import androidx.core.content.edit

/**
 * Created on 2017/11/3.
 */

fun SharedPreferences.getNonNullString(name: String, default: String): String {
    return getString(name, default) ?: default
}

fun SharedPreferences.getNonNullStringSet(
    name: String,
    default: Set<String> = emptySet()
): Set<String> {
    return getStringSet(name, default) ?: default
}

fun SharedPreferences.getOrPutString(key: String, defaultValue: () -> String): String {
    val value = getString(key, null)
    return if (value == null) {
        val answer = defaultValue()
        edit { putString(key, answer) }
        answer
    } else {
        value
    }
}
