@file:Suppress("unused")

package xyz.aprildown.tools.helper

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.core.content.edit
import androidx.preference.PreferenceManager

/**
 * Created on 2017/11/3.
 */

val Context.safeContext: Context
    get() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) return this
        if (isDeviceProtectedStorage) return this
        return ContextCompat.createDeviceProtectedStorageContext(this) ?: this
    }

fun SharedPreferences.getNonNullString(name: String, default: String): String {
    return getString(name, default) ?: default
}

fun SharedPreferences.getNonNullStringSet(name: String, default: Set<String>): Set<String> {
    return getStringSet(name, default) ?: default
}

val Activity.localSharedPreferences: SharedPreferences get() = getPreferences(Context.MODE_PRIVATE)

fun SharedPreferences.getOrPutString(key: String, defaultProvider: () -> String): String {
    return getString(key, null) ?: (defaultProvider.invoke().also {
        edit {
            putString(key, it)
        }
    })
}

//
// Advanced
//

val Context.safeSharedPreference: SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(safeContext)
