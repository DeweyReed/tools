@file:Suppress("unused")

package xyz.aprildown.tools.arch

import androidx.annotation.MainThread
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
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
