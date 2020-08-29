@file:Suppress("unused")

package xyz.aprildown.tools.arch

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * Created on 2018/4/15.
 */

//
// LiveData
//

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) {
    liveData.observe(this, Observer(body))
}

fun <T : Any, L : LiveData<T>> LifecycleOwner.observeNonNull(liveData: L, body: (T) -> Unit) {
    liveData.observe(this, { if (it != null) body(it) })
}

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, observer: Observer<T>) {
    liveData.observe(this, observer)
}

//
// Fragment LiveData
//

fun <T : Any, L : LiveData<T>> Fragment.observeView(liveData: L, body: (T?) -> Unit) {
    liveData.observe(viewLifecycleOwner, Observer(body))
}

fun <T : Any, L : LiveData<T>> Fragment.observeViewNonNull(liveData: L, body: (T) -> Unit) {
    liveData.observe(viewLifecycleOwner, { if (it != null) body(it) })
}
