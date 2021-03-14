@file:Suppress("unused")

package xyz.aprildown.tools.arch

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

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
