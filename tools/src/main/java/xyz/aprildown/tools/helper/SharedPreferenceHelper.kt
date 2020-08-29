@file:Suppress("unused")

package xyz.aprildown.tools.helper

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

/**
 * Created on 2017/11/3.
 */

fun Context.getSpString(name: String, default: String) =
    safeSharedPreference.getNonNullString(name, default)

fun Context.putSpString(name: String, value: String) =
    safeSharedPreference.storeString(name, value)

fun Context.getSpBoolean(name: String, default: Boolean) =
    safeSharedPreference.getBoolean(name, default)

fun Context.putSpBoolean(name: String, value: Boolean) =
    safeSharedPreference.storeBoolean(name, value)

fun Context.getSpInt(name: String, default: Int) = safeSharedPreference.getInt(name, default)
fun Context.putSpInt(name: String, value: Int) = safeSharedPreference.storeInt(name, value)
fun Context.getSpFloat(name: String, default: Float) = safeSharedPreference.getFloat(name, default)
fun Context.putSpFloat(name: String, value: Float) = safeSharedPreference.storeFloat(name, value)
fun Context.getSpLong(name: String, default: Long) = safeSharedPreference.getLong(name, default)
fun Context.putSpLong(name: String, value: Long) = safeSharedPreference.storeLong(name, value)

fun Context.getSpStringSet(name: String, default: Set<String>) =
    safeSharedPreference.getNonNullStringSet(name, default)

fun Context.putSpStringSet(name: String, value: Set<String>) =
    safeSharedPreference.storeStringSet(name, value)

fun SharedPreferences.getNonNullString(name: String, default: String): String =
    getString(name, default) ?: default

fun SharedPreferences.getNonNullStringSet(name: String, default: Set<String>): Set<String> =
    getStringSet(name, default) ?: default

fun SharedPreferences.storeString(name: String, value: String) =
    edit().putString(name, value).apply()

fun SharedPreferences.storeBoolean(name: String, value: Boolean) =
    edit().putBoolean(name, value).apply()

fun SharedPreferences.storeInt(name: String, value: Int) =
    edit().putInt(name, value).apply()

fun SharedPreferences.storeFloat(name: String, value: Float) =
    edit().putFloat(name, value).apply()

fun SharedPreferences.storeLong(name: String, value: Long) =
    edit().putLong(name, value).apply()

fun SharedPreferences.storeStringSet(name: String, value: Set<String>) =
    edit().putStringSet(name, value).apply()

val Activity.localSharedPreferences: SharedPreferences get() = getPreferences(Context.MODE_PRIVATE)

fun SharedPreferences.getOrPutString(key: String, defaultProvider: () -> String): String {
    return getString(key, null) ?: (defaultProvider.invoke().also {
        storeString(key, it)
    })
}

//
// Advanced
//

val Context.safeSharedPreference: SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(safeContext)
