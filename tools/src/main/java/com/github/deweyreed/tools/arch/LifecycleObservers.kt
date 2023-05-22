@file:Suppress("unused")

package com.github.deweyreed.tools.arch

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner

fun Lifecycle.doOnLifecycleEvent(
    onCreate: (() -> Unit)? = null,
    onStart: (() -> Unit)? = null,
    onResume: (() -> Unit)? = null,
    onPause: (() -> Unit)? = null,
    onStop: (() -> Unit)? = null,
    onDestroy: (() -> Unit)? = null,
): LifecycleObserver {
    val observer = object : DefaultLifecycleObserver {
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
    addObserver(observer)
    return observer
}

fun Lifecycle.doOnStart(block: () -> Unit): LifecycleObserver {
    val observer = object : DefaultLifecycleObserver {
        override fun onStart(owner: LifecycleOwner) {
            super.onStart(owner)
            removeObserver(this)
            block()
        }
    }
    addObserver(observer)
    return observer
}

fun Lifecycle.doOnResume(block: () -> Unit): LifecycleObserver {
    val observer = object : DefaultLifecycleObserver {
        override fun onResume(owner: LifecycleOwner) {
            super.onResume(owner)
            removeObserver(this)
            block()
        }
    }
    addObserver(observer)
    return observer
}

fun Lifecycle.doOnDestroy(block: () -> Unit): LifecycleObserver {
    val observer = object : DefaultLifecycleObserver {
        override fun onDestroy(owner: LifecycleOwner) {
            super.onDestroy(owner)
            removeObserver(this)
            block()
        }
    }
    addObserver(observer)
    return observer
}
