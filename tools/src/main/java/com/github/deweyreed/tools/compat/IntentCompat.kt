@file:Suppress("unused")

package com.github.deweyreed.tools.compat

import android.content.Intent
import android.os.Build
import android.os.Parcelable

inline fun <reified T : Parcelable> Intent.getParcelableExtraCompat(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableExtra(key, T::class.java)
    } else {
        @Suppress("DEPRECATION")
        getParcelableExtra(key)
    }
}

inline fun <reified T : Parcelable> Intent.getParcelableArrayExtraCompat(key: String): Array<T> {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableArrayExtra(key, T::class.java) ?: emptyArray()
    } else {
        @Suppress("DEPRECATION")
        getParcelableArrayExtra(key)?.filterIsInstance<T>()?.toTypedArray() ?: emptyArray()
    }
}
