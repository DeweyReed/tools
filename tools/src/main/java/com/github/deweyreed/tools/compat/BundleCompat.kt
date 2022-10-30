@file:Suppress("unused")

package com.github.deweyreed.tools.compat

import android.os.Build
import android.os.Bundle
import android.os.Parcelable

inline fun <reified T : Parcelable> Bundle.getParcelableCompat(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelable(key, T::class.java)
    } else {
        @Suppress("DEPRECATION")
        getParcelable(key)
    }
}

inline fun <reified T : Parcelable> Bundle.getParcelableArrayCompat(key: String): Array<T> {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableArray(key, T::class.java) ?: emptyArray()
    } else {
        @Suppress("DEPRECATION")
        getParcelableArray(key)?.filterIsInstance<T>()?.toTypedArray() ?: emptyArray()
    }
}
