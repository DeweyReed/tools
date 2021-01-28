@file:Suppress("unused")

package xyz.aprildown.tools.arch

import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * Created on 2018/4/15.
 */

@Deprecated("Use lifecycleOwner.observe", ReplaceWith(""))
fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) {
    liveData.observe(this, Observer(body))
}

@Deprecated("Use lifecycleOwner.observeNonNull", ReplaceWith(""))
fun <T : Any, L : LiveData<T>> LifecycleOwner.observeNonNull(liveData: L, body: (T) -> Unit) {
    liveData.observe(this, { if (it != null) body(it) })
}

@Deprecated("Use lifecycleOwner.observe", ReplaceWith(""))
fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, observer: Observer<T>) {
    liveData.observe(this, observer)
}

@Deprecated("Use viewLifecycleOwner.observe", ReplaceWith(""))
fun <T : Any, L : LiveData<T>> Fragment.observeView(liveData: L, body: (T?) -> Unit) {
    liveData.observe(viewLifecycleOwner, Observer(body))
}

@Deprecated("Use viewLifecycleOwner.observeNonNull", ReplaceWith(""))
fun <T : Any, L : LiveData<T>> Fragment.observeViewNonNull(liveData: L, body: (T) -> Unit) {
    liveData.observe(viewLifecycleOwner, { if (it != null) body(it) })
}

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
