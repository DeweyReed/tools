@file:Suppress("unused")

package com.github.deweyreed.tools.helper

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

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

fun SharedPreferences.intValue(
    key: String,
    default: Int = -1
): ReadWriteProperty<Any?, Int> {
    return object : ReadWriteProperty<Any?, Int> {
        override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
            return getInt(key, default)
        }

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
            edit { putInt(key, value) }
        }
    }
}

fun SharedPreferences.longValue(
    key: String,
    default: Long = -1L
): ReadWriteProperty<Any?, Long> {
    return object : ReadWriteProperty<Any?, Long> {
        override fun getValue(thisRef: Any?, property: KProperty<*>): Long {
            return getLong(key, default)
        }

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: Long) {
            edit { putLong(key, value) }
        }
    }
}

fun SharedPreferences.booleanValue(
    key: String,
    default: Boolean = false
): ReadWriteProperty<Any?, Boolean> {
    return object : ReadWriteProperty<Any?, Boolean> {
        override fun getValue(thisRef: Any?, property: KProperty<*>): Boolean {
            return getBoolean(key, default)
        }

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: Boolean) {
            edit { putBoolean(key, value) }
        }
    }
}

fun SharedPreferences.floatValue(
    key: String,
    default: Float = 0f
): ReadWriteProperty<Any?, Float> {
    return object : ReadWriteProperty<Any?, Float> {
        override fun getValue(thisRef: Any?, property: KProperty<*>): Float {
            return getFloat(key, default)
        }

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: Float) {
            edit { putFloat(key, value) }
        }
    }
}

fun SharedPreferences.stringValue(
    key: String,
    default: String? = null
): ReadWriteProperty<Any?, String?> {
    return object : ReadWriteProperty<Any?, String?> {
        override fun getValue(thisRef: Any?, property: KProperty<*>): String? {
            return getString(key, default)
        }

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: String?) {
            edit { putString(key, value) }
        }
    }
}

fun SharedPreferences.nonNullStringValue(
    key: String,
    default: String = ""
): ReadWriteProperty<Any?, String> {
    return object : ReadWriteProperty<Any?, String> {
        override fun getValue(thisRef: Any?, property: KProperty<*>): String {
            return getNonNullString(key, default)
        }

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
            edit { putString(key, value) }
        }
    }
}

fun SharedPreferences.stringSetValue(
    key: String,
    default: Set<String> = emptySet()
): ReadWriteProperty<Any?, Set<String>> {
    return object : ReadWriteProperty<Any?, Set<String>> {
        override fun getValue(thisRef: Any?, property: KProperty<*>): Set<String> {
            return getNonNullStringSet(key, default)
        }

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: Set<String>) {
            edit { putStringSet(key, value) }
        }
    }
}
