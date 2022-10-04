@file:Suppress("unused")

package com.github.deweyreed.tools.arch

import androidx.annotation.MainThread
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
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

fun Lifecycle.doOnLifecycleEvent(
    onCreate: (() -> Unit)? = null,
    onStart: (() -> Unit)? = null,
    onResume: (() -> Unit)? = null,
    onPause: (() -> Unit)? = null,
    onStop: (() -> Unit)? = null,
    onDestroy: (() -> Unit)? = null,
) {
    addObserver(
        object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                super.onCreate(owner)
                onCreate?.invoke()
            }

            override fun onStart(owner: LifecycleOwner) {
                super.onStart(owner)
                onStart?.invoke()
            }

            override fun onResume(owner: LifecycleOwner) {
                super.onResume(owner)
                onResume?.invoke()
            }

            override fun onPause(owner: LifecycleOwner) {
                super.onPause(owner)
                onPause?.invoke()
            }

            override fun onStop(owner: LifecycleOwner) {
                super.onStop(owner)
                onStop?.invoke()
            }

            override fun onDestroy(owner: LifecycleOwner) {
                super.onDestroy(owner)
                onDestroy?.invoke()
            }
        }
    )
}

fun Lifecycle.doOnDestroy(block: () -> Unit) {
    doOnLifecycleEvent(onDestroy = block)
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
