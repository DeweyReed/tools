@file:Suppress("unused")

package com.github.deweyreed.tools.arch

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * From [androidx.lifecycle.observe].
 */
@MainThread
inline fun <T> LiveData<T?>.observeNonNull(
    owner: LifecycleOwner,
    crossinline onChanged: (T) -> Unit
): Observer<T?> {
    val wrappedObserver = Observer<T?> { t ->
        if (t != null) {
            onChanged.invoke(t)
        }
    }
    observe(owner, wrappedObserver)
    return wrappedObserver
}

fun <T> SavedStateHandle.value(key: String): ReadWriteProperty<Any?, T?> {
    return object : ReadWriteProperty<Any?, T?> {
        override fun getValue(thisRef: Any?, property: KProperty<*>): T? {
            return get(key)
        }

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
            set(key, value)
        }
    }
}
